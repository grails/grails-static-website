package org.grails.guides

import groovy.transform.CompileStatic
import groovy.transform.ToString

@ToString(includes = ['title'])
@CompileStatic
class SingleGuide implements Guide {
    String versionNumber
    List<String> authors
    String category
    String githubSlug
    String githubBranch
    String name
    String title
    String subtitle
    List<String> tags
    Date publicationDate
}
