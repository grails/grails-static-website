package org.grails.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder

@CompileStatic
class RedirectPage implements HtmlPage {

    String url
    String slug

    RedirectPage(String url, String slug) {
        this.url = url
        this.slug = slug
    }

    @CompileDynamic
    String html() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)

        html.html(lang: 'en') {
            head {
                meta(charset: "utf-8")
                mkp.yieldUnescaped "<meta http-equiv='refresh', content=\"0 url=${url}\"/>"
            }
            body {
            }
        }
        writer.toString()
    }

    @Override
    void setTimestamp(String timestamp) {

    }
}
