package org.grails.guides

import groovy.transform.CompileStatic
import org.grails.plugin.Plugin
import org.grails.tags.Tag

@CompileStatic
class TagUtils {
    static Set<Tag> populateTags(List<Guide> guides) {
        Map<String, Integer> tagsMap = [:]
        if ( guides ) {
            for ( Guide guide : guides ) {
                if (guide.tags) {
                    for ( String tag : guide.tags ) {
                        String k = tag.trim().toLowerCase()
                        tagsMap[k] = tagsMap.containsKey(k) ? ( 1 + tagsMap[k] ) : 1
                    }
                }
            }
        }
        populateWithTagsMap(tagsMap)
    }

    static Set<Tag> populateTagsByPlugins(List<Plugin> plugins) {
        Map<String, Integer> tagsMap = [:]
        if (plugins) {
            for (Plugin plugin : plugins) {
                if (plugin.labels) {
                    for (String tag : plugin.labels) {
                        String k = tag.trim().toLowerCase()
                        tagsMap[k] = tagsMap.containsKey(k) ? (1 + tagsMap[k]) : 1
                    }
                }
            }
        }
        populateWithTagsMap(tagsMap)
    }

    private static Set<Tag> populateWithTagsMap(Map<String, Integer> tagsMap) {
        Set<Tag> tags = []
        tagsMap.each { k, v ->
            tags << new Tag(title: k, ocurrence: v)
        }
        tags
    }
}