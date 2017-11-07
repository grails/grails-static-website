package org.grails.model

import groovy.transform.CompileStatic

@CompileStatic
interface PageElement {

    String renderAsHtml()
}
