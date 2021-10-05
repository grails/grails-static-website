package org.grails

import groovy.transform.CompileStatic

@CompileStatic
class Page implements Content {
    String filename
    Map<String, String> metadata
    String content

    @Override
    String getPath() {
        filename?.replaceAll('\\\\', '/')
    }

    @Override
    String getTitle() {
        metadata && metadata.containsKey('title') ? metadata['title'] : null
    }

    @Override
    String getBody() {
        metadata && metadata.containsKey('body') ? metadata['body'] : null
    }

    @Override
    String getDate() {
        metadata && metadata.containsKey('date') ? metadata['date'] : null
    }

    @Override
    String getDescription() {
        metadata && metadata.containsKey('description') ? metadata['description'] : null
    }

    @Override
    List<String> getKeywords() {
        metadata && metadata.containsKey('keywords') ? metadata['keywords'].split(",") as List<String> : null
    }

    @Override
    String getRobots() {
        metadata && metadata.containsKey('robots') ? metadata['robots'] : null
    }
}
