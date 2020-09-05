package org.grails.guides

import groovy.transform.CompileStatic

@CompileStatic
interface Guide {
    String getVersionNumber()
    List<String> getAuthors()
    String getCategory()
    String getName()
    String getTitle()
    String getSubtitle()
    List<String> getTags()
    Date getPublicationDate()
}