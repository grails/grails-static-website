package org.grails.guides

import groovy.time.TimeCategory
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.grails.GuidesFetcher
import org.grails.WebsiteGenerator
import org.grails.guides.model.Category
import org.grails.model.Guide
import org.grails.guides.model.Tag
import org.grails.guides.pages.GuidesPage
import org.grails.pages.Page
import org.grails.pages.SiteMapPage

@CompileStatic
class Main {

    static void main(String[] args) {
        List<Guide> guides = GuidesFetcher.fetchGuides()

        Set<Tag> tags = TagUtils.populateTags(guides)
        List<GuidesPage> pages = []
        pages << new GuidesPage(guides, tags)
        for ( Tag tag : tags ) {
            pages << new GuidesPage(guides, tags, tag)
        }
        for ( Category category : GuidesPage.categories().values() ) {
            pages << new GuidesPage(guides, tags, category)
        }
        String timestamp = WebsiteGenerator.timestamp()
        for (GuidesPage page : pages) {
            page.timestamp = timestamp
            String text = page.html()
            String path = "build/site/"
            if ( page.tag ) {
                path = "build/site/tags/"
            }
            if ( page.category ) {
                path = "build/site/categories/"
            }
            File f = new File("${path}${page.slug}")
            f.text = text
        }

        WebsiteGenerator.copyAssetsWithTimestamp(timestamp)

        List<String> urls = guides.collect { Guide guide ->
            "${Page.guidesUrl()}/${guide.name}/guide/index.html" as String
        }
        urls.add('index.html')
        SiteMapPage siteMapPage = new SiteMapPage(urls)
        WebsiteGenerator.savePage(siteMapPage, siteMapPage.slug)
    }

}
