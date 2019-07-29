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
class DownloadPage extends Page {
    String slug = 'download.html'
    String bodyClass = ''
    String title = 'Download'

    @Override
    MenuItem menuItem() {
        new TextMenuItem(href: "${grailsUrl()}/download.html", title: 'Download')
    }

    GuideGroup downloadGuideGroup() {
        SoftwareVersion latest = SiteMap.latestVersion()
        String latestVersion = latest.versionText


        new GuideGroup(title: "Download Grails ${latestVersion}",
                image: "${getImageAssetPreffix()}download.svg",
                description: 'Select a profile and set of features tailored to your needs with our application initializer, Grails Application Forge',
                items: [
                        new GuideGroupItem(href: "http://start.grails.org", title: 'Grails Application Forge'),
                        new GuideGroupItem(href: "https://github.com/grails/grails-core/releases/tag/v${latestVersion}", title: 'Release Notes'),
                        new GuideGroupItem(href: "https://github.com/grails/grails-core/releases/download/v${latestVersion}/grails-${latestVersion}.zip", title: 'Binary'),
                        new GuideGroupItem(href: "https://github.com/grails/grails-doc/releases/download/v${latestVersion}/grails-docs-${latestVersion}.zip", title: 'Documentation'),
                ])
    }

    @CompileDynamic
    String olderVersions() {
        List<String> olderVersions = SiteMap.olderVersions()

        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: "transparent_post", style: 'margin-top: 0;') {
            h3 class: "columnheader", 'Older Versions'
            p "You can download previous versions as far back as Grails ${olderVersions.first()}."
            div(class: "versionselector") {
                select(class: "form-control", onchange: "window.location.href='https://github.com/grails/grails-core/releases/download/v'+ this.value +'/grails-' + this.value + '.zip'") {
                    option label: "Select a version", disabled: "disabled", selected: "selected"
                    for (String version : olderVersions) {
                        option version
                    }
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
            div(class: 'twocolumns') {
                div(class: "odd column") {
                    mkp.yieldUnescaped downloadGuideGroup().renderAsHtmlWithStyleAttr('margin-bottom: 0;')
                    p(style: 'margin-top: 10px; margin-bottom: 0;') {
                        mkp.yield 'For historical release notes, refer to '
                        a href: "https://github.com/grails/grails-core/releases", 'Github'
                    }
                    mkp.yieldUnescaped olderVersions()
                }
                div(class: "column") {
                    div(class: 'transparent_post') {
                        p {
                            mkp.yield 'For a quick and effortless start on Mac OSX, Linux, or Cygwin, you can use '
                            a href: "http://sdkman.io", 'SDKMAN! (The Software Development Kit Manager)'
                            mkp.yield ' to download and configure any Grails version of your choice. '
                        }
                    }
                    article(class: "question", style: 'margin-top: 0;margin-bottom: 50px;') {
                        h3(class: 'columnheader', 'Installing with SDKMAN!')
                        p('This tool makes installing Grails on any Unix based platform (Mac OSX, Linux, Cygwin, Solaris, or FreeBSD) easy.')
                        p( 'Simply open a new terminal and enter:')
                        div(class: 'code') {
                            p '$ curl -s https://get.sdkman.io | bash'
                        }
                        p('Follow the on-screen instructions to complete installation.')
                        p( 'Open a new terminal or type the command:')
                        div(class: 'code') {
                            p '$ source "$HOME/.sdkman/bin/sdkman-init.sh"'
                        }
                        p 'Then install the latest stable Grails:'
                        div(class: 'code') {
                            p '$ sdk install grails'
                        }
                        p 'If prompted, make this your default version. After installation is complete it can be tested with:'
                        div(class: 'code') {
                            p '$ grails -version'
                        }
                        p 'That\'s all there is to it!'
                    }
                }
            }
        }
        writer.toString()
    }


}
