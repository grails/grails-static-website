package org.grails.airtable

import io.micronaut.core.beans.BeanIntrospection
import spock.lang.Specification

class SortSpec extends Specification {

    void "Sort is annotated with @Introspected"() {
        when:
        BeanIntrospection.getIntrospection(Sort)

        then:
        noExceptionThrown()
    }

    void "sort default direction is asc"() {
        expect:
        new Sort().direction == Direction.ASC
    }
}
