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
                                    img src: "images/oci_logo.svg", width: "90%", alt: "Object Computing"
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
                                p 'OCI\'s Subject Matter Experts (SMEs) are available to provide up to 24/7 real-time technical support for Grails. The OCI team can assist you with:'
                                ul {
                                    li 'Architecture and design review'
                                    li 'Rapid prototyping, troubleshooting and debugging'
                                    li 'Customizing, modifying and extending the product'
                                    li 'Integration assistance and support'
                                    li 'Offering mentors, architects, and engineers to work alongside your team providing assistance and thought leadership throughout the life cycle of your project.'
                                }
                                p 'OCI offers flexible, customizable open source support service with direct access to a dedicated team of architects and engineers, whom developed the product and/or have spent their careers supporting and maturing it.'
                            }
                            section {
                                h3(class: "columnheader") {
                                    a href: 'https://objectcomputing.com/products/grails/consulting-support/', 'Consulting Support'
                                }
                                p {
                                    mkp.yield 'We build high-performance, real-time, mission-critical middleware systems and integration solutions. Our goal is to make solutions more open, scalable, reusable, interoperable, and affordable. Please visit '
                                    a href: "http://objectcomputing.com", 'objectcomputing.com'
                                    mkp.yield ' to learn more about our '
                                    a href: "https://objectcomputing.com/services/software-engineering/", 'engineering services'
                                    mkp.yield ', '
                                    a href: "https://objectcomputing.com/products/", 'Open Source middleware technologies'
                                    mkp.yield ', and '
                                    a href: "https://objectcomputing.com/training/catalog/grails/", 'Grails training'
                                    mkp.yield '.'
                                }
                            }
                            section {
                                h3(class: "columnheader") {
                                    a href: 'https://objectcomputing.com/training/catalog/grails/', 'Grails Training'
                                }
                                p {
                                    mkp.yield 'OCI, home to Grails, offers a number of training courses related to the Groovy and Grails framework. The curriculum provides students with the knowledge and skills they need to maximize developer productivity with Groovy, Grails, and related technologies. See all upcoming '
                                    a href: "https://objectcomputing.com/training/catalog/grails/", 'Grails training courses here'
                                }
                            }
                            section {
                                h3 class: "columnheader", 'Contact'
                                p {
                                    strong 'If you would like to find out more about OCI\'s engineering services, please contact us.'
                                }
                                ul {
                                    li '12140 Woodcrest Executive Drive, Suite 250 Saint Louis, MO 63141, USA'
                                    li {
                                        mkp.yield 'Tel: '
                                        a href: "tel:+1-314-579-0066", '01*314*579*0066'
                                    }
                                    li {
                                        mkp.yield 'Email: '
                                        a href: "mailto:info@objectcomputing.com", 'info@objectcomputing.com'
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        writer.toString()
    }

}
