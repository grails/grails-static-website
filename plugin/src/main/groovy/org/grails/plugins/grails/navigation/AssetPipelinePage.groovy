package org.grails.plugins.grails.navigation

import groovy.transform.CompileStatic
import org.grails.pages.Page

@CompileStatic
abstract class AssetPipelinePage extends Page {

    @Override
    String renderImage(String image, String alt, String className) {
        "<img src=\"/assets/${image}\" class=\"${className}\" alt=\"${alt}\"/>" as String
    }
}

