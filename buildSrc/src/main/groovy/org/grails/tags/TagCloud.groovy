package org.grails.tags

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder

@CompileStatic
class TagCloud {

    @CompileDynamic
    static String tagCloud(String url, Set<Tag> tags, boolean includeHeader = true) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'tagsbytopic') {
            if (includeHeader) {
                h3 class: 'columnheader', 'Guides by Tag'
            }
            ul(class: 'tagcloud') {
                tags.sort { Tag a, Tag b -> a.slug <=> b.slug }.each { Tag t ->
                    li(class: "tag${t.ocurrence}") {
                        a href: "${url}/${t.slug.toLowerCase()}.html", t.title
                    }
                }
            }
        }
        writer.toString()
    }
}
