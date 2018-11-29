package org.grails.main.model

import groovy.transform.CompileStatic

@CompileStatic
class BuildStatus implements BuildStatusHtml {
    String title
    String href
    String badge
    String version
}
