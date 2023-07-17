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
import org.grails.HtmlPost
import org.grails.plugin.Plugin

import static groovy.io.FileType.FILES

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
        File outputFile = new File(inputFile.absolutePath + "/" + "plugins.html")
        outputFile.createNewFile()
        String html = pluginsHeader() + plugins.collect { plugin -> htmlForPlugin(plugin) }.join("\n")
        outputFile.text = RenderSiteTask.renderHtmlWithTemplateContent(html, metadata, templateText)
    }

    void renderHtmlPagesForTags(File folder, List<Plugin> plugins) {
        // Get Set<PluginTag> given plugins
        // Loop through every tag
        // Get the plugins by tag
        // and generate HTML page just with the plugins belong tag
    }

    void renderHtmlPagesForOwners(File folder, List<Plugin> plugins) {
        // Get Set<Owner> given plugins
        // Loop through every owner
        // Get the plugins by owner
        // and generate HTML page just with the plugins belong owner
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
    String htmlForPlugin(Plugin plugin) {
        StringWriter writer = new StringWriter()
        MarkupBuilder mb = new MarkupBuilder(writer)
        mb.div(class: 'plugin') {
            h3 {
                mkp.yieldUnescaped(plugin.name)
            }
            if (plugin.owner) {
                a(href: "[%url]/plugins/owner/${plugin.owner}.html") {
                    mkp.yield(plugin.owner)
                }
            }

            //TODO render tags

        }
        writer.toString()

    }
    @CompileDynamic
    List<Plugin> pluginsFromJson(Object json) {
        List<Plugin> plugins = []
        for (int i = 0; i < json.size(); i++) {
            plugins.add(new Plugin(name: json[i].bintrayPackage.name, owner: json[i].bintrayPackage.owner))
        }
        plugins
    }

}
