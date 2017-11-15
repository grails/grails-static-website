package org.grails.main.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.main.SiteMap
import org.grails.model.MenuItem
import org.grails.model.TextMenuItem
import org.grails.pages.Page

@CompileStatic
class HomePage extends Page {
    String slug = 'index.html'
    String bodyClass = 'home'
    String title = null

    @Override
    MenuItem menuItem() {
        new TextMenuItem(href: "${grailsUrl()}/index.html", title: 'Grails')
    }

    @Override
    List<String> getJavascriptFiles() {
        List<String> jsFiles = super.getJavascriptFiles()
        jsFiles << 'javascripts/oci-training.js'
        jsFiles
    }

    @CompileDynamic
    @Override
    String mainContent() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'homebar') {
            div(class: 'content') {
                h1 {
                    mkp.yield 'A powerful Groovy-based web application framework '
                    mkp.yield 'for the JVM built on top of Spring Boot'
                }
                div(class: "calltoactions") {
                    div(class: "calltoaction") {
                        b 'Read the docs'
                        h2 {
                            a(href: "http://docs.grails.org/latest/guide/single.html") {
                                mkp.yield 'Documentation'
                                span class: "version", SiteMap.LATEST_VERSION
                            }
                        }
                        a class: 'allversions', href: "documentation.html", 'browse all versions'
                    }
                    div(class: "calltoaction") {
                        b 'Get the latest version'
                        h2 {
                            a(href: "https://github.com/grails/grails-core/releases/download/v${SiteMap.LATEST_VERSION}/grails-${SiteMap.LATEST_VERSION}.zip") {
                                mkp.yield 'Download'
                                span class: "version", SiteMap.LATEST_VERSION
                            }
                        }
                        a class: 'allversions', href: "download.html", 'browse all versions'
                    }
                }
            }
        }
        html.article(id: "features") {
                div(class: 'content') {
                    div(class: 'threecolumns') {
                        div(class: 'column') {
                            img src: "images/groovy.png", alt: "Groovy Programming Language"
                            h3 'Groovy-Based'
                            p 'Apache Groovy is a language for the Java platform designed to enhance developers\' productivity. It is an optionally-typed and dynamic language but with static-typing and static compilation capabilities.'
                        }
                        div(class: 'column') {
                            img src: "images/springboot.svg", alt: "Spring Boot"
                            h3 'On top of Spring Boot'
                            p 'Grails is built on top of Spring Boot and leverages Spring Boot\'s time-saving features, such as Spring-powered dependency injection.'
                        }
                        div(class: 'column') {
                            img src: "images/java.png", alt: "Smooth Java Integration"
                            h3 'Smooth Java Integration'
                            p 'Grails seamlessly and transparently integrates and interoperates with Java, the JVM, and existing Java EE containers.'
                        }
                        div(class: 'column') {
                            img src: "images/flatlearningcurve.png", alt: "Flat Learning Curve"
                            h3 'Flat Learning Curve'
                            p 'Convention-over-configuration, sensible defaults, opinionated APIs, and the Groovy language combine to make Grails easy to learn for Java developers.'
                        }
                        div(class: 'column') {
                            img src: "images/gorm.png", alt: "GORM - Data Access Toolkit"
                            h3 'Seamless GORM Integration'
                            p 'Grails seamlessly integrates with GORM, a data access toolkit that provides a rich set of APIs for accessing relational and non-relational data. GORM also includes implementations for Hibernate (SQL), MongoDB, Cassandra, and Neo4j.'
                        }
                        div(class: 'column') {
                            img src: "images/restapis.svg", alt: "REST APIs, REACT, ANGULAR"
                            h3 'REST APIs, REACT, ANGULAR'
                            p 'With the use of application profiles, including React and Angular, Grails allows developers to build REST APIs or modern web applications with a JavaScript frontend.'
                        }
                        div(class: 'column') {
                            img src: "images/plugins.svg", alt: ""
                            h3 'Plugins'
                            p 'Developers can build plugins that extend and enhance Grails, or they can access existing plugins published by a vibrant plugin community.'
                        }
                        div(class: 'column') {
                            img src: "images/view.svg", alt: "View Technologies"
                            h3 'View Technologies'
                            p 'Technologies such as GSP, JSON Views, and Markup Views help developers effortlessly generate HTML, JSON and XML.'
                        }
                        div(class: 'column') {
                            img src: "images/asynchronous.svg", alt: "Asynchronous Capabilities"
                            h3 'Asynchronous Capabilities'
                            p 'The async features of Grails aim to simplify concurrent programming. Grails\' features include the concept of Promises, a unified event model, and the use of RxJava to write reactive logic.'
                        }
                        div(class: 'column') {
                            img src: "images/opensource.svg", alt: "Open Source"
                            h3 'Open Source'
                            p 'Grails is an Open Source Apache 2 License project.'
                        }
                        div(class: 'column') {
                            img src: "images/dsl.png", alt: "Domain-Specific Languages"
                            h3 'Domain-Specific Languages'
                            p 'Grails relies on expressive domain-specific languages (DSLs) for validation, querying, markup rendering, and more.'
                        }
                        div(class: 'column') {
                            img src: "images/idesupport.png", alt: "IDE Support"
                            h3 'IDE Support'
                            p 'Developers can access support at any time via IDEs and text editors, such as Intellij IDEA, Eclipse, Sublime, and Textmate.'
                        }
                    }
                }
            }
            html.article(class: 'training', style: 'display: none;') {
                div(class: "content") {
                    h2 'Grails Training'
                    div(id: 'ocitraining') {
                        span ''
                    }
                }
            }
            html.article(class: "testimonials") {
                div(class: "content") {
                    h2 'They all use Grails'
                    img src: "${getImageAssetPreffix()}testimoniallogos.png", alt: "Companies using Grails"
                }
            }

        writer.toString()
    }
}
