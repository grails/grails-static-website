package org.grails.plugin

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.guides.TagUtils
import org.grails.tags.Tag
import org.grails.tags.TagCloud

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
                    //TODO Legacy Plugins
                    //TODO Plublishing Guide
                    //TODO Portal on github linking to https://github.com/grails/grails-plugins-metadata
                    
                    //TODO add Header for Plugins Tag Cloud
                    Set<Tag> tags = TagUtils.populateTagsByPlugins(plugins)
                    mkp.yieldUnescaped TagCloud.tagCloud(siteUrl + "/plugins/tags", tags, false)

                    //TODO add Header for Latest Plugins
                    //TODO render Latest Plugins

                    //TODO add Header for TOP Rated Plugins
                    //TODO render TOP Rated

                    //TODO add Header for Plugins by Owner Tag Cloud
                    //TODO render Owner Tag Cloud


                }
            }
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
}
