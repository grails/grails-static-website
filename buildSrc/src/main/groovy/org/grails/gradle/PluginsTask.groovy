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
import org.grails.plugin.Tag

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

        String html = pluginsHeader() + PluginsPage.mainContent(plugins)
        pluginOutputFile.text = RenderSiteTask.renderHtmlWithTemplateContent(html, metadata, templateText)

        Set<Tag> tags = getTags(plugins)
        for (tag in tags) {
            File tagsOutputFile = new File(pluginsTagsFolder.getPath() + "/" + tag.label + ".html")
            tagsOutputFile.createNewFile()
            String htmlForTags = tagsHeader() + renderHtmlPagesForTags(plugins, tag)
            tagsOutputFile.text = RenderSiteTask.renderHtmlWithTemplateContent(htmlForTags, metadata, templateText)
        }

        Set<Owner> owners = getOwners(plugins)

        for (owner in owners) {
            File ownersOutputFile = new File(pluginsOwnersFolder.getPath() + "/" + owner.name + ".html")
            ownersOutputFile.createNewFile()
            String htmlForOwnersFile = ownersHeader() + renderHtmlPagesForOwners(plugins, owner)
            ownersOutputFile.text = RenderSiteTask.renderHtmlWithTemplateContent(htmlForOwnersFile, metadata, templateText)
        }
    }

    String renderHtmlPagesForTags(List<Plugin> plugins, Tag tag) {
        List<String> pluginsByTag = new ArrayList<>()
            for (plugin in plugins){
                if(plugin.labels.contains(tag)){
                    pluginsByTag.add(PluginsPage.renderSinglePlugin(plugin))
                }
            }
            pluginsByTag.toString()
    }

    String renderHtmlPagesForOwners(List<Plugin> plugins, Owner owner) {

        List<String> pluginByOwner = new ArrayList<>()
        for (plugin in plugins){
            if(plugin.owner.name == owner.getName()){
                pluginByOwner.add(PluginsPage.renderSinglePlugin(plugin))
            }
        }
        pluginByOwner.toString()
    }


    @CompileDynamic
    String pluginsHeader() {
        StringWriter writer = new StringWriter()
        MarkupBuilder mb = new MarkupBuilder(writer)
        mb.div(class: 'headerbar chalicesbg') {
            div(class: 'content') {
                h1 {
                    mkp.yield('Plugins')
                }
            }
        }
        writer.toString()
    }


    @CompileDynamic
    List<Plugin> pluginsFromJson(Object json) {

        List<Plugin> plugins = []
        for (int i = 0; i < json.size(); i++) {
            Owner owner = new Owner(name: json[i].bintrayPackage.owner)

            List<Tag> tag = new ArrayList<Tag>()
            for (label in json[i].bintrayPackage.labels){
                tag.add(new Tag(label: label))
            }


            plugins.add(new Plugin(name: json[i].bintrayPackage.name, owner: owner,
                    latestVersion: json[i].bintrayPackage.latestVersion, labels: tag))
        }
        plugins
    }

    static boolean seen(Tag tag, Set elements) {
        elements.contains(tag)
    }

    static boolean seen(Owner owner, Set elements) {
        elements.contains(owner)
    }

    Set<Tag> getTags(List<Plugin> plugins) {

        Set<Tag> tags = []
        for (plugin in plugins){
            for (label in plugin.labels){
                if (!seen(label,tags)){
                    tags.add(label);
                }
            }
        }
        return tags
    }

    @CompileDynamic
    String tagsHeader() {
        StringWriter writer = new StringWriter()
        MarkupBuilder mb = new MarkupBuilder(writer)
        mb.div(class: 'headerbar chalicesbg') {
            div(class: 'content') {
                h1 {
                    mkp.yield('Tags')
                }
            }
        }
        writer.toString()
    }
    @CompileDynamic
    String ownersHeader() {
        StringWriter writer = new StringWriter()
        MarkupBuilder mb = new MarkupBuilder(writer)
        mb.div(class: 'headerbar chalicesbg') {
            div(class: 'content') {
                h1 {
                    mkp.yield('Owners')
                }
            }
        }
        writer.toString()
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
