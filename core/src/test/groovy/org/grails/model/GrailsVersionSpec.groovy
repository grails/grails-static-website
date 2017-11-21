package org.grails.model

import spock.lang.Specification

class GrailsVersionSpec extends Specification {

    void "test compareTo"() {
        expect:
        GrailsVersion.build(greater) > GrailsVersion.build(lesser)

        where:
        greater                 | lesser
        "3.0.0"                 | "2.99.99.BUILD-SNAPSHOT"
        "3.0.0"                 | "2.99.99"
        "3.0.1"                 | "3.0.1.BUILD-SNAPSHOT"
        "3.1.2"                 | "3.1.1"
        "3.2.2"                 | "3.1.2"
        "4.1.1"                 | "3.1.1"
    }
}
