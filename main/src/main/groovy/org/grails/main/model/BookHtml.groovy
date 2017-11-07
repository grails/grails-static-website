package org.grails.main.model

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.model.PageElement

@CompileStatic
trait BookHtml implements PageElement {

    abstract String getImage()
    abstract String getTitle()
    abstract String getHref()
    abstract String getAuthor()
    abstract String getAbout()

    @Override
    @CompileDynamic
    String renderAsHtml() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.figure(class: "book") {
            img src: image, alt: title
            figcaption {
                h3 {
                    a href: href, title
                }
                span class: "author", "By ${author}"
                span class: "about", about
            }
        }
        writer.toString()
    }
}
