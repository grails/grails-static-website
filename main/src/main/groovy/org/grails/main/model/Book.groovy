package org.grails.main.model

import groovy.transform.CompileStatic

@CompileStatic
class Book implements BookHtml {
    String title
    String about
    String href
    String image
    String author
}
