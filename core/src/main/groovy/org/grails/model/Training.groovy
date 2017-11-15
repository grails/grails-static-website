package org.grails.model

import groovy.transform.CompileDynamic
import groovy.xml.MarkupBuilder

class Training {
    @CompileDynamic
    static String training() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'training', style: 'display: none;') {
            a(href: 'https://objectcomputing.com/training/catalog/grails/') {
                h3 class: 'columnheader', 'Grails Training'
            }
            div(id: 'ocitraining') {
                span ''
            }
        }
        writer.toString()
    }
}
