package org.grails.main.model

import groovy.transform.CompileStatic

@CompileStatic
class Profile implements ProfileHtml {
    String title
    String href
    String docsHref

    String getSlug() {
        title?.replace(' ', '-')?.toLowerCase()
    }

    String getHref() {
        "profiles/${slug}/index.html"
    }
}
