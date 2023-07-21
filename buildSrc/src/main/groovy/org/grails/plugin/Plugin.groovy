package org.grails.plugin

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
@CompileStatic
class Plugin {
    String name
    Owner owner
    String desc
    List<Tag> labels
    String latestVersion
    String vcsUrl
    String updated
    String license





}
