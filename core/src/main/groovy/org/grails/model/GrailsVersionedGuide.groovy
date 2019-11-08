package org.grails.model

import groovy.transform.CompileStatic

@CompileStatic
class GrailsVersionedGuide implements Guide {
    List<String> authors
    String category
    Map<GrailsMayorVersion, List<String>> grailsMayorVersionTags = [:]
    String name
    String title
    String subtitle
    Date publicationDate
    String versionNumber
    String githubSlug
    String githubBranch

    @Override
    List<String> getTags() {
        List<List<String>> tags = grailsMayorVersionTags.collect {
            GrailsMayorVersion k, List<String> v ->
                v
        }
        tags << grailsMayorVersionTags.collect {
            GrailsMayorVersion k, List<String> v ->
                k.toString().toLowerCase()
        } as List<String>
        tags.flatten() as List<String>
    }
}
