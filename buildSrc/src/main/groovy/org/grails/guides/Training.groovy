package org.grails.guides

import groovy.transform.CompileDynamic
import groovy.xml.MarkupBuilder

class Training {
    @CompileDynamic
    static String training() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'training') {
            h3(class: 'columnheader') {
                a(href: 'https://objectcomputing.com/training/catalog/grails/', 'Grails Training')
            }
            mkp.yieldUnescaped'[%events]'
        }
        writer.toString()
    }
}
