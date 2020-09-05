package org.grails.airtable

import spock.lang.Specification

class UserLocaleSpec extends Specification {

    void "UserLocale::toString() returns modifier in lowercase"() {
        expect:
        UserLocale.BS.toString() == 'bs'
    }
}
