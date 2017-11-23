package org.grails.main

import groovy.transform.CompileStatic
import org.grails.GuidesFetcher
import org.grails.WebsiteGenerator
import org.grails.model.Guide
import org.grails.main.model.Profile
import org.grails.main.pages.BuildStatusPage
import org.grails.pages.HtmlPage
import org.grails.pages.IFramePage
import org.grails.pages.Page
import org.grails.pages.RedirectPage
import org.grails.pages.SiteMapPage

@CompileStatic
class Main {
    static void main(String[] args) {
        List<Guide> guides = GuidesFetcher.fetchGuides()
        List<HtmlPage> pages = SiteMap.PAGES
        pages << new BuildStatusPage(guides)

        ([SiteMap.PROFILES.profiles, SiteMap.PLUGIN_PROFILES.profiles].flatten() as List<Profile>).each { Profile profile ->
            RedirectPage page = new RedirectPage(profile.docsHref, "profiles/${profile.slug}/index.html")
            pages << page
        }
        pages << new RedirectPage('https://grails.github.io/grails-upgrade/latest/guide/index.html', "upgrade.html")

        pages << new IFramePage('http://docs.grails.org/latest/api/', 'api.html', null)
        pages << new IFramePage('https://grails.github.io/grails-data-mapping/latest/api/', 'gormApi.html', null)

        String timestamp = WebsiteGenerator.timestamp()

        for (HtmlPage page : pages) {
            page.timestamp = timestamp
            WebsiteGenerator.savePage(page, page.slug)
        }

        WebsiteGenerator.copyAssetsWithTimestamp(timestamp)

        String grailsUrl = (pages.get(0) as Page).grailsUrl()

        List<String> urls = pages.collect { HtmlPage page -> "${grailsUrl}/${page.slug}" as String }
        SiteMapPage siteMapPage = new SiteMapPage(urls)
        WebsiteGenerator.savePage(siteMapPage, siteMapPage.slug)
    }
}