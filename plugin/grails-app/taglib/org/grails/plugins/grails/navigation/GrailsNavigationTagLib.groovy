package org.grails.plugins.grails.navigation

class GrailsNavigationTagLib {

    static namespace = 'grailsnavigation'

    def mainHeader = { attrs, body ->
        boolean active = attrs.active as boolean
        PluginsPage pluginsPage = new PluginsPage()
        pluginsPage.active = active


        out << pluginsPage.mainHeader()
    }

    def footer = { attrs, body ->
        PluginsPage pluginsPage = new PluginsPage()
        out << pluginsPage.renderFooter()
    }

    def scriptAtClosingBody = { attrs, body ->
        PluginsPage pluginsPage = new PluginsPage()
        out << pluginsPage.scriptAtClosingBody()
    }
}