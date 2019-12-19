package org.grails.plugins.grails.navigation

import groovy.transform.CompileStatic
import org.grails.Navigation
import org.grails.model.MenuItem

@CompileStatic
class GenericPage extends AssetPipelinePage {
    String mainContent() { '' }
    String slug = ''
    String bodyClass = ''
    String title = 'Grails'

    boolean active = true

    @Override
    MenuItem menuItem() {
        null
    }
}
