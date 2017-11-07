package org.grails.guides.single

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.ReadFileUtils
import org.grails.model.TextMenuItem
import org.grails.pages.Page

@CompileStatic
class GuidePage extends Page implements ReadFileUtils {

    @Override
    TextMenuItem menuItem() {
        null
    }

    @Override
    String getHtmlHeadTitle() {
        '${title} | Grails Guides | Grails Framework'
    }

    @Override
    String getMetaDescription() {
        '${title}. ${subtitle}'
    }

    @Override
    List<String> getCssFiles() {
        ['${resourcesPath}/css/main.css','${resourcesPath}/css/screen.css']
    }

    @Override
    String getImageAssetPreffix() {
        '${resourcesPath}/img/'
    }

    @Override
    List<String> getJavascriptFiles() {
        ['https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/1.7.1/clipboard.min.js']
    }

    @CompileDynamic
    @Override
    String mainContent() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'fullwidth') {
            String text = readFileContent('guide.html')
            if (text) {
                mkp.yieldUnescaped text
            }
        }
        writer.toString()
    }

    @Override
    String getSlug() {
        'layout.html'
    }

    @Override
    String getBodyClass() {
        'guide'
    }

    @Override
    String getTitle() {
        null
    }
}