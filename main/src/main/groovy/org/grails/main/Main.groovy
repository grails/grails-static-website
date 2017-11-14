package org.grails.main

import groovy.transform.CompileStatic
import org.grails.GuidesFetcher
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

        ['Web',
         'Rest API',
         'AngularJS',
         'Angular',
         'React',
         'Webpack',
         'Plugin',
         'Rest API Plugin',
         'Web Plugin',].each { String title ->
            List<Profile> profiles = [SiteMap.PROFILES.profiles, SiteMap.PLUGIN_PROFILES.profiles].flatten()  as List<Profile>
            Profile profile = profiles.find { Profile profile ->
                profile.title == title
            }
            if ( profile ) {
                RedirectPage page = new RedirectPage(profile.docsHref, "profiles/${profile.slug}/index.html")
                pages << page
            }
        }
        pages << new RedirectPage('https://grails.github.io/grails-upgrade/latest/guide/index.html', "upgrade.html")

        pages << new IFramePage('http://docs.grails.org/latest/api/', 'api.html', null)
        pages << new IFramePage('https://grails.github.io/grails-data-mapping/latest/api/', 'gormApi.html', null)

        for (HtmlPage page : pages) {
            savePage(page, page.slug)
        }

        String grailsUrl = (pages.get(0) as Page).grailsUrl()

        List<String> urls = pages.collect { HtmlPage page -> "${grailsUrl}/${page.slug}" as String }
        SiteMapPage siteMapPage = new SiteMapPage(urls)
        savePage(siteMapPage, siteMapPage.slug)
    }

    static savePage(HtmlPage page, String filename) {
        String text = page.html()
        String preffix = "build/site/"
        File f = new File("${preffix}${filename}")
        f.createNewFile()
        f.text = text
    }
}