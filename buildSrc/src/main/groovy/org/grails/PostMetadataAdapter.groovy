package org.grails

import groovy.transform.CompileStatic

@CompileStatic
class PostMetadataAdapter implements PostMetadata {

    Map<String, String> metadata
    PostMetadataAdapter(Map<String, String> metadata) {
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
    String getAuthor() {
        get('author')
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
