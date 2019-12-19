package org.grails.plugins.grails.navigation

import groovy.transform.CompileStatic
import org.grails.Navigation
import org.grails.model.MenuItem

@CompileStatic
class PluginsPage extends AssetPipelinePage  {
    String mainContent() { '' }
    String slug = ''
    String bodyClass = ''
    String title = 'Grails 3/4 Plugins'

    boolean active = true

    @Override
    MenuItem menuItem() {
        active ? Navigation.pluginsMenuItem() : null
    }
}

