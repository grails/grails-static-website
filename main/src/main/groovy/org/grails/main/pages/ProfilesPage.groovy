package org.grails.main.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.Navigation
import org.grails.main.SiteMap
import org.grails.model.MenuItem
import org.grails.pages.Page

@CompileStatic
class ProfilesPage extends Page {
    String slug = 'profiles.html'
    String bodyClass = ''
    String title = 'Profiles'

    @Override
    MenuItem menuItem() {
        Navigation.profilesMenuItem(grailsUrl())
    }

    @CompileDynamic
    @Override
    String mainContent() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class:"content") {
            div(class:"twocolumns") {
                div(class:"column") {
                    mkp.yieldUnescaped SiteMap.PROFILES.renderAsHtml()
                }
                div(class:"column") {
                    mkp.yieldUnescaped SiteMap.PLUGIN_PROFILES.renderAsHtml()

                    mkp.yieldUnescaped SiteMap.THIRD_PARTY_PROFILES.renderAsHtml()
                }
            }

        }
        writer.toString()
    }
}
