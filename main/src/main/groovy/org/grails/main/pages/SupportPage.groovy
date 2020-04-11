package org.grails.main.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.ReadFileUtils
import org.grails.model.MenuItem
import org.grails.model.TextMenuItem
import org.grails.pages.Page

@CompileStatic
class SupportPage extends Page implements  ReadFileUtils {
    String title = 'Commercial Support'
    String slug = 'support.html'
    String bodyClass = ''

    @Override
    MenuItem menuItem() {
        new TextMenuItem(href: "${grailsUrl()}/support.html", title: 'Support')
    }

    @CompileDynamic
    @Override
    String mainContent() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'content') {
            article {
                div(class: "twocolumns") {
                    div(class: "column") {
                        div(class: 'transparent_post') {
                            section {
                                p 'Grails development is sponsored by:'
                                a(href: "https://objectcomputing.com/products/grails/") {
                                    img src: "images/oci_home_to_grails.svg", width: "90%", alt: "Object Computing"
                                }
                            }
                            article {
                                h3 class: "columnheader", 'Request Free Consultation'
                                String text = readFileContent('freeconsultationform.html')
                                if ( text ) {
                                    mkp.yieldUnescaped text
                                }
                            }
                        }
                    }
                    div(class: "column") {
                        div(class: "post") {
                            section {
                                p 'OCI offers flexible, customizable open source support services with direct access to the architects and engineers who developed Grails and have spent their careers supporting and maturing the framework.'
                                p 'The OCI team can assist you with:'
                                ul {
                                    li 'Architecture and design review'
                                    li 'Rapid prototyping, troubleshooting, and debugging'
                                    li 'Customizing, modifying, and extending the product'
                                    li 'Integration assistance and support'
                                    li 'Mentorship and architecture throughout the lifecycle of your project'
                                }
                                p {
                                    a href: 'mailto:info@objectcomputing.com', 'Contact OCI'
                                    mkp.yield ' to learn more.'
                                }
                                p '12140 Woodcrest Executive Drive, Suite 300 Saint Louis, MO 63141, USA'
                                p 'Tel: 01*314*579*0066'
                                p 'Email: info@objectcomputing.com'
                          
                            }
                           
                        }
                    }
                }
            }
        }
        writer.toString()
    }

}
