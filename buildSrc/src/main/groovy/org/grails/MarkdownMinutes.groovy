package org.grails

import groovy.transform.CompileStatic


@CompileStatic
class MarkdownMinutes extends Page {

    @Override
    String getPath() {
        return filename.replaceAll(".md", ".html").replaceAll(".markdown", ".html")
    }
}
