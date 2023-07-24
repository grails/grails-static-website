package org.grails.plugin

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.guides.TagUtils
import org.grails.tags.Tag
import org.grails.tags.TagCloud

import java.sql.Array
import java.time.LocalDate

@CompileStatic
class PluginsPage {

    @CompileDynamic
    static String mainContent(
                              String siteUrl,
                              List<Plugin> plugins,
                                String title) {


        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'headerbar chalicesbg') {
            div(class: 'content') {
                h1 title
            }
        }
        html.div(class: 'content') {
            setOmitEmptyAttributes(true)
            setOmitNullAttributes(true)
            div(class: 'twocolumns') {
                div(class: 'column') {
                    mkp.yieldUnescaped rightColumn(siteUrl, plugins)
                }
                div(class: 'column') {
                    mkp.yieldUnescaped leftColumnMenu(siteUrl)

                    mkp.yieldUnescaped createHeader('Plugins By Tag')
                    Set<Tag> tags = TagUtils.populateTagsByPlugins(plugins)
                    mkp.yieldUnescaped TagCloud.tagCloud(siteUrl + "/plugins/tags", tags, false)

                    mkp.yieldUnescaped createHeader('Latest Plugins')
                    //TODO render Latest Plugins
                    mkp.yieldUnescaped fetchLatestPlugins(plugins)

                    mkp.yieldUnescaped createHeader('Top Rated Plugins')
                    //TODO render TOP Rated

                    mkp.yieldUnescaped createHeader('Plugins By Owner')
                    mkp.yieldUnescaped OwnerUtils.populateOwnersByPlugins(plugins)


                }
            }
        }
        writer.toString()
    }

    @CompileDynamic
    static String leftColumnMenu(String siteUrl){
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
            html.ul(class: 'guidegroup'){
                 mkp.yieldUnescaped(createLink(siteUrl))
            }
        writer.toString()
    }

    @CompileDynamic
    static String leftColumn(Tag tag, Owner owner, Set<Tag> tags) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div {
            if (!( tag || owner )) {
                mkp.yieldUnescaped tagCloud(tags)
            }
        }
        writer.toString()
    }

    @CompileDynamic
    static String rightColumn(String siteUrl,List<Plugin> plugins) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'plugins'){
            html.ul {
                for (plugin in plugins) {
                    mkp.yieldUnescaped renderSinglePlugin(siteUrl, plugin)
                }
            }
        }
        writer.toString()
    }

    @CompileDynamic
    static String renderSinglePlugin(String siteUrl, Plugin plugin) {
        StringWriter writer = new StringWriter()
        MarkupBuilder mb = new MarkupBuilder(writer)
        mb.li(class: 'plugin') {
            if (plugin.vcsUrl) {
                ul(class: 'iconlinks') {
                    a(href: plugin.vcsUrl) {
                        if (plugin.vcsUrl.contains("bintray.com")) {
                            img(src: "${siteUrl}/images/bintray.svg", width: 20)
                        } else if (plugin.vcsUrl.contains("github.com")) {
                            img(src: "${siteUrl}/images/github.svg", width: 20)
                        } else {
                            mkp.yield("Repository")
                        }
                    }
                }
                h3 {
                    mkp.yield(plugin.name)
                }
                if (plugin.desc) {
                    p {
                        mkp.yield(plugin.desc)
                    }
                }
                p {
                    if (plugin.latestVersion) {
                        b {
                            mkp.yield(plugin.latestVersion)
                        }
                    }
                    mkp.yield(plugin.updated) //TODO format this nicely

                    if (plugin.owner) {
                        span {
                            mkp.yield("by")
                        }
                        a(href: "[%url]/plugins/owners/${plugin.owner.name}.html") {
                            mkp.yield(plugin.owner.name)
                        }
                    }
                }
                if (plugin.labels) {
                    ul(class: 'labels') {
                        for (String label : plugin.labels) {
                            li(class: 'label') {
                                a(href: "[%url]/plugins/tags/${label}.html") {
                                    mkp.yield(label)
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
    static String createLink(String siteUrl){
        StringWriter writer = new StringWriter()
        MarkupBuilder mkp = new MarkupBuilder(writer)

        List<String> urlLink = ["https://plugins.grails.org/",
        siteUrl+"/legacy-plugins",
        "https://grails.org/blog/2021-04-07-publish-grails-plugin-to-maven-central.html",
        "https://github.com/grails/grails3-plugins"]

        List<String> title = ["Current Plugins (Grails 3+)",
        "Legacy Plugins (Grails 1 & 2)",
        "Publishing Guide",
        "Portal on Github"]
        for (int i = 0; i<urlLink.size();i++) {
            mkp.li() {
                mkp.a(href: urlLink[i]) {
                    mkp.yield(title[i])
                }
            }
        }
        writer.toString()

    }
    @CompileDynamic
    static String createHeader(String section){
        StringWriter writer = new StringWriter()
        MarkupBuilder mkp = new MarkupBuilder(writer)
        mkp.h3(class: 'columnheader'){
            mkp.yield section
        }
        writer.toString()
    }

    @CompileDynamic
    static String fetchLatestPlugins(List<Plugin> plugins){
        StringWriter writer = new StringWriter()
        MarkupBuilder mkp = new MarkupBuilder(writer)
        String today = LocalDate.now().toString()
        List<Plugin> filteredPlugins = plugins.sort{a,b-> a.updated<=>today}
        List<Plugin> topFive = filteredPlugins.take(5)

        makeHtml(topFive)
    }
    @CompileDynamic
    static String makeHtml(List<Plugin> topFive){
        StringWriter writer = new StringWriter()
        MarkupBuilder mkp = new MarkupBuilder(writer)
        mkp.ul(class: 'latestguides') {
            for (plugin in topFive) {
                li {
                        b plugin.name
                        span plugin.updated
                        a href: plugin.vcsUrl, 'Read More'

                }
            }

        }
        writer.toString()
    }
}
