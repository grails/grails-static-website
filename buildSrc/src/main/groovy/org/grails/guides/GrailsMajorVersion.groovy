package org.grails.guides

import groovy.transform.CompileStatic

@CompileStatic
enum GrailsMajorVersion {
    GRAILS_3(3),
    GRAILS_4(4),
    GRAILS_5(5),
    GRAILS_6(6),

    final int id

    private GrailsMajorVersion(int id) {
        this.id = id
    }

    @Override
    String toString() {
        this.name()
    }
}