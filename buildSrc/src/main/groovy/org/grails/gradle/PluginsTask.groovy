package org.grails.gradle

import groovy.json.JsonSlurper
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.grails.plugin.Owner
import org.grails.plugin.Plugin
import org.grails.plugin.PluginsPage

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

@CompileStatic
class PluginsTask extends DefaultTask {
    public static final String GRAILS_PLUGINS_JSON = "https://raw.githubusercontent.com/grails/grails-plugins-metadata/main/grails-plugins.json"
    @OutputDirectory
    final Property<File> output = project.objects.property(File)

    @Input
    final Property<String> url = project.objects.property(String)

    @Input
    final Property<File> document = project.objects.property(File)

    @TaskAction
    void renderPluginsPage() {
        File template = document.get()
        final String templateText = template.text

        Map<String, String> metadata = RenderSiteTask.siteMeta("Grails Plugins", // TODO Make it configurable
                "List of Plugins", //TODO Make it configurable
                url.get(),
                [], //TODO
                "all", //TODO Make it configurable,
                "",
                "")
        Map<String, String> resolvedMetadata = org.grails.gradle.RenderSiteTask.processMetadata(metadata)

        String json = new URL(GRAILS_PLUGINS_JSON).text
        JsonSlurper slurper = new JsonSlurper()
        def result = slurper.parseText(json)

        List<Plugin> plugins = pluginsFromJson(result)
        renderHtml(plugins, templateText, metadata, "plugins.html")
    }

    void renderHtml(List<Plugin> plugins, String templateText, Map<String, String> metadata, String fileName) {
        String siteUrl = url.get()
        File inputFile = new File(output.get().absolutePath + "/" + RenderSiteTask.DIST)
        if (!inputFile.exists()) {
            inputFile.mkdir()
        }
        File pluginsFolder = new File(inputFile.absolutePath + "/" + "plugins")
        if (!pluginsFolder.exists()) {
            pluginsFolder.mkdir()
        }
        File pluginsTagsFolder = new File(inputFile.absolutePath + "/" + "plugins" + "/" + "tags")
        if (!pluginsTagsFolder.exists()) {
            pluginsTagsFolder.mkdir()
        }
        File pluginsOwnersFolder = new File(inputFile.absolutePath + "/" + "plugins" + "/" + "owners")
        if (!pluginsOwnersFolder.exists()) {
            pluginsOwnersFolder.mkdir()
        }
        File pluginOutputFile = new File(inputFile.absolutePath + "/" + fileName)
        pluginOutputFile.createNewFile()
        String html = PluginsPage.mainContent(siteUrl, plugins, 'Grails Plugins')
        html = RenderSiteTask.renderHtmlWithTemplateContent(html, metadata, templateText)
        html = RenderSiteTask.highlightMenu(html, metadata, "/plugins.html")
        pluginOutputFile.text = html

        List<String> tags = plugins.stream().flatMap(p -> p.labels.stream()).distinct().collect(Collectors.toList()) as List<String>
        for (String tag in tags) {
            File tagsOutputFile = new File(pluginsTagsFolder.getPath() + "/" + tag + ".html")
            tagsOutputFile.createNewFile()
            String htmlForTags = renderHtmlPagesForTags(siteUrl, plugins, tag)
            tagsOutputFile.text = RenderSiteTask.renderHtmlWithTemplateContent(htmlForTags, metadata, templateText)
        }

        List<String> owners = plugins.stream().map(p -> p.owner.name).distinct().collect(Collectors.toList())
                as List<String>

        for (String owner in owners) {
            File ownersOutputFile = new File(pluginsOwnersFolder.getPath() + "/" + owner + ".html")
            ownersOutputFile.createNewFile()
            String htmlForOwnersFile = renderHtmlPagesForOwners(siteUrl, plugins, owner)
            ownersOutputFile.text = RenderSiteTask.renderHtmlWithTemplateContent(htmlForOwnersFile, metadata, templateText)
        }
    }

    String renderHtmlPagesForTags(String siteUrl, List<Plugin> plugins, String tag) {
        List<Plugin> filteredPlugins = plugins.stream().filter(p -> p.labels.contains(tag)).collect(Collectors.toList())
        return PluginsPage.mainContent(siteUrl, filteredPlugins, "Plugins by tag #${tag}", )
    }

    String renderHtmlPagesForOwners(String siteUrl, List<Plugin> plugins, String owner) {
        List<Plugin> filteredPlugins = plugins.stream().filter(p -> p.owner.name == owner).collect(Collectors.toList())
        return PluginsPage.mainContent(siteUrl, filteredPlugins, "Plugins by creator: #${owner}")
    }

    @CompileDynamic
    List<Plugin> pluginsFromJson(Object json) {
        List<Plugin> plugins = []

        for (int i = 0; i < json.size(); i++) {
            Owner owner = new Owner(name: json[i].bintrayPackage.owner)
            LocalDateTime updatedDate = parseIsoStringToDate(json[i].bintrayPackage.updated)
            plugins.add(new Plugin(name: json[i].bintrayPackage.name,
                    updated: updatedDate,
                    owner: owner,
                    desc: json[i].bintrayPackage.desc,
                    vcsUrl: json[i].bintrayPackage.vcsUrl,
                    latestVersion: json[i].bintrayPackage.latestVersion,
                    githubStars: githubStars(json[i].bintrayPackage.vcsUrl).orElse(null),
                    labels: json[i].bintrayPackage.labels as List<String>))
        }
        plugins
    }

    LocalDateTime parseIsoStringToDate(String isoFormattedString){
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" );
        return  LocalDateTime.parse(isoFormattedString, f);
    }

    @CompileDynamic
    Optional<Integer> githubStars(String vcsUrl) {
        if (!vcsUrl) {
            return Optional.empty()
        }
        if (!vcsUrl.contains("github.com")) {
            return Optional.empty()
        }
        if (!System.getenv("GH_TOKEN")) {
            return Optional.empty()
        }
        try {
            println("fetching github stars of " + vcsUrl)
            String url = "https://api.github.com/repos/" + vcsUrl.substring(vcsUrl.indexOf("github.com/") + "github.com/".length())
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("X-GitHub-Api-Version", "2022-11-28")
                    .header("Authorization", "Bearer ${System.getenv("GH_TOKEN")}")
                    .GET()
                    .build()
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString())
            if (response.statusCode() == 200) {
                String json = response.body()
                Integer stars = new JsonSlurper().parseText(json).stargazers_count as Integer
                return stars == 0 ? Optional.empty() : Optional.of(stars)
            }
        } catch (Exception e) {
            println e.message
            return Optional.empty()
        }
        return Optional.empty()
    }

}
