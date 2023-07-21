package org.grails.gradle

import groovy.json.JsonSlurper
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import org.grails.plugin.Owner
import org.grails.plugin.Plugin
import org.grails.plugin.PluginsPage

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


        String json = new URL(GRAILS_PLUGINS_JSON).text
        JsonSlurper slurper = new JsonSlurper()
        def result = slurper.parseText(json)
        renderHtml(pluginsFromJson(result), templateText, metadata)
    }

    void renderHtml(List<Plugin> plugins, String templateText, Map<String, String> metadata) {
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
        File pluginOutputFile = new File(inputFile.absolutePath + "/" + "plugins.html")
        pluginOutputFile.createNewFile()

        String html = PluginsPage.mainContent(siteUrl, plugins, 'Grails Plugins')
        pluginOutputFile.text = RenderSiteTask.renderHtmlWithTemplateContent(html, metadata, templateText)

        Set<String> tags = getTags(plugins)
        for (String tag in tags) {
            File tagsOutputFile = new File(pluginsTagsFolder.getPath() + "/" + tag + ".html")
            tagsOutputFile.createNewFile()
            String htmlForTags = renderHtmlPagesForTags(siteUrl, plugins, tag)
            tagsOutputFile.text = RenderSiteTask.renderHtmlWithTemplateContent(htmlForTags, metadata, templateText)
        }

        Set<Owner> owners = getOwners(plugins)

        for (owner in owners) {
            File ownersOutputFile = new File(pluginsOwnersFolder.getPath() + "/" + owner.name + ".html")
            ownersOutputFile.createNewFile()
            String htmlForOwnersFile = renderHtmlPagesForOwners(siteUrl, plugins, owner)
            ownersOutputFile.text = RenderSiteTask.renderHtmlWithTemplateContent(htmlForOwnersFile, metadata, templateText)
        }
    }

    String renderHtmlPagesForTags(String siteUrl, List<Plugin> plugins, String tag) {
        List<Plugin> filteredPlugins = plugins.stream().filter(p -> p.labels.contains(tag)).collect(Collectors.toList())
        return PluginsPage.mainContent(siteUrl, filteredPlugins, "Plugins by tag #${tag}", )
    }

    String renderHtmlPagesForOwners(String siteUrl, List<Plugin> plugins, Owner owner) {
        List<Plugin> filteredPlugins = plugins.stream().filter(p -> p.owner.name == owner.name).collect(Collectors.toList())
        return PluginsPage.mainContent(siteUrl, filteredPlugins, "Plugins by creator: #${owner.name}")
    }

    @CompileDynamic
    List<Plugin> pluginsFromJson(Object json) {
        List<Plugin> plugins = []
        for (int i = 0; i < json.size(); i++) {
            Owner owner = new Owner(name: json[i].bintrayPackage.owner)
            plugins.add(new Plugin(name: json[i].bintrayPackage.name,
                    updated: json[i].bintrayPackage.updated,
                    owner: owner,
                    desc: json[i].bintrayPackage.desc,
                    vcsUrl: json[i].bintrayPackage.vcsUrl,
                    latestVersion: json[i].bintrayPackage.latestVersion,
                    githubStars: githubStars(json[i].bintrayPackage.vcsUrl).orElse(null),
                    labels: json[i].bintrayPackage.labels as List<String>))
        }
        plugins
    }

    Optional<Integer> githubStars(String vcsUrl) {
        if (!vcsUrl) {
            return Optional.empty()
        }
        return Optional.of(36)
        //TODO fetch API
        return Optional.empty()

    }

    static boolean seen(Owner owner, Set elements) {
        elements.contains(owner)
    }

    Set<String> getTags(List<Plugin> plugins) {
        Set<String> tags = []
        for (plugin in plugins) {
            for (String label : plugins.labels) {
                tags.add(label)
            }
        }
        tags
    }

    Set<Owner> getOwners(List<Plugin> plugins) {
        Set<Owner> owners = []
        for (plugin in plugins){
                if (!seen(plugin.owner, owners)){
                    owners.add(plugin.owner);
                }
        }
        return owners
    }
}
