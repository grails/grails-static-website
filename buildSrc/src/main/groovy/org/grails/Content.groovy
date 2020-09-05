package org.grails

import edu.umd.cs.findbugs.annotations.Nullable

interface Content {
    @Nullable String getPath();
    @Nullable String getTitle();
    /**
     *
     * @return Body class
     */
    @Nullable String getBody();
    @Nullable String getDate();
    @Nullable String getDescription();
    @Nullable List<String> getKeywords();
    @Nullable String getRobots();
}
