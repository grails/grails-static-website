package org.grails.main.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.main.model.DocumentationGroup
import org.grails.model.GuideGroup
import org.grails.model.PageElement

@CompileStatic
class TwoColumnsPageElement {
    int firstColumnItems = 0
    int secondColumnItems = 0
    List<PageElement> firstColumn = []
    List<PageElement> secondColumn = []

    TwoColumnsPageElement(List<PageElement> elementList) {
        for ( int i = 0; i < elementList.size(); i++ ) {
            PageElement element = elementList[i] as PageElement
            boolean addToFirstColumn = ( i % 2 )
            int numberOfElements = 0
            if ( element instanceof DocumentationGroup) {
                numberOfElements = (element as DocumentationGroup).links.size()
            }
            if ( element instanceof GuideGroup) {
                numberOfElements = (element as GuideGroup).items.size()
            }
            if ( !addToFirstColumn ) {
                if ( secondColumnItems > ( firstColumnItems + numberOfElements ) ) {
                    addToFirstColumn = true
                }
            }

            if ( addToFirstColumn ) {
                firstColumnItems += numberOfElements
                firstColumn << element
            } else {
                secondColumnItems += numberOfElements
                secondColumn << element
            }
        }
    }

    @CompileDynamic
    String renderAsHtml() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: "twocolumns") {
            div(class: "column") {
                for (PageElement el : firstColumn) {
                    mkp.yieldUnescaped el.renderAsHtml()
                }
            }
            div(class: "column") {
                for (PageElement el : secondColumn) {
                    mkp.yieldUnescaped el.renderAsHtml()
                }
            }
        }
        writer.toString()
    }
}

