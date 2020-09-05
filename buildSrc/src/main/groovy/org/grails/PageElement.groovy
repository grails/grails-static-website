package org.grails

import groovy.transform.CompileStatic

@CompileStatic
interface PageElement {

    String renderAsHtml()
}
