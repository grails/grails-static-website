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

    GuideGroup snapshotDocumentationGuideGroup() {
        new GuideGroup(title: 'Snapshot Documentation',
                image: "${getImageAssetPreffix()}documentation.svg",
                items: [
                        new GuideGroupItem(href: "http://docs.grails.org/snapshot/guide/single.html", title: 'Single Page - User Guide'),
                        new GuideGroupItem(href: "http://docs.grails.org/snapshot/", title: 'User Guide'),
                ])
    }

    @CompileDynamic
    @Override
    String mainContent() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class:"content") {
            div(class: "twocolumns") {
                div(class: "odd column") {
                    mkp.yieldUnescaped snapshotDocumentationGuideGroup().renderAsHtml()
                    mkp.yieldUnescaped documentationGuideGroup().renderAsHtml()
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
                                for (String version : SiteMap.olderVersions()) {
                                    option version
                                }
                            }
                        }
                        div(class: "versionselector") {
                            h4 'User Guide'
                            select(onchange: "window.location.href='http://grails.org/doc/' + this.value") {
                                option 'Select a version'
                                for (String version : SiteMap.olderVersions()) {
                                    option version
                                }
                            }
                        }
                        div(class: "versionselector") {
                            h4 'API Reference'
                            select(onchange: "window.location.href='http://grails.org/doc/' + this.value + '/api'") {
                                option 'Select a version'
                                for (String version : SiteMap.olderVersions()) {
                                    option version
                                }
                            }
                        }
                    }
                    mkp.yieldUnescaped(SiteMap.DOCUMENTATION_PROFILES.renderAsHtml())

                }
            }
            div(class: "twocolumns") {
                div(class: "odd column") {
                    mkp.yieldUnescaped(SiteMap.DOCUMENTATION_UPGRADE.renderAsHtml('margin-top: 0;'))
                }
                div(class: "column") {
                    mkp.yieldUnescaped(SiteMap.DOCUMENTATION_TESTING.renderAsHtml('margin-top: 0;'))
                }
            }
            div(class: "twocolumns") {
                div(class: "odd column") {
                    mkp.yieldUnescaped(SiteMap.DOCUMENTATION_GORM.renderAsHtml('margin-top: 0;'))
                }
                div(class: "column") {
                    mkp.yieldUnescaped(SiteMap.DOCUMENTATION_VIEWS.renderAsHtml('margin-top: 0;'))
                }
            }
            div(class: "twocolumns") {
                div(class: "odd column") {
                    mkp.yieldUnescaped(SiteMap.DOCUMENTATION_SECURITY.renderAsHtml('margin-top: 0;'))
                }
                div(class: "column") {
                    mkp.yieldUnescaped(SiteMap.DOCUMENTATION_ASYNC.renderAsHtml('margin-top: 0;'))
                    mkp.yieldUnescaped(SiteMap.DOCUMENTATION_DATABASE.renderAsHtml('margin-top: 0;'))
                    mkp.yieldUnescaped(SiteMap.DOCUMENTATION_BUILDSTATUS.renderAsHtml('margin-top: 0;'))
                }
            }
        }
        writer.toString()
    }
}
