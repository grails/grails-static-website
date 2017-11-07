package org.grails

import groovy.transform.CompileStatic
import org.grails.model.IconMenuItem
import org.grails.model.Menu
import org.grails.model.MenuItem
import org.grails.model.TextMenuItem

@CompileStatic
class Navigation {

    static Menu secondaryMenu(String url = null, String gormUrl) {
        new Menu(items: [
                blogMenuItem(),
                communityMenuItem(url),
                booksMenuItem(url),
                searchMenuItem(url),
        ] as List<MenuItem>)
    }

    static MenuItem blogMenuItem() {
        new TextMenuItem(href: 'http://grailsblog.objectcomputing.com', title: 'Blog')
    }

    static Menu mainMenu(String url = null, String guidesUrl = null) {
        new Menu(items: [
                documentationMenuItem(url),
                downloadMenuItem(url),
                pluginsMenuItem(),
                guidesMenuItem(guidesUrl),
                questionsMenuItem(url),
                supportMenuItem(url)
        ] as List<MenuItem>)
    }

    static Menu parternsMenu() {
        new Menu(items: [
                new TextMenuItem(intro: 'Grails\' repositories is hosted by', title: 'Artifactory', href: 'http://artifactoryonline.com/'),
                new TextMenuItem(intro: 'Website hosting provided by', title: 'Pivotal', href: 'http://run.pivotal.io/'),
                new TextMenuItem(intro: 'YourKit supports Grails with its ', title:'Java Profiler', href: 'https://www.yourkit.com/java/profiler/index.jsp'),
                new TextMenuItem(intro: 'Grails is Open Source', title:'Apache 2 License', href: 'http://www.apache.org/licenses/LICENSE-2.0.html'),
        ] as List<MenuItem>)
    }

    static Menu socialMediaMenu() {
        new Menu(items: [
                new IconMenuItem(image: 'slack.svg', href: 'https://grails.slack.com', alt: 'Slack Icon'),
                new IconMenuItem(image: 'youtube.svg', href: 'https://www.youtube.com/watch?v=XnRNfDGkBVg&list=PLI74De5M9T73uH3WilDCePP2qfSDpMaGu', alt: 'Youtube Icon'),
                new IconMenuItem(image: 'linkedin.svg', href: 'https://www.linkedin.com/groups/39757', alt: 'LinkedIn Icon'),
                new IconMenuItem(image: 'github.svg', href: 'https://github.com/grails/', alt: 'Github Icon'),
                new IconMenuItem(image: 'twitter.svg', href: 'https://twitter.com/grailsframework', alt: 'Twitter Icon'),
        ] as List<MenuItem>)
    }


    static TextMenuItem communityMenuItem(String url = null) {
        menuItemWithHref('community.html', 'Community', url)
    }

    static TextMenuItem profilesMenuItem(String url = null) {
        menuItemWithHref('profiles.html', 'Profiles', url)
    }

    static TextMenuItem booksMenuItem(String url = null) {
        menuItemWithHref('books.html', 'Books', url)
    }

    static TextMenuItem searchMenuItem(String url = null) {
        menuItemWithHref('search.html', 'Search', url)
    }


    static TextMenuItem documentationMenuItem(String url = null) {
        menuItemWithHref('documentation.html', 'Documentation', url)
    }


    static TextMenuItem downloadMenuItem(String url = null) {
        menuItemWithHref('download.html', 'Download', url)
    }


    static TextMenuItem pluginsMenuItem() {
        new TextMenuItem(href: 'http://plugins.grails.org', title: 'Plugins')
    }

    static TextMenuItem gormMenuItem(String gormUrl) {
        menuItemWithHref('index.html', 'Gorm', gormUrl)
    }

    static TextMenuItem guidesMenuItem(String guidesUrl) {
        menuItemWithHref('index.html', 'Learning', guidesUrl)
    }

    static TextMenuItem questionsMenuItem(String url = null) {
        menuItemWithHref('faq.html', 'FAQ', url)
    }

    static TextMenuItem supportMenuItem(String url = null) {
        menuItemWithHref('support.html', 'Support', url)
    }

    static TextMenuItem menuItemWithHref(String relativePath, String title, String url) {
        String href = url ?  "${url}/${relativePath}" : relativePath
        new TextMenuItem(href: href, title: title)
    }
}
