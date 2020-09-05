package org.grails

import edu.umd.cs.findbugs.annotations.Nullable

interface PostMetadata {

    String get(String name)

    @Nullable
    String getUrl()

    @Nullable
    String getTitle()

    @Nullable
    String getAuthor()

    @Nullable
    String getDate()

    Map<String, String> toMap()
}