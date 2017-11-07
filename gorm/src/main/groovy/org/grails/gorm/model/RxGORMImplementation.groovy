package org.grails.gorm.model

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder

@CompileStatic
class RxGORMImplementation extends GormImplementation {

    @CompileDynamic
    @Override
    String renderAsHtml() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: "guidegroup") {
            div(class: "guidegroupheader") {
                img src: "images/${image}", alt: name
                h2 name
            }
            ul {
                li(class: 'legend', description)
                li {
                    a href: hrefDocumentation, 'Documentation'
                }
                li {
                    a href: hrefApi, 'API'
                }
                li {
                    a href: hrefSource, 'Source'
                }
                li {
                    a href: 'http://gorm.grails.org/latest/rx/manual/index.html', 'RxGORM for MongoDB'
                }
                li {
                    a href: 'http://gorm.grails.org/latest/rx/rest-client/manual/index.html', 'RxGORM for REST'
                }
            }
        }
        writer.toString()
    }
}
