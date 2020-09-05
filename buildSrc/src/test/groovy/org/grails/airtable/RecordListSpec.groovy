package org.grails.airtable

import io.micronaut.core.beans.BeanIntrospection
import spock.lang.Specification

class RecordListSpec extends Specification {
    void "RecordList is annotated with @Introspected"() {
        when:
        BeanIntrospection.getIntrospection(RecordList)

        then:
        noExceptionThrown()
    }
}
