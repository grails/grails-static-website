package org.grails.model

import groovy.transform.CompileStatic
import groovy.transform.ToString

@ToString(includes = ['title'])
@CompileStatic
class Guide {
    String versionNumber
    List<String> authors
    String category
    String githubSlug
    String name
    String title
    String subtitle
    List<String> tags
    Date publicationDate
}
