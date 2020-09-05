package org.grails.airtable

import spock.lang.Specification

class CellFormatSpec extends Specification {

    void "CellFormat::toString() returns lowercase string"() {
        expect:
        CellFormat.JSON.toString() == 'json'

        and:
        CellFormat.STRING.toString() == 'string'
    }
}
