package org.grails

import groovy.transform.CompileStatic

@CompileStatic
class MinutesMetadataAdaptor implements MinutesMetadata {

    Map<String, String> metadata
    MinutesMetadataAdaptor(Map<String, String> metadata) {
        this.metadata = metadata

    }

    @Override
    String get(String name) {
        return metadata[name]
    }

    @Override
    String getUrl() {
        get('url')
    }

    @Override
    String getTitle() {
        get('title')
    }

    @Override
    String getDate() {
        get('date')
    }

    @Override
    Map<String, String> toMap() {
        metadata
    }
}
