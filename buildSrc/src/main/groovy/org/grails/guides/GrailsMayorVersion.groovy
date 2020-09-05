package org.grails.guides

import groovy.transform.CompileStatic

@CompileStatic
enum GrailsMayorVersion {
    GRAILS_3(3),
    GRAILS_4(4)

    final int id

    private GrailsMayorVersion(int id) {
        this.id = id
    }

    @Override
    String toString() {
        this.name()
    }
}