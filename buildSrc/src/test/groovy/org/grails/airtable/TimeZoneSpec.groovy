package org.grails.airtable

import spock.lang.Specification

class TimeZoneSpec extends Specification {

    void "TimeZone::toString() returns identifier in regular case"() {
        expect:
        TimeZone.PACIFIC_TAHITI.toString() == 'Pacific/Tahiti'
    }
}
