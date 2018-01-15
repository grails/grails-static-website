package org.grails.main.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.Navigation
import org.grails.main.SiteMap
import org.grails.main.model.Book
import org.grails.model.GuideGroupItem
import org.grails.model.MenuItem
import org.grails.model.PageElement
import org.grails.model.TextMenuItem
import org.grails.model.Training
import org.grails.pages.Page

@CompileStatic
class LearningPage extends Page {
    String slug = 'learning.html'
    String bodyClass = ''
    String title = 'Learning'

    @Override
    MenuItem menuItem() {
        Navigation.learningMenuItem(grailsUrl())
    }

    @Override
    List<String> getJavascriptFiles() {
        List<String> jsFiles = super.getJavascriptFiles()
        String ociTraining = "${grailsUrl()}/javascripts/${timestamp ? (timestamp + '.') : ''}oci-training.js".toString()
        jsFiles << ociTraining
        jsFiles
    }

    @CompileDynamic
    String blog() {
        String image = "${getImageAssetPreffix()}/documentation.svg"
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: "guidegroup", style: '') {
            div(class: "guidegroupheader") {
                img src: image, alt: title
                h2 'Blog Posts, Step-by-Step Tutorials'
            }
            ul {
                li {
                    a href: guidesUrl(), 'Grails Guides'
                }
                li {
                    a(href: Navigation.blogMenuItem().href, 'Grails Team Blog')
                }
            }
        }
        writer.toString()
    }


    @CompileDynamic
    @Override
    String mainContent() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'content') {
            setOmitEmptyAttributes(true)
            setOmitNullAttributes(true)
            div(class: 'twocolumns') {
                div(class: 'column') {
                    mkp.yieldUnescaped Training.training()
                }
                div(class: 'column') {
                    mkp.yieldUnescaped blog()
                }
            }
            h3 class: "columnheader", 'Grails Books'
            mkp.yieldUnescaped new TwoColumnsPageElement(SiteMap.GRAILS_BOOKS as List<PageElement>).renderAsHtml()
            h3 class: "columnheader", 'Groovy Books'
            mkp.yieldUnescaped new TwoColumnsPageElement(SiteMap.GROOVY_BOOKS as List<PageElement>).renderAsHtml()
        }
        writer.toString()
    }
}
