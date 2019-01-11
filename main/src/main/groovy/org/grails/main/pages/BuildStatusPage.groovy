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

    List<String> profileBranches = ['master', '3.3.x', '3.3.3']
    List<String> profiles = [
            'rest-api-plugin',
            'web-plugin',
            'plugin',
            'web',
            'web-jboss7',
            'rest-api',
            'react',
            'vue',
            'angular',
    ]

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
    String renderBuildStatusListBranchesAsTable(List<List<BuildStatus>> buildStatusList,
                                                List<String> branches,
                                        boolean useVersionColumn = false) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.table {
            thead {
                tr {
                    if (useVersionColumn) {
                        th 'Version'
                    }
                    th 'Build name'
                    for (String branch : branches) {
                        th branch
                    }

                }
            }
            tbody {
                for (List<BuildStatus> l : buildStatusList) {
                    tr {
                        td l.first().title
                        for ( BuildStatus buildStatus : l) {
                            td {
                                a(href: buildStatus.href) {
                                    img src: buildStatus.badge
                                }
                            }
                        }
                    }
                }
            }
        }
        writer.toString()
    }

    @CompileDynamic
    String renderBuildStatusListAsTable(List<BuildStatus> buildStatusList,
                                        boolean useVersionColumn = false,
                                        String title = 'Version') {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.table {
            thead {
                tr {
                    if (useVersionColumn) {
                        th title
                    }
                    th 'Build name'
                    th 'Status'
                }
            }
            tbody {
                for ( BuildStatus buildStatus : buildStatusList ) {
                    mkp.yieldUnescaped buildStatus.renderAsHtml(useVersionColumn)

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
            article(class: 'post') {
                p {
                    mkp.yield 'Our'
                    a href: 'http://travis-ci.org/grails/grails-core', 'continuous integration server'
                    mkp.yield ' runs on '
                    a href: 'http://www.travis-ci.org/', 'Travis CI'
                    mkp.yield ', and builds Grails and Grails plugins.'
                }
                h3(class: 'columnheader', 'Grails Profiles Tests')
                mkp.yieldUnescaped renderBuildStatusListBranchesAsTable(profiles.collect { String profileName ->
                    String githubSlug = 'grails-profiles-tests'
                    profileBranches.collect { branch ->
                        new BuildStatus([title: profileName,
                                         href : "https://travis-ci.org/${githubSlug}/${profileName}?branch=${branch}",
                                         badge: "https://travis-ci.org/${githubSlug}/${profileName}.svg?branch=${branch}"])
                    } as List<BuildStatus>
                } as List<List<BuildStatus>>, profileBranches)

                h3(class: 'columnheader', 'Grails Build Status')

                mkp.yieldUnescaped repositoriesStatus('grails', ['grails3-functional-tests'], ['master', '3.3.x', '3.2.x'])

                mkp.yieldUnescaped repositoriesStatus('grails', ['grails-core'], ['master',
                                                               '3.3.x',
                                                               '3.2.x',
                                                               '3.1.x',
                                                               '3.0.x',
                                                               '2.5.x',
                                                               '2.4.x',
                                                               '2.3.x',])

                mkp.yieldUnescaped repositoriesStatus('grails', ['grails-gsp', 'grails-testing-support', 'grails-data-mapping', 'gorm-hibernate5', 'gorm-mongodb', 'grails-async', 'gorm-graphql', 'gorm-neo4j', 'scaffolding', 'grails-views'], ['master', '3.3.x'])

                mkp.yieldUnescaped repositoriesStatus('grails-fields-plugin', ['grails-fields'], ['master', '3.3.x'])
                
                h3(class: 'columnheader', 'GORM')
                mkp.yieldUnescaped renderBuildStatusListAsTable([
                        [title: 'GORM Master', branch: 'master', githubSlug: 'grails/grails-data-mapping'],
                        [title: 'GORM GraphQL', branch: 'master', githubSlug: 'grails/gorm-graphql']
                ].collect { Map guide ->
                    new BuildStatus([title: guide.title,
                                     href : "https://travis-ci.org/${guide.githubSlug}?branch=${guide.branch}",
                                     badge: "https://travis-ci.org/${guide.githubSlug}.svg?branch=${guide.branch}"])
                } as List<BuildStatus>)


                h3(class: 'columnheader', 'Grails Guides Build Status')
                div(class: 'twocolumns') {

                    for ( def l : guides.collate((int) (guides.size() / 2)) ) {
                        div(class: 'column') {

                        List<BuildStatus> guideBuildStatuses = l.collect { Guide guide ->
                            new BuildStatus([
                                    title: guide.title,
                                    href : "https://travis-ci.org/${guide.githubSlug}?branch=master",
                                    badge: "https://travis-ci.org/${guide.githubSlug}.svg?branch=master",
                                    version: guide.versionNumber,
                            ])
                        }
                        mkp.yieldUnescaped renderBuildStatusListAsTable(guideBuildStatuses, true, "Grails Version")
                        }
                    }
                }
            }

        }
        writer.toString()
    }

    private String repositoriesStatus(String githubOrg, List<String> githubRepo, List<String> branches) {
        renderBuildStatusListBranchesAsTable(githubRepo.collect { String name ->
            String githubSlug = githubOrg
            branches.collect { branch ->
                new BuildStatus([title: name,
                                 href : "https://travis-ci.org/${githubSlug}/${name}?branch=${branch}",
                                 badge: "https://travis-ci.org/${githubSlug}/${name}.svg?branch=${branch}"])
            } as List<BuildStatus>
        } as List<List<BuildStatus>>, branches)
    }
}
