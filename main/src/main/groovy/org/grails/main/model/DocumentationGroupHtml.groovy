package org.grails.main.model

import groovy.transform.CompileDynamic
import groovy.xml.MarkupBuilder
import org.grails.model.PageElement

trait DocumentationGroupHtml implements PageElement {
    abstract String getDescription()
    abstract String getHref()
    abstract String getImage()
    abstract String getTitle()
    abstract List<DocumentationLink> getLinks()

    @CompileDynamic
    String renderAsHtml(String cssStyle = '') {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: "guidegroup", style: cssStyle) {
            div(class:"guidegroupheader") {
                img src: image, alt: title
                if ( href ) {
                    a(href: href) {
                        h2 title
                    }
                } else {
                    h2 title
                }

            }
			ul {
                if ( description ) {
                    li(class: 'legend', description)
                }
                for (DocumentationLink link : getLinks()) {
                    html.mkp.yieldUnescaped link.renderAsHtml()
                }
            }
        }
        writer.toString()

    }
}
