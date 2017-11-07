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
        jsFiles << 'javascripts/training.js'
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
                    mkp.yield 'A Powerful Groovy-Based Web application framework '
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
                        b 'Get the latest Version'
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
                            p 'Groovy is a language aimed at improving developer productivity. It is optionally-typed, dynamic but with static-typing and static compilation capabilities.'
                        }
                        div(class: 'column') {
                            img src: "images/springboot.svg", alt: "Spring Boot"
                            h3 'On top of Spring Boot'
                            p 'Grails is a web framework, for the JVM, built on top of Spring Boot. It leverages many features such as Spring-Powered dependency injection.'
                        }
                        div(class: 'column') {
                            img src: "images/java.png", alt: "Smooth Java Integration"
                            h3 'Smooth Java Integration'
                            p 'Seamlessly and transparently integrates and interoperates with Java, the JVM, and existing Java EE containers.'
                        }
                        div(class: 'column') {
                            img src: "images/flatlearningcurve.png", alt: "Flat Learning Curve"
                            h3 'Flat Learning Curve'
                            p 'Convention-over-Configuration, sensible defaults, opinionated APIs and the Groovy language combine to make Grails easy to learn  for Java developers.'
                        }
                        div(class: 'column') {
                            img src: "images/gorm.png", alt: "GORM - Data Access Toolkit"
                            h3 'Seamless GORM Integration'
                            p 'GORM is a data access toolkit that provides a rich sets of APIs for accessing relational and non-relational data. It includes implementations for Hibernate (SQL), MongoDB, Cassandra, and Neo4j.'
                        }
                        div(class: 'column') {
                            img src: "images/restapis.svg", alt: "REST APIs, REACT, ANGULAR"
                            h3 'REST APIs, REACT, ANGULAR'
                            p 'With the use of application profiles, Grails allows you to build modern web applications. There are profiles to facilitate the construction of REST APIs or Web applications with a Javascript frontend.'
                        }
                        div(class: 'column') {
                            img src: "images/plugins.svg", alt: ""
                            h3 'Plugins'
                            p 'Build plugins that extend and enhance Grails or reuse an existing plugin already published by a vibrant plugin community!'
                        }
                        div(class: 'column') {
                            img src: "images/view.svg", alt: "View Technologies"
                            h3 'View Technologies'
                            p 'Technologies such as GSP, JSON Views and Markup Views help you generate HTML, JSON or XML effortlessly.'
                        }
                        div(class: 'column') {
                            img src: "images/asynchronous.svg", alt: "Asynchronous Capabilities"
                            h3 'Asynchronous Capabilities'
                            p 'The async features of Grails aim to simplify concurrent programming. Grails\' features include the concept of Promises, a unified event model, and the use of RxJava to write reactive logic.'
                        }
                        div(class: 'column') {
                            img src: "images/opensource.svg", alt: "Open Source"
                            h3 'Open Source'
                            p 'Grails is an Open Source, Apache 2 License project.'
                        }
                        div(class: 'column') {
                            img src: "images/dsl.png", alt: "Domain-Specific Languages"
                            h3 'Domain-Specific Languages'
                            p 'Expressive Domain-Specific Languages (DSLs) used for validation, querying, markup rendering, etc.'
                        }
                        div(class: 'column') {
                            img src: "images/idesupport.png", alt: "IDE Support"
                            h3 'IDE Support'
                            p 'Great support in IDEs and text editors such as Intellij IDEA, Eclipse, Sublime, Textmate, etc.'
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
