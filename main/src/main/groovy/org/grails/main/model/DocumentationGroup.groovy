package org.grails.main.model

import groovy.transform.CompileStatic

@CompileStatic
class DocumentationGroup implements DocumentationGroupHtml {
    String image
    String title
    String href
    String description
    List<DocumentationLink> links
}
