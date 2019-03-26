package org.grails.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.Navigation
import org.grails.model.IconMenuItem
import org.grails.model.Menu
import org.grails.model.MenuItem
import org.grails.model.TextMenuItem

@CompileStatic
abstract class Page implements HtmlPage {

    String timestamp

    abstract MenuItem menuItem()

    abstract String mainContent()

    abstract String getSlug()

    abstract String getBodyClass()

    abstract String getTitle()

    public static WebsiteEnvironment environment = WebsiteEnvironment.PRODUCTION

    static String developmentServer() {
        'http://localhost:8888'
    }

    @Override
    void setTimestamp(String timestamp) {
        this.timestamp = timestamp
    }

    static String gormUrl() {
        switch (environment) {
            case WebsiteEnvironment.DEVELOPMENT:
                return developmentServer()
            case WebsiteEnvironment.STAGING:
                return 'http://gorm.sergiodelamo.es'
            case WebsiteEnvironment.PRODUCTION:
                return 'http://gorm.grails.org'
        }
    }

    static String guidesUrl() {
        switch (environment) {
            case WebsiteEnvironment.DEVELOPMENT:
                return developmentServer()
            case WebsiteEnvironment.PRODUCTION:
                return 'https://guides.grails.org'
        }
    }

    static String grailsUrl() {
        switch (environment) {
            case WebsiteEnvironment.DEVELOPMENT:
                return developmentServer()
            case WebsiteEnvironment.STAGING:
                return 'http://grails.sergiodelamo.es'
            case WebsiteEnvironment.PRODUCTION:
                return 'https://grails.org'
        }
    }

    static String docsUrl() {
        'https://docs.grails.org'
    }

    @CompileDynamic
    String renderMenuItems(Menu menu) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.ul {
            setOmitEmptyAttributes(true)
            setOmitNullAttributes(true)
            for (MenuItem menuItem : menu.items) {
                MenuItem activeMenuItem = this.menuItem()

                if ( menuItem instanceof IconMenuItem ) {
                    IconMenuItem iconMenuItem = menuItem as IconMenuItem
                    li {
                        a(href: menuItem.href) {
                            mkp.yieldUnescaped renderImage(iconMenuItem.image, iconMenuItem.alt, '')
                        }
                    }
                } else if ( menuItem instanceof TextMenuItem ) {
                    TextMenuItem textMenuItem = menuItem as TextMenuItem
                    if ( activeMenuItem && activeMenuItem.equals(menuItem) ) {
                        li(class: 'active') {
                            if ( textMenuItem.intro ) {
                                mkp.yield textMenuItem.intro
                            }
                            a href: textMenuItem.href, textMenuItem.title
                        }
                    } else {
                        li {
                            if ( textMenuItem.intro ) {
                                mkp.yield textMenuItem.intro
                            }
                            a href: textMenuItem.href, textMenuItem.title
                        }
                    }
                }
            }
        }
        writer.toString()
    }

    String getHtmlHeadTitle() {
        if ( !getTitle() ) {
            return 'Grails Framework'
        }
        [getTitle(), 'Grails Framework'].join(' | ')
    }

    String getMetaDescription() {
        null
    }

    List<String> getCssFiles() {
        ["/stylesheets/${timestamp ? (timestamp + '.') : ''}screen.css" as String]
    }

    List<String> getJavascriptFiles() {
        ["/javascripts/${timestamp ? (timestamp + '.') : ''}navigation.js" as String]
    }

    static String getImageAssetPreffix() {
        "/images/"
    }

    boolean showChalicesBackground() {
        true
    }

    Menu secondaryMenu() {
        Navigation.secondaryMenu(grailsUrl(), gormUrl())
    }

    Menu mainMenu() {
        Navigation.mainMenu(grailsUrl(), guidesUrl())
    }

    Menu parternsMenu() {
        Navigation.parternsMenu(grailsUrl())
    }

    Menu socialMediaMenu() {
        Navigation.socialMediaMenu()
    }

    String mainLogo() {
        'grails_logo.svg'
    }

    String mainLogoAlt() {
        'Grails Logo'
    }

    @CompileDynamic
    String renderImage(String image, String alt, String className, Integer width = null) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        if (width) {
            html.img class: className, src: "${getImageAssetPreffix()}${image}", alt: alt, width: "${width}px"
        } else {
            html.img class: className, src: "${getImageAssetPreffix()}${image}", alt: alt
        }
        writer.toString()
    }

    @CompileDynamic
    String renderFooter() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.footer {
            div(class: 'content') {
                div(class: 'ocihometograils') {
                    span 'Sponsored by'
                    a(href: "https://objectcomputing.com/products/grails/") {
                        mkp.yieldUnescaped renderImage('oci_home_to_grails.svg', 'Object Computing - Home to Grails', '', 300)
                    }
                }
                nav(class: 'socialmedianav') {
                    mkp.yieldUnescaped renderMenuItems(socialMediaMenu())
                }
                nav(class: 'partnersnav') {
                    mkp.yieldUnescaped renderMenuItems(parternsMenu())
                }
            }
        }
        writer.toString()
    }

    @CompileDynamic
    String mainHeader() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.header(class: 'mainheader') {
            div(class: 'content') {
                a(href: "${grailsUrl() ? grailsUrl() + '/' : ''}index.html") {
                    mkp.yieldUnescaped renderImage(mainLogo(), mainLogoAlt(),"grailslogo")
                }
                a(href: "javascript:show('topmenus', 'showNavigationLink')", id: 'showNavigationLink', class: 'mobile align-center', 'Show Navigation')
                div(id: 'topmenus') {
                    nav(class: "secondarymenu", id: 'secondarymenu') {
                        mkp.yieldUnescaped renderMenuItems(secondaryMenu())
                    }
                    nav(class: "mainmenu", id: 'mainmenu') {
                        mkp.yieldUnescaped renderMenuItems(mainMenu())
                    }
                }

            }
        }
        writer.toString()
    }

    String pinnedIcon() {
        'grails-pinned-icon.svg'
    }

    String favIcon() {
        'favicon.ico'
    }

    boolean doNotIndex() {
        false
    }

    String googleSiteVerification() {
        'REYQ1_I6HGowAE7LOLVqfQbnKaB4IfFpMlGzMbJj55Q'
    }

    @CompileDynamic
    String html() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.html {
            setOmitEmptyAttributes(true)
            setOmitNullAttributes(true)
            head {
                meta name: 'google-site-verification', content: googleSiteVerification()
                meta name: 'viewport', content: 'width=device-width, initial-scale=1'
                if ( doNotIndex() ) {
                    meta name: 'robots', content: 'noindex,nofollow'
                }
                link rel: 'icon', href: "${getImageAssetPreffix()}${favIcon()}"
                link rel: 'mask-icon', href: "/${getImageAssetPreffix()}${pinnedIcon()}", color: 'feb672'
                meta charset: 'UTF-8'
                title getHtmlHeadTitle()
                if ( getMetaDescription() ) {
                    meta name: 'description', content: getMetaDescription()
                }

                if ( getCssFiles() ) {
                    for ( String css : getCssFiles() ) {
                        link rel: 'stylesheet', href: css
                    }
                }
                if ( getJavascriptFiles() ) {
                    for ( String javascriptSrc : getJavascriptFiles() ) {
                        script src: javascriptSrc, ''
                    }
                }
            }
            body(class: getBodyClass()) {
                mkp.yieldUnescaped mainHeader()
                if ( getTitle() ) {
                    if ( showChalicesBackground() ) {
                        div(class: "headerbar chalicesbg") {
                            div(class: "content") {
                                h1 {
                                    mkp.yieldUnescaped getTitle()
                                }
                            }
                        }
                    } else {
                        div(class: "headerbar") {
                            div(class: "content") {
                                h1 {
                                    mkp.yieldUnescaped getTitle()
                                }
                            }
                        }
                    }

                }
                mkp.yieldUnescaped mainContent()
                mkp.yieldUnescaped renderFooter()
                mkp.yieldUnescaped scriptAtClosingBody()

            }

        }
        "<!DOCTYPE html>\n${writer.toString()}"
    }

    @CompileDynamic
    String scriptAtClosingBody() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div {

            script(type: 'text/javascript') {
                mkp.yieldUnescaped '''
              (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
              (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
              m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
              })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

              ga('create', 'UA-82213539-2', 'auto');
              ga('send', 'pageview');
'''
            }
            script(type: 'text/javascript') {
                mkp.yieldUnescaped '''
adroll_adv_id = "HBWJH4CQCJGS5DJRSB4Z4D";
adroll_pix_id = "IVEQYFOZXZAPZMDVQH7BFE";
/* OPTIONAL: provide email to improve user identification */
/* adroll_email = "username@example.com"; */
(function () {
    var _onload = function(){
        if (document.readyState && !/loaded|complete/.test(document.readyState)){setTimeout(_onload, 10);return}
        if (!window.__adroll_loaded){__adroll_loaded=true;setTimeout(_onload, 50);return}
        var scr = document.createElement("script");
        var host = (("https:" == document.location.protocol) ? "https://s.adroll.com" : "http://a.adroll.com");
        scr.setAttribute('async', 'true');
        scr.type = "text/javascript";
        scr.src = host + "/j/roundtrip.js";
        ((document.getElementsByTagName('head') || [null])[0] ||
            document.getElementsByTagName('script')[0].parentNode).appendChild(scr);
    };
    if (window.addEventListener) {window.addEventListener('load', _onload, false);}
    else {window.attachEvent('onload', _onload)}
}());            
'''
            }
        }
        writer.toString()
    }

    static String columnsClass(List l) {
        switch ( l.size() ) {
            case 3:
                return 'threecolumns'
            default:
                return 'twocolumns'
        }
    }
}
