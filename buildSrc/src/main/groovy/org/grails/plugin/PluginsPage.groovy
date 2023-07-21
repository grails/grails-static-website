package org.grails.plugin

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder

import java.text.SimpleDateFormat

@CompileStatic
class PluginsPage {

    public static final Integer NUMBER_OF_LATEST_GUIDES = 8
    private static final Integer MARGIN_TOP = 50
    public static final String GUIDES_URL = "https://plugins.grails.org"


    //mainContent(){}
    //rightColumn()
        //genPluginList
            //pluginpage as href

    //leftColumn()
        //genOwnerCloud
            //ownerpage as href
        //genTagCloud
            //tagpage as href


//    @CompileDynamic
//    static String renderGuide(Plugin plugin, String query = null) {
//        StringWriter writer = new StringWriter()
//        MarkupBuilder html = new MarkupBuilder(writer)
//        html.li {
//            //check for instance of just one plugin (for single plugin page)
//            if ( guide instanceof SingleGuide) {
//
//                a class: (guide.owners.contains('quickcast') ? 'quickcast guide' : 'guide'), href: "${GUIDES_URL}/${guide.name}/guide/index.html", guide.title
//                guide.owners.each { String tag ->
//                    span(style: 'display: none', class: 'tag', tag)
//                }
//            } else if (guide instanceof GrailsVersionedGuide) {
//                GrailsVersionedGuide multiGuide = ((GrailsVersionedGuide) guide)
//                div(class: (guide.owners.contains('quickcast') ? 'quickcast multiguide' : 'multiguide')) {
//                    span(class: 'title', guide.title)
//                    for (GrailsMajorVersion grailsVersion :  multiGuide.grailsMayorVersionTags.keySet())  {
//                        Set<String> tagList = multiGuide.grailsMayorVersionTags[grailsVersion] as Set<String>
//                        if (query == null || titlesMatchesQuery(multiGuide.title, query) || tagsMatchQuery(tagList as List<String>, query)) {
//                            div(class: 'align-left') {
//                                final String href = GUIDES_URL + '/' + grailsVersion.name().toLowerCase().replaceAll('_', '') + '/' + multiGuide.githubSlug.replaceAll(/grails-plugins\//, '') + '/guide/index.html'
//                                a(class: 'grailsVersion', href: href) {
//                                    mkp.yield(grailsVersion.toString().replaceAll("_", " "))
//                                }
//                                tagList.each { String tag ->
//                                    span(style: 'display: none', class: 'tag', tag)
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        writer.toString()
//    }


    @CompileDynamic
    static String mainContent(
                              List<Plugin> plugin,
                              Set<Owner> owners = null,
                              Tag tag = null) {


        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'headerbar chalicesbg') {
            div(class: 'content') {
                if (tag || owner) {
                    h1 'Tag or Owner'
//                        a(href: "[%url]/index.html", 'Grails Plugin')
//                        if (tag) {
//                            mkp.yield(" → #${plugin.labels}")
//                        } else if(owner) {
//                            mkp.yield(" → #${plugin.owner.name}")
//                        }


                } else {
                    h1 'Grails Plugins'
                }
            }
        }
        html.div(class: 'content') {
            setOmitEmptyAttributes(true)
            setOmitNullAttributes(true)
            div(class: 'twocolumns') {
                div(class: 'column') {
                    mkp.yieldUnescaped rightColumn(tag, null, plugin)
                }
                div(class: 'column') {
                    //mkp.yieldUnescaped leftColumn(tag, null, owners)
                    if (tag) {
                      //  mkp.yieldUnescaped guideGroupByTag(tag, guides)

                    } else if ( owner ) {
                        //mkp.yieldUnescaped guideGroupByCategory(category, guides.findAll { it.category == category.name }, false )

                    }
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
    static String rightColumn(Tag tag, Owner owner, List<Plugin> plugins) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'latestguides'){
            html.ul {
                //TODO add search box
                if (!(tag || owner)) {
                    for (plugin in plugins) {
                        mkp.yieldUnescaped renderSinglePlugin(plugin)
                    }
                }
            }
        }
        writer.toString()
    }


//    @CompileDynamic
//    static String tagCloud(Set<Tag> tags) {
//        StringWriter writer = new StringWriter()
//        MarkupBuilder html = new MarkupBuilder(writer)
//        html.div(class: 'tagsbytopic') {
//            h3 class: 'columnheader', 'Guides by Tag'
//            ul(class: 'tagcloud') {
//                tags.each { Tag t ->
//                    li(class: "tag") {
//                        a href: "${GUIDES_URL}/owners/${t.label.toLowerCase()}.html", t.label
//                    }
//                }
//            }
//        }
//        writer.toString()
//    }



//    @CompileDynamic
//    static String pluginsByTag(Tag tag, List<Plugin> plugins) {
//        StringWriter writer = new StringWriter()
//        MarkupBuilder html = new MarkupBuilder(writer)
//        html.div(class: "guidegroup") {
//            div(class: "guidegroupheader") {
//                img src: "[%url]/images/documentation.svg" as String, alt: 'Guides'
//                h2 "Guides filtered by #${tag.label}"
//            }
//            ul {
//                List<Plugin> plugin = plugins.findAll { Plugin plugin -> plugin.tags.contains(tag.label) }
//                plugin.each { mkp.yieldUnescaped renderSinglePlugin(it) }
//            }
//        }
//        writer.toString()
//    }

    @CompileDynamic
    static String renderSinglePlugin(Plugin plugin) {
        StringWriter writer = new StringWriter()
        MarkupBuilder mb = new MarkupBuilder(writer)
        mb.li {
            b {
                mkp.yieldUnescaped(plugin.name)
            }
            if (plugin.owner) {
                a(href: "[%url]/plugins/owners/${plugin.owner.name}.html") {
                    mkp.yield(plugin.owner.name)
                }
            }
            if (plugin.latestVersion) {
                span{
                    mkp.yield(plugin.latestVersion)
                }
            }
            if(plugin.labels.size()>0){
                span{
                    for (int i=0;i<plugin.labels.size();i++){
                        a(href: "[%url]/plugins/tags/${plugin.labels.label[i]}.html") {
                            mkp.yield(plugin.labels.label[i] + " ")
                        }
                    }
                }
            }
        }
        writer.toString()

    }

}
