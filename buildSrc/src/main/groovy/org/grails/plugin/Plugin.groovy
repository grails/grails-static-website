package org.grails.plugin

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
@CompileStatic
class Plugin {
    String name
    String owner
    String desc
    List<String> labels
    String latestVersion
    String vcsUrl
    String updated
    String license





}
