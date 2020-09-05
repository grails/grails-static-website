package org.grails.airtable

import io.micronaut.core.beans.BeanIntrospection
import spock.lang.Specification

class AirtableApiErrorSpec extends Specification {

    void "AirtableApiError is annotated with @Introspected"() {
        when:
        BeanIntrospection.getIntrospection(AirtableApiError)

        then:
        noExceptionThrown()
    }
}
