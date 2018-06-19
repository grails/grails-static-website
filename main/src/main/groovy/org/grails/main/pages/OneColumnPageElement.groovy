package org.grails.main.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.model.PageElement

@CompileStatic
class OneColumnPageElement {
    PageElement uniqueColumn

    OneColumnPageElement(List<PageElement> elementList) {
        uniqueColumn = elementList.first()
    }

    @CompileDynamic
    String renderAsHtml() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: "onecolum") {
            div(class: "column") {
                mkp.yieldUnescaped uniqueColumn.renderAsHtml()
            }
        }
        writer.toString()
    }
}
