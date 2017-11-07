package org.grails.guides

import groovy.transform.CompileStatic
import org.grails.model.Guide
import org.grails.guides.model.Tag

@CompileStatic
class TagUtils {

    static Set<Tag> populateTags(List<Guide> guides) {
        Map<String, Integer> tagsMap = [:]
        for ( Guide guide : guides ) {
            if ( guides.tags ) {
                for ( String tag : guide.tags ) {
                    String k = tag.trim().toLowerCase()
                    tagsMap[k] = tagsMap.containsKey(k) ? ( 1 + tagsMap[k] ) : 1
                }
            }
        }
        Set<Tag> tags = []
        tagsMap.each { k, v ->
            tags << new Tag(title: k, ocurrence: v)
        }
        tags
    }

}
