package org.grails.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder

@CompileStatic
class SiteMapPage implements HtmlPage {

    List<HtmlPage> pages
    String url
    String slug = 'sitemap.xml'

    SiteMapPage(String url, List<HtmlPage> pages) {
        this.url = url
        this.pages = pages
    }

    @CompileDynamic
    String html() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)

        html.urlset(xmlns: "http://www.sitemaps.org/schemas/sitemap/0.9") {
            for ( HtmlPage page : pages ) {
                url {
                    loc "${url}/${page.slug}"
                }
            }
        }
        '<?xml version="1.0" encoding="UTF-8"?>\n' + writer.toString()
    }
}
