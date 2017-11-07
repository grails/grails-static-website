package org.grails.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.model.MenuItem

@CompileStatic
class IFramePage extends Page {
    String bodyClass = ''
    String iframeTarget
    String slug
    String title

    IFramePage(String iframeTarget, String slug, String title) {
        this.iframeTarget = iframeTarget
        this.slug = slug
        this.title = title
    }

    @Override
    MenuItem menuItem() {
        return null
    }

    @CompileDynamic
    @Override
    String mainContent() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.iframe(class: 'doc-embed', frameborder: '0', height: '100%', width: '100%', style: 'display: block;padding-bottom: 70px;', src: iframeTarget) {}
        writer.toString()
    }
}
