package org.grails

import groovy.json.JsonSlurper
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.grails.model.Guide

import java.text.DateFormat
import java.text.SimpleDateFormat

@CompileStatic
class GuidesFetcher {

    public static final String GUIDES_JSON = 'https://raw.githubusercontent.com/grails/grails-guides/gh-pages/guides.json'
    private static final DateFormat dateFormat = new SimpleDateFormat('dd MMM yyyy', Locale.US)
    @CompileDynamic
    static List<Guide> fetchGuides() {
        URL url = new URL(GUIDES_JSON)
        def jsonArr = new JsonSlurper().parseText(url.text)
        List<Guide> guides = jsonArr.collect {
            Guide guide = new Guide(authors: it.authors as List,
                    category: it.category,
                    githubSlug: it.githubSlug,
                    name: it.name,
                    title: it.title,
                    subtitle: it.subtitle,
                    tags: it.tags as List
            )
            if ( it.publicationDate ) {
                guide.publicationDate = dateFormat.parse(it.publicationDate as String)
            }
            guide
        }
        guides.sort { Guide a, Guide b ->
            b.publicationDate <=> a.publicationDate
        }
    }
}
