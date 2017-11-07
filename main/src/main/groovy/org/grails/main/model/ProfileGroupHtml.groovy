package org.grails.main.model

import groovy.transform.CompileDynamic
import groovy.xml.MarkupBuilder
import org.grails.model.PageElement

trait ProfileGroupHtml implements PageElement {
    abstract String getImage()
    abstract String getTitle()
    abstract List<Profile> getProfiles()
    abstract String getDescription()

        @CompileDynamic
        @Override
        String renderAsHtml() {
            StringWriter writer = new StringWriter()
            MarkupBuilder html = new MarkupBuilder(writer)
            html.div(class: "guidegroup") {
                div(class:"guidegroupheader") {
                    img src: image, alt: title
                    h2 title
                }
                ul {
                    if ( description ) {
                        li(class: 'legend') {
                            html.mkp.yieldUnescaped description
                        }
                    }
                    for (Profile profile : getProfiles()) {
                        html.mkp.yieldUnescaped profile.renderAsHtml()
                    }
                }
            }
            writer.toString()

        }
    }

