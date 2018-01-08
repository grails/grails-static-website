package org.grails.gorm.model

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.model.PageElement

@CompileStatic
trait GormImplementationHtml implements PageElement {

    abstract String getName()
    abstract String getImage()
    abstract String getHrefDocumentation()
    abstract String getHrefApi()
    abstract String getHrefSource()
    abstract String getDescription()

    @CompileDynamic
    @Override
    String renderAsHtml(String styleAttr = null) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: "guidegroup", style: "${styleAttr ?: ''}") {
            div(class: "guidegroupheader") {
                img src: "images/${image}", alt: name
                h2 name
            }
            ul {
                if ( description ) {
                    li(class: 'legend', description)
                }
                li {
                    a href: hrefDocumentation, 'Documentation'
                }
                li {
                    a href: hrefApi, 'API'
                }
                li {
                    a href: hrefSource, 'Source'
                }
            }

        }
        writer.toString()
    }
}
