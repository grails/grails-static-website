package org.grails.guides

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.ReadFileUtils
import org.grails.tags.Tag

import java.text.SimpleDateFormat

@CompileStatic
class GuidesPage {

    public static final Integer NUMBER_OF_LATEST_GUIDES = 5
    private static final Integer MARGIN_TOP = 50
    public static final String GUIDES_URL = "https://guides.grails.org"

    @CompileDynamic
    static String renderGuide(Guide guide, String query = null) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.li {
            if ( guide instanceof SingleGuide) {

                a class: (guide.tags.contains('quickcast') ? 'quickcast guide' : 'guide'), href: "${GUIDES_URL}/${guide.name}/guide/index.html", guide.title
                guide.tags.each { String tag ->
                    span(style: 'display: none', class: 'tag', tag)
                }
            } else if (guide instanceof GrailsVersionedGuide) {
                GrailsVersionedGuide multiGuide = ((GrailsVersionedGuide) guide)
                div(class: (guide.tags.contains('quickcast') ? 'quickcast multiguide' : 'multiguide')) {
                    span(class: 'title', guide.title)
                    for (GrailsMayorVersion grailsVersion :  multiGuide.grailsMayorVersionTags.keySet())  {
                        Set<String> tagList = multiGuide.grailsMayorVersionTags[grailsVersion] as Set<String>
                        if (query == null || titlesMatchesQuery(multiGuide.title, query) || tagsMatchQuery(tagList as List<String>, query)) {
                            div(class: 'align-left') {
                                String href = "${GUIDES_URL}${grailsVersion == GrailsMayorVersion.GRAILS_3 ? '/grails3' : ''}/${multiGuide.githubSlug.replaceAll('grails-guides/', '')}/guide/index.html"
                                a(class: 'grailsVersion', href: href) {
                                    mkp.yield(grailsVersion.toString().replaceAll("_", " "))
                                }
                                tagList.each { String tag ->
                                    span(style: 'display: none', class: 'tag', tag)
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
    static String mainContent(ClassLoader classLoader,
                              List<Guide> guides,
                              Set<Tag> tags,
                              Category category = null,
                              Tag tag = null) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div( class:'headerbar chalicesbg') {
            div( class:'content') {
                if (tag || category) {
                    h1 {
                        a(href: "[%url]/index.html", 'Guides')
                        if (tag) {
                            mkp.yield(" → #${tag.title}")
                        } else if(category) {
                            mkp.yield(" → ${category.name}")
                        }
                    }
                } else {
                    h1 'Guides'
                }
            }
        }
        html.div(class: 'content') {
            setOmitEmptyAttributes(true)
            setOmitNullAttributes(true)
            div(class: 'twocolumns') {
                div(class: 'column') {
                    mkp.yieldUnescaped rightColumn(tag, category, tags)
                }
                div(class: 'column') {
                    mkp.yieldUnescaped leftColumn(tag, category, guides)
                    if ( tag ) {
                        mkp.yieldUnescaped guideGroupByTag(tag, guides)

                    } else if ( category ) {
                        mkp.yieldUnescaped guideGroupByCategory(category, guides.findAll { it.category == category.name }, false )

                    } else {
                        div(id: 'searchresults') {
                            mkp.yieldUnescaped('')
                        }
                        mkp.yieldUnescaped guideGroupByCategory(categories().apprentice, guides)
                    }
                }
            }
            div(class: 'twocolumns') {
                div(class: 'column') {
                    if ( !(tag || category) ) {
                        mkp.yieldUnescaped guideGroupByCategory(categories().advanced, guides, true, 'margin-top: 0;')
                    }
                }
                div(class: 'column') {
                    if ( !(tag || category) ) {
                        mkp.yieldUnescaped guideGroupByCategory(categories().gorm, guides, true, 'margin-top: 0;')
                        mkp.yieldUnescaped guideGroupByCategory(categories().testing, guides)

                    }
                }
            }
            div(class: 'twocolumns') {
                div(class: 'column') {
                    if ( !(tag || category) ) {
                        mkp.yieldUnescaped guideGroupByCategory(categories().devops, guides)
                        mkp.yieldUnescaped guideGroupByCategory(categories().async, guides, true, 'margin-top: 0;')
                        mkp.yieldUnescaped guideGroupByCategory(categories().googlecloud, guides)
                        mkp.yieldUnescaped guideGroupByCategory(categories().ios, guides)
                        mkp.yieldUnescaped guideGroupByCategory(categories().android, guides)
                        mkp.yieldUnescaped guideGroupByCategory(categories().ria, guides)
                    }
                }
                div(class: 'column') {
                    if ( !(tag || category) ) {
                        mkp.yieldUnescaped guideGroupByCategory(categories().vue, guides)
                        mkp.yieldUnescaped guideGroupByCategory(categories().angular, guides, true, 'margin-top: 0;')
                        mkp.yieldUnescaped guideGroupByCategory(categories().angularjs, guides)
                        mkp.yieldUnescaped guideGroupByCategory(categories().react, guides)
                        mkp.yieldUnescaped guideSuggestion(classLoader)
                    }
                }
            }
        }
        writer.toString()
    }

    @CompileDynamic
    static String guideSuggestion(ClassLoader classLoader) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'guidesuggestion') {
            h3 class: 'columnheader', 'Which topic would you like us to cover?'
            String formHtml = ReadFileUtils.readFileContent(classLoader, 'guidesuggestionform.html')
            if ( formHtml ) {
                mkp.yieldUnescaped formHtml
            }
        }
        writer.toString()
    }

    @CompileDynamic
    static String leftColumn(Tag tag, Category category, List<Guide> guides) {
        if ( tag || category ) {
            return ''
        }
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div {
            mkp.yieldUnescaped latestGuides(guides)
        }
        writer.toString()
    }
    @CompileDynamic
    static String sponsoredBy() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'sponsoredby') {
            h4 'Sponsored by'
            a(href: 'https://objectcomputing.com/products/grails/') {
                img src: "[%url]/images/oci_home_to_grails.svg", alt: 'Object Computing'
            }
        }
        writer.toString()
    }
    @CompileDynamic
    static String latestGuides(List<Guide> guides) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'latestguides') {
            h3 class: 'columnheader', 'Latest Guides'
            ul {
                List<Guide> latestGuides = guides.findAll {
                    it.publicationDate
                }.sort { Guide a, Guide b ->
                    b.publicationDate <=> a.publicationDate
                }.take(NUMBER_OF_LATEST_GUIDES)
                latestGuides.each { Guide guide ->
                    li {
                        b guide.title
                        span {

                            mkp.yield new SimpleDateFormat('MMM dd, yyyy').format(guide.publicationDate)
                            mkp.yield ' - '
                            mkp.yield guide.category
                        }
                        a href: "${GUIDES_URL}/${guide.name}/guide/index.html", 'Read More'
                    }
                }
            }
        }
        writer.toString()
    }

    @CompileDynamic
    static String tagCloud(Set<Tag> tags) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'tagsbytopic') {
            h3 class: 'columnheader', 'Guides by Tag'
            ul(class: 'tagcloud') {
                tags.sort { Tag a, Tag b -> a.slug <=> b.slug }.each { Tag t ->
                    li(class: "tag${t.ocurrence}") {
                        a href: "[%url]/tags/${t.slug.toLowerCase()}.html", t.title
                    }
                }
            }
        }
        writer.toString()
    }

    @CompileDynamic
    static String rightColumn(Tag tag, Category category, Set<Tag> tags) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div {
            mkp.yieldUnescaped searchBox(tag, category)
            if ( tag || category ) {
                mkp.yieldUnescaped sponsoredBy()
            } else {
                mkp.yieldUnescaped Training.training()
                mkp.yieldUnescaped sponsoredBy()
                mkp.yieldUnescaped tagCloud(tags)
            }
        }
        writer.toString()
    }

    @CompileDynamic
    static String searchBox(Tag tag, Category category) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        if ( !(tag || category) ) {
            html.div(class: 'searchbox', style: 'margin-top: 50px !important;') {
                div(class: 'search', style: 'margin-bottom: 0px !important;') {
                    input(type: 'text', id: 'query', placeholder: 'SEARCH')
                    div(id: 'noresults') {
                        mkp.yieldUnescaped '&nbsp;'
                    }
                }
            }
        }
        writer.toString()
    }

    @CompileDynamic
    static String guideGroupByCategory(Category category,
                                       List<Guide> guides,
                                       boolean linkToCategory = true,
                                       String cssStyle = '') {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: "guidegroup", style: cssStyle) {
            div(class: "guidegroupheader") {
                img src: "[%url]/images/${category.image}" as String, alt: category.name
                if ( linkToCategory )  {
                    a(href: "[%url]/categories/${category.slug}.html") {
                        h2 category.name
                    }
                } else {
                    h2 category.name
                }
            }
            ul {
                List<Guide> categoryGuides = guides.findAll { it.category == category.name }
                categoryGuides.each { mkp.yieldUnescaped renderGuide(it) }
            }
        }
        writer.toString()
    }

    @CompileDynamic
    static String guideGroupByTag(Tag tag, List<Guide> guides) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: "guidegroup") {
            div(class: "guidegroupheader") {
                img src: "[%url]/images/documentation.svg" as String, alt: 'Guides'
                h2 "Guides filtered by #${tag.title}"
            }
            ul {
                List<Guide> tagGuides = guides.findAll { Guide guide -> guide.tags.contains(tag.title) }
                tagGuides.each { mkp.yieldUnescaped renderGuide(it) }
            }
        }
        writer.toString()
    }

    static Map<String, Category> categories() {
        [
                googlecloud: new Category(name: 'Grails + Google Cloud', image: 'googlecloud.svg'),
                angular: new Category(name: 'Grails + Angular', image: 'grailsangular.svg'),
                angularjs: new Category(name: 'Grails + AngularJS', image: 'grailsangular.svg'),
                react: new Category(name: 'Grails + React', image: 'react.svg'),
                vue: new Category(name: 'Grails + Vue.js', image: 'vue.svg'),
                ria: new Category(name: 'Grails + RIA (Rich Internet Application)', image: 'ria.svg'),
                ios: new Category(name: 'Grails + iOS', image: 'ios.svg'),
                android: new Category(name: 'Grails + Android', image: 'grails_android.svg'),
                devops: new Category(name: 'Grails + DevOps', image: 'grailsdevops.svg'),
                apprentice: new Category(name: 'Grails Apprentice', image: 'grailaprrentice.svg'),
                gorm: new Category(name: 'GORM', image: 'gorm.svg'),
                testing: new Category(name: 'Grails Testing', image: 'testing.svg'),
                async: new Category(name: 'Grails Async', image: 'async.svg'),
                advanced: new Category(name: 'Advanced Grails', image: 'advancedgrails.svg'),
        ]
    }
}
