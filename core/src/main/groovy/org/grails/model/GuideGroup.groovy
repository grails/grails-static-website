package org.grails.model

import groovy.transform.CompileStatic

@CompileStatic
class GuideGroup implements GuideGroupHtml {
    String title
    String image
    String description
    List<GuideGroupItem> items = []
}
