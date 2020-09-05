package org.grails.airtable

import spock.lang.Specification

class DirectionSpec extends Specification {

    void "Direction::toString() returns lowercase string"() {
        expect:
        Direction.ASC.toString() == 'asc'

        and:
        Direction.DESC.toString() == 'desc'
    }
}
