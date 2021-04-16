package org.grails

import edu.umd.cs.findbugs.annotations.Nullable
import groovy.transform.CompileStatic

@CompileStatic
interface MinutesMetadata {

    String get(String name)

    @Nullable
    String getUrl()

    @Nullable
    String getTitle()

    @Nullable
    String getDate()

    Map<String, String> toMap()

}
