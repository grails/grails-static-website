package org.grails.main.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.SoftwareVersion
import org.grails.main.SiteMap
import org.grails.model.GuideGroup
import org.grails.model.GuideGroupItem
import org.grails.model.MenuItem
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

    GuideGroup preReleaseDocumentationGuideGroup() {
        SoftwareVersion version = SiteMap.latestPreReleaseVersion()
        if (!version) {
            return null
        }
        new GuideGroup(title: "Milestone Version (${version.versionText}) Documentation",
                image: "${getImageAssetPreffix()}documentation.svg",
                items: [
                        new GuideGroupItem(href: "${docsUrl()}/${version.versionText}/guide/index.html", title: 'User Guide'),
                        new GuideGroupItem(href: "${docsUrl()}/${version.versionText}/api/", title: 'API Reference'),
                ])
    }

    GuideGroup documentationGuideGroup() {
        SoftwareVersion version = SiteMap.latestVersion()
        new GuideGroup(title: "Latest Version (${version.versionText}) Documentation",
        image: "${getImageAssetPreffix()}documentation.svg",
        items: [
                new GuideGroupItem(href: "${docsUrl()}/${version.versionText}/guide/single.html", title: 'Single Page - User Guide'),
                new GuideGroupItem(href: "${docsUrl()}/${version.versionText}/", title: 'User Guide'),
                new GuideGroupItem(href: "${docsUrl()}/${version.versionText}/api/", title: 'API Reference'),
        ])
    }

    GuideGroup snapshotDocumentationGuideGroup() {
        new GuideGroup(title: 'Snapshot Documentation',
                image: "${getImageAssetPreffix()}documentation.svg",
                items: [
                        new GuideGroupItem(href: "${docsUrl()}/snapshot/guide/single.html", title: 'Single Page - User Guide'),
                        new GuideGroupItem(href: "${docsUrl()}/snapshot/", title: 'User Guide'),
                ])
    }

    @CompileDynamic
    @Override
    String mainContent() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)

        GuideGroup milestone = preReleaseDocumentationGuideGroup()

        html.div(class:"content") {
            div(class: "twocolumns") {
                div(class: "odd column") {
                    if (shouldDisplayMilestone()) {
                        mkp.yieldUnescaped milestone.renderAsHtml()
                    } else {
                        mkp.yieldUnescaped snapshotDocumentationGuideGroup().renderAsHtml()
                    }
                }
                div(class: "column") {

                    mkp.yieldUnescaped documentationGuideGroup().renderAsHtml()
                }
            }
            div(class: "twocolumns") {
                div(class: "odd column") {
                    mkp.yieldUnescaped snapshotDocumentationGuideGroup().renderAsHtml()
                }
                div(class: "column") {
                    List<String> olderVersions = SiteMap.olderVersions().reverse()
                    div(class: "olderversions") {
                        h3(class: "columnheader", style: 'margin-bottom: 10px;') {
                            mkp.yieldUnescaped('Older Versions')
                        }
                        p "Browse previous versions' documentation since Grails ${olderVersions.last()}"
                        div(class: "versionselector") {
                            h4 'Single Page - User Guide'
                            select(onchange: "window.location.href='http://grails.org/doc/' + this.value + '/guide/single.html'") {
                                option 'Select a version'
                                for (String version : olderVersions) {
                                    option version
                                }
                            }
                        }
                        div(class: "versionselector") {
                            h4 'User Guide'
                            select(onchange: "window.location.href='http://grails.org/doc/' + this.value") {
                                option 'Select a version'
                                for (String version : olderVersions) {
                                    option version
                                }
                            }
                        }
                        div(class: "versionselector") {
                            h4 'API Reference'
                            select(onchange: "window.location.href='http://grails.org/doc/' + this.value + '/api'") {
                                option 'Select a version'
                                for (String version : olderVersions) {
                                    option version
                                }
                            }
                        }
                    }

                }
            }
            div(class: "twocolumns") {
                div(class: "odd column") {
                    mkp.yieldUnescaped(SiteMap.DOCUMENTATION_PROFILES.renderAsHtml())
                }
                div(class: "column") {
                    mkp.yieldUnescaped(SiteMap.DOCUMENTATION_UPGRADE.renderAsHtml())
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

    private boolean shouldDisplayMilestone() {
        SoftwareVersion milestone = SiteMap.latestPreReleaseVersion()
        SoftwareVersion latest = SiteMap.latestVersion()
        int compare = milestone.compareTo(latest)
        return milestone && compare > 0

    }
}
