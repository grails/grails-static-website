package org.grails.gradle

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import static groovy.io.FileType.FILES

@CompileStatic
class SitemapTask extends DefaultTask {
    static final String FILE_SITEMAP = 'sitemap.xml'

    @OutputDirectory
    final Property<File> output = project.objects.property(File)

    @Input
    final Property<String> url = project.objects.property(String)

    @TaskAction
    void renderSitemap() {
        String websiteUrl = url.get()
        File inputFile = new File(output.get().absolutePath + "/" + RenderSiteTask.DIST)

        List<String> urls = []
        inputFile.eachFileRecurse(FILES) {
            if (it.name.endsWith('.html')) {
                urls << "${websiteUrl}${it.absolutePath.replace(inputFile.absolutePath, "")}".toString()
            }
        }
        File outputFile = new File(inputFile.absolutePath + "/" + FILE_SITEMAP)
        outputFile.createNewFile()
        outputFile.text = sitemapContent(urls)
    }

    @CompileDynamic
    static String sitemapContent(List<String> urls) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.urlset(xmlns: "https://www.sitemaps.org/schemas/sitemap/0.9") {
            for (String urlStr : urls) {
                url {
                    loc urlStr
                }
            }
        }
        '<?xml version="1.0" encoding="UTF-8"?>\n' + writer.toString()
    }
}
