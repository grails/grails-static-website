package org.grails.guides

import groovy.transform.CompileStatic

@CompileStatic
class Category {
    String name
    String image

    String getSlug() {
        String slug = name.replaceAll(' ', '')
        slug = slug.replaceAll('\\(', '')
        slug = slug.replaceAll('\\+', '')
        slug = slug.replaceAll('\\)', '')
        URLEncoder.encode(slug, 'UTF-8').toLowerCase()
    }
}
