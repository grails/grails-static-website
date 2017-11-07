package org.grails.main.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.main.SiteMap
import org.grails.main.model.BuildStatus
import org.grails.model.Guide
import org.grails.model.MenuItem
import org.grails.model.TextMenuItem
import org.grails.pages.Page

@CompileStatic
class BuildStatusPage extends Page {

    String bodyClass = ''
    String title = 'Build Status'
    String slug = 'buildstatus.html'

    List<Guide> guides

    BuildStatusPage(List<Guide> guides) {
        this.guides = guides
    }

    @Override
    MenuItem menuItem() {
        new TextMenuItem(href: "${grailsUrl()}/buildstatus.html", title: 'Build Status')
    }

    @CompileDynamic
    String renderBuildStatusListAsTable(List<BuildStatus> buildStatusList) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.table {
            thead {
                tr {
                    th 'Build name'
                    th 'Status'
                }
            }
            tbody {
                for ( BuildStatus buildStatus : buildStatusList ) {
                    mkp.yieldUnescaped buildStatus.renderAsHtml()
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
        List<BuildStatus> buildStatusList = SiteMap.BUILDS
        html.div(class: 'content') {
            article(class: 'post') {
                div(class: 'twocolumns') {
                    div(class: 'column') {
                        section {
                            p {
                                mkp.yield 'Our'
                                a href: 'http://travis-ci.org/grails/grails-core', 'continuous integration server'
                                mkp.yield ' runs on '
                                a href: 'http://www.travis-ci.org/', 'Travis CI'
                                mkp.yield ', and builds Grails and Grails plugins.'
                            }
                            mkp.yieldUnescaped renderBuildStatusListAsTable(buildStatusList)
                        }
                        section {
                            h3(class: 'columnheader', 'GORM')
                            mkp.yieldUnescaped renderBuildStatusListAsTable([
                                    [title: 'GORM Master', branch: 'master', githubSlug: 'grails/grails-data-mapping'],
                                    [title: 'GORM GraphQL', branch: 'master', githubSlug: 'grails/gorm-graphql']
                            ].collect { Map guide ->
                                new BuildStatus([title: guide.title,
                                                 href : "https://travis-ci.org/${guide.githubSlug}?branch=${guide.branch}",
                                                 badge: "https://travis-ci.org/${guide.githubSlug}.svg?branch=${guide.branch}"])
                            })
                        }
                    }
                    div(class: 'column') {
                        h3(class: 'columnheader', 'Grails Guides Build Status')
                        List<BuildStatus> guideBuildStatuses = guides.collect { Guide guide ->
                            new BuildStatus([title: guide.title,
                                             href : "https://travis-ci.org/${guide.githubSlug}?branch=master",
                                             badge: "https://travis-ci.org/${guide.githubSlug}.svg?branch=master"])
                        }
                        mkp.yieldUnescaped renderBuildStatusListAsTable(guideBuildStatuses)
                    }
                }



            }

        }
        writer.toString()
    }
}
