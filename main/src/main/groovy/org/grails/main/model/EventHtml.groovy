package org.grails.main.model

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.main.MarkdownUtil
import org.grails.model.PageElement

@CompileStatic
trait EventHtml implements PageElement {

    abstract String getImage()
    abstract String getHref()
    abstract String getTitle()
    abstract String getLocation()
    abstract String getDates()
    abstract String getAbout()

    @Override
    @CompileDynamic
    String renderAsHtml() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'event') {
            img src: image, alt: title
            h3 {
                a href: href, title
            }
            span class: "location", location
            span class: "dates", dates
            html.mkp.yieldUnescaped(MarkdownUtil.htmlFromMarkdown(about))
        }
        writer.toString()
    }

}