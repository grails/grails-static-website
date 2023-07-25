package org.grails.plugin

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.gradle.PluginsTask
import org.grails.guides.TagUtils
import org.grails.tags.Tag
import org.grails.tags.TagCloud

import java.time.LocalDateTime


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
                    mkp.yieldUnescaped fetchLatestPlugins(plugins)

                    mkp.yieldUnescaped createHeader('Top Rated Plugins')
                    mkp.yieldUnescaped fetchTopRatedPlugins(siteUrl,plugins)

                    mkp.yieldUnescaped createHeader('Plugins By Owner')
                    mkp.yieldUnescaped populateOwnersByPlugins(siteUrl,plugins)


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
                    mkp.yield(" published ")
                    mkp.yield(PluginsTask.formatUpdatedDate(plugin.updated))

                    if (plugin.owner) {
                        span {
                            mkp.yield("by ")
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

        List<String> urlLink = [
        siteUrl+"/legacy-plugins",
        "https://grails.org/blog/2021-04-07-publish-grails-plugin-to-maven-central.html",
        "https://github.com/grails/grails3-plugins"]

        List<String> title = [
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

        //TODO why is this sort method not working?
        List<Plugin> filteredPlugins = plugins.sort{a,b-> a.updated<=> LocalDateTime.now()}
        List<Plugin> topFive = filteredPlugins.take(5)

        renderLatestPlugins(topFive)
    }
    @CompileDynamic
    static String renderLatestPlugins(List<Plugin> topFive){
        StringWriter writer = new StringWriter()
        MarkupBuilder mkp = new MarkupBuilder(writer)

        mkp.ul(class: 'latestguides') {
            for (plugin in topFive) {

                li {
                        b plugin.name
                        span PluginsTask.formatUpdatedDate(plugin.updated)
                        a href: plugin.vcsUrl, 'Read More'

                }
            }

        }
        writer.toString()
    }

    @CompileDynamic
    static String populateOwnersByPlugins(String siteUrl, List<Plugin> plugins){
        //TODO Owner cloud is created by ocurance. Occurance will need to be added to Owner pojo
        StringWriter writer = new StringWriter()
        MarkupBuilder mkp = new MarkupBuilder(writer)
        Set <Owner> owners =PluginsTask.getOwners(plugins)
        mkp.ul(class: 'tagsbytopic') {
            for (owner in owners) {
                li(class: 'tag1'){
                    a href: "[%url]/plugins/owners/${owner.name}.html", owner.name
                }
            }
        }
        writer.toString()

    }

    @CompileDynamic
    static String fetchTopRatedPlugins(String siteUrl, List<Plugin> plugins){
        //TODO github API fetch highest ratings for all plugins and sort top 5
        StringWriter writer = new StringWriter()
        MarkupBuilder mkp = new MarkupBuilder(writer)
        List<String> ratings =["250", "213", "213", "213", "202"]
        List<String> title = [
                "Spring Security Core", "Hibernate", "Hibernate3", "Hibernate5", "Spring-Security-Rest"]
        mkp.div(class: 'latestguides') {
            mkp.ul(class: 'latestguides') {
                for (int i = 0; i < title.size(); i++) {
                    li {
                        b {
                            mkp.yield title[i],false
                        }
                        span {
                            img(src: "${siteUrl}/images/small_githubstar.svg", width: 52){}
                                mkp.yield ratings[i], false
                        }
                        //a href: "", "Read More"
                    }
                }
            }
        }
        writer.toString()
    }
}
