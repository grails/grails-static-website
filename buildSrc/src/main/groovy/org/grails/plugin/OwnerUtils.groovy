package org.grails.plugin

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder


@CompileStatic
class OwnerUtils {


    static Set<Owner> populateOwnersByPlugins(List<Plugin> plugins) {
        Set<Owner> owners = []
        if (plugins) {
            for (Plugin plugin : plugins) {
                if (plugin.owner) {
                        owners.add(plugin.owner)
                    }
            }
        }
        return ownerCloud(owners) as Set<Owner>
    }
    @CompileDynamic
    static String ownerCloud(Set<Owner> owners) {

        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'tagsbytopic') {
            html.ul(class: 'tagcloud') {
                for (owner in owners) {
                    html.li(class: 'tag') {
                        a (href: "sdf.html"){ owner.name}
                    }
                }
            }

        }
        writer.toString()
    }

}