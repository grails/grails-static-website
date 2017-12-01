package org.grails.guides.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.Navigation
import org.grails.ReadFileUtils
import org.grails.guides.model.Category
import org.grails.model.Guide
import org.grails.guides.model.Tag
import org.grails.model.TextMenuItem
import org.grails.model.Training
import org.grails.pages.Page

@CompileStatic
class GuidesPage extends Page implements ReadFileUtils {

    public static final Integer NUMBER_OF_LATEST_GUIDES = 5

    String bodyClass = 'guides'
    List<Guide> guides
    Set<Tag> tags
    Tag tag
    Category category

    GuidesPage(List<Guide> guides, Set<Tag> tags) {
        this.guides = guides
        this.tags = tags
    }

    GuidesPage(List<Guide> guides, Set<Tag> tags, Tag tag) {
        this(guides, tags)
        this.tag = tag
    }

    GuidesPage(List<Guide> guides, Set<Tag> tags, Category category) {
        this(guides, tags)
        this.category = category
    }

    String getSlug() {
        if ( tag ) {
            return "${tag.slug.toLowerCase()}.html"
        }
        if ( category ) {
            return "${category.slug.toLowerCase()}.html"
        }
        'index.html'
    }

    @Override
    boolean doNotIndex() {
        if ( tag || category ) {
            return true
        }
        false
    }

    @Override
    String getHtmlHeadTitle() {
        'Guides | Grails Framework'
    }

    @Override
    boolean showChalicesBackground() {
        !( tag ||category )
    }

    @CompileDynamic
    String renderGuide(Guide guide) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.li {
            a class: 'guide', href: "http://guides.grails.org/${guide.name}/guide/index.html", guide.title
            guide.tags.each { String tag ->
                span(style: 'display: none', class: 'tag', tag)
            }
        }
        writer.toString()
    }


    @CompileDynamic
    @Override
    String getTitle() {
        if ( tag || category ) {
            StringWriter writer = new StringWriter()
            MarkupBuilder html = new MarkupBuilder(writer)
            html.div {
                a href: "${guidesUrl()}/index.html", 'Guides'
                if (tag) {
                    mkp.yieldUnescaped " &rarr; #${tag.title}"
                } else if (category) {
                    mkp.yieldUnescaped " &rarr; ${category.name}"
                }
            }
            return writer.toString()
        }
        'Learning'
    }

    @Override
    List<String> getJavascriptFiles() {
        List<String> jsFiles = super.getJavascriptFiles()
        jsFiles << ("${guidesUrl()}/javascripts/${timestamp ? (timestamp + '.') : ''}oci-training.js" as String)
        jsFiles << ("${guidesUrl()}/javascripts/${timestamp ? (timestamp + '.') : ''}search.js" as String)
        jsFiles
    }

    @Override
    List<String> getCssFiles() {
        ["${guidesUrl()}/stylesheets/${timestamp ? (timestamp + '.') : ''}screen.css" as String]
    }

    @Override
    String getImageAssetPreffix() {
        "${guidesUrl()}/images/"
    }

    @Override
    TextMenuItem menuItem() {
        if ( tag ||category ){
            return null
        }
        Navigation.guidesMenuItem(guidesUrl())
    }

    @CompileDynamic
    String guideGroupByCategory(Category category, List<Guide> guides, boolean linkToCategory = true, String cssStyle = '') {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)

        html.div(class: "guidegroup", style: cssStyle) {
            div(class: "guidegroupheader") {
                img src: "${getImageAssetPreffix()}${category.image}" as String, alt: category.name
                if ( linkToCategory )  {
                    a(href: "${guidesUrl()}/categories/${category.slug}.html") {
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
    String guideGroupByTag(Tag tag, List<Guide> guides) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)

        html.div(class: "guidegroup") {
            div(class: "guidegroupheader") {
                img src: "${getImageAssetPreffix()}documentation.svg" as String, alt: 'Guides'
                h2 "Guides filtered by #${tag.title}"
            }
            ul {
                List<Guide> tagGuides = guides.findAll { Guide guide -> guide.tags.contains(tag.title) }
                tagGuides.each { mkp.yieldUnescaped renderGuide(it) }
            }
        }
        writer.toString()
    }

    @CompileDynamic
    String leftColumn() {
        if ( tag || category ) {
            return ''
        }
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div {
            mkp.yieldUnescaped latestGuides()
        }
        writer.toString()
    }

    @CompileDynamic
    String rightColumn() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div {
            mkp.yieldUnescaped searchBox()
            if ( tag || category ) {
                mkp.yieldUnescaped sponsoredBy()
            } else {
                mkp.yieldUnescaped Training.training()
                mkp.yieldUnescaped sponsoredBy()
                mkp.yieldUnescaped tagCloud()
            }
        }
        writer.toString()
    }

    @CompileDynamic
    String guideSuggestion() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'guidesuggestion') {
            h3 class: 'columnheader', 'Which topic would you like us to cover?'
            String formHtml = readFileContent('guidesuggestionform.html')
            if ( formHtml ) {
                mkp.yieldUnescaped formHtml
            }
        }
        writer.toString()
    }

    @CompileDynamic
    String searchBox() {
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
    String sponsoredBy() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'sponsoredby') {
            h4 'Sponsored by'
            a(href: 'https://objectcomputing.com/products/grails/') {
                img src: "${getImageAssetPreffix()}oci_logo.svg", alt: 'Object Computing'
            }
        }
        writer.toString()
    }

    @CompileDynamic
    String latestGuides() {
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
                            mkp.yield guide.publicationDate.format('MMM dd, yyyy')
                            mkp.yield ' - '
                            mkp.yield guide.category
                        }
                        a href: "http://guides.grails.org/${guide.name}/guide/index.html", 'Read More'
                    }
                }
            }
        }
        writer.toString()
    }

    @CompileDynamic
    String tagCloud() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'tagsbytopic') {
            h3 class: 'columnheader', 'Guides by Tag'
            ul(class: 'tagcloud') {
                tags.sort { Tag a, Tag b -> a.slug <=> b.slug }.each { Tag t ->
                    li(class: "tag${t.ocurrence}") {
                        a href: "${guidesUrl()}/tags/${t.slug.toLowerCase()}.html", t.title
                    }
                }
            }
        }
        writer.toString()
    }

    @CompileDynamic
    String mainContent() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'content') {
            setOmitEmptyAttributes(true)
            setOmitNullAttributes(true)
            div(class: 'twocolumns') {
                div(class: 'column') {
                    mkp.yieldUnescaped rightColumn()
                }
                div(class: 'column') {
                    mkp.yieldUnescaped leftColumn()
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
                        mkp.yieldUnescaped guideGroupByCategory(categories().googlecloud, guides, true, 'margin-top: 0;')
                        mkp.yieldUnescaped guideGroupByCategory(categories().angular, guides)
                        mkp.yieldUnescaped guideGroupByCategory(categories().angularjs, guides)
                        mkp.yieldUnescaped guideGroupByCategory(categories().react, guides)
                        mkp.yieldUnescaped guideGroupByCategory(categories().ria, guides)
                        mkp.yieldUnescaped guideGroupByCategory(categories().ios, guides)
                        mkp.yieldUnescaped guideGroupByCategory(categories().android, guides)
                        mkp.yieldUnescaped guideGroupByCategory(categories().devops, guides)
                        mkp.yieldUnescaped guideSuggestion()
                    }
                }
                div(class: 'column') {
                    mkp.yieldUnescaped guideGroupByCategory(categories().gorm, guides, true, 'margin-top: 0;')
                    mkp.yieldUnescaped guideGroupByCategory(categories().testing, guides)
                    mkp.yieldUnescaped guideGroupByCategory(categories().async, guides)
                    mkp.yieldUnescaped guideGroupByCategory(categories().advanced, guides)
                }
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
