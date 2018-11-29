package org.grails.main.model

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.model.PageElement

@CompileStatic
trait BuildStatusHtml implements PageElement {

    abstract String getTitle()
    abstract String getHref()
    abstract String getBadge()
    abstract String getVersion()

    @Override
    String renderAsHtml() {

    }

    @CompileDynamic
    String renderAsHtml(boolean useVersionColumn) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.tr {
            if (useVersionColumn) {
                td version
            }
            td title
            td {
                a(href: href) {
                    img src: badge
                }
            }
        }
        writer.toString()
    }


}