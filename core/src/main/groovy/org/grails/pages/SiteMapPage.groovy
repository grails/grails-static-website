package org.grails.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder

@CompileStatic
class SiteMapPage implements HtmlPage {

    List<String> urls
    String slug = 'sitemap.xml'

    SiteMapPage(List<String> urls) {
        this.urls = urls
    }

    @CompileDynamic
    String html() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)

        html.urlset(xmlns: "http://www.sitemaps.org/schemas/sitemap/0.9") {
            for ( String urlStr : this.urls) {
                url {
                    loc urlStr
                }
            }
        }
        '<?xml version="1.0" encoding="UTF-8"?>\n' + writer.toString()
    }
}
