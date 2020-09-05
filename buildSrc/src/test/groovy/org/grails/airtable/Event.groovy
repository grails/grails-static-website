package org.grails.airtable

import edu.umd.cs.findbugs.annotations.Nullable
import groovy.transform.CompileStatic
import groovy.transform.ToString

import java.time.LocalDate

@ToString
@CompileStatic
class Event {
    String name
    LocalDate date
    List<String> speakers

    @Nullable
    String link
}
