package org.grails.main.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.main.SiteMap
import org.grails.model.GuideGroup
import org.grails.model.GuideGroupItem
import org.grails.model.MenuItem
import org.grails.model.PageElement
import org.grails.model.TextMenuItem
import org.grails.pages.Page

@CompileStatic
class DocumentationPage extends Page {
    String title = 'Documentation'
    String slug = 'documentation.html'
    String bodyClass = ''

    @Override
    MenuItem menuItem() {
        new TextMenuItem(href: "${grailsUrl()}/documentation.html", title: 'Documentation')
    }

    GuideGroup documentationGuideGroup() {
        new GuideGroup(title: 'Latest Version Documentation',
        image: "${getImageAssetPreffix()}documentation.svg",
        items: [
                new GuideGroupItem(href: "http://docs.grails.org/latest/guide/single.html", title: 'Single Page - User Guide'),
                new GuideGroupItem(href: "http://docs.grails.org/latest/", title: 'User Guide'),
                new GuideGroupItem(href: "http://docs.grails.org/latest/api/", title: 'API Reference'),
        ])
    }

    @CompileDynamic
    @Override
    String mainContent() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class:"content") {
            div(class: "twocolumns") {
                TwoColumnsPageElement twoColumnsPageElement = new TwoColumnsPageElement(SiteMap.DOCUMENTATION)
                div(class: "odd column") {
                    mkp.yieldUnescaped documentationGuideGroup().renderAsHtml()
                    for (PageElement el : twoColumnsPageElement.firstColumn) {
                        mkp.yieldUnescaped el.renderAsHtml()
                    }
                }
                div(class: "column") {
                    div(class: "olderversions") {
                        h3(class: "columnheader", style: 'margin-bottom: 10px;') {
                          mkp.yieldUnescaped('Older Versions')
                        }
                        p 'Browse previous versions\' documentation since Grails 1.2.0'
                        div(class: "versionselector") {
                            h4 'Single Page - User Guide'
                            select(onchange: "window.location.href='http://grails.org/doc/' + this.value + '/guide/single.html'") {
                                option 'Select a version'
                                for (String version : SiteMap.OLDER_VERSIONS) {
                                    option version
                                }
                            }
                        }
                        div(class: "versionselector") {
                            h4 'User Guide'
                            select(onchange: "window.location.href='http://grails.org/doc/' + this.value") {
                                option 'Select a version'
                                for (String version : SiteMap.OLDER_VERSIONS) {
                                    option version
                                }
                            }
                        }
                        div(class: "versionselector") {
                            h4 'API Reference'
                            select(onchange: "window.location.href='http://grails.org/doc/' + this.value + '/api'") {
                                option 'Select a version'
                                for (String version : SiteMap.OLDER_VERSIONS) {
                                    option version
                                }
                            }
                        }
                    }

                    for (PageElement el : twoColumnsPageElement.secondColumn) {
                        mkp.yieldUnescaped el.renderAsHtml()
                    }
                }
            }
        }

        writer.toString()
    }
}
