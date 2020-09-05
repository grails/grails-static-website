package org.grails.airtable

import io.micronaut.core.beans.BeanIntrospection
import spock.lang.Specification

class AirtableErrorSpec extends Specification {
    void "AirtableError is annotated with @Introspected"() {
        when:
        BeanIntrospection.getIntrospection(AirtableError)

        then:
        noExceptionThrown()
    }
}
