package org.grails.gorm.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.Navigation
import org.grails.gorm.model.GormImplementation
import org.grails.gorm.model.RxGORMImplementation
import org.grails.model.IconMenuItem
import org.grails.model.Menu
import org.grails.model.MenuItem
import org.grails.model.TextMenuItem
import org.grails.pages.Page

@CompileStatic
class GormHomePage extends Page {
    String title = null
    String slug = 'index.html'
    String bodyClass = 'gorm'

    MenuItem menuItem() {
        return null
    }

    @Override
    boolean showChalicesBackground() {
        false
    }

    String mainLogo() {
        'gorm_logo.svg'
    }

    String mainLogoAlt() {
        'GOM LOGO'
    }

    Map<String, GormImplementation> gormImplementations() {
        [hibernate:
                new GormImplementation(name: 'Gorm for Hibernate',
                                       image: 'hibernate.svg',
                                       description: 'The original implementation of GORM, GORM for Hibernate provides all the goodness of GORM for SQL databases.',
                                       hrefDocumentation: 'http://gorm.grails.org/latest/hibernate/manual/index.html',
                                       hrefApi: 'http://gorm.grails.org/latest/hibernate/api/index.html',
                                       hrefSource: 'https://github.com/grails/gorm-hibernate5'),
        neo4j:
                new GormImplementation(name: 'Gorm for Neo4j',
                                       image: 'neo4j.svg',
                                       description: 'Built for Neo4j 3.0 and above, GORM for Neo4j is built on the native Neo4j Bolt driver.',
                                       hrefDocumentation: 'http://gorm.grails.org/latest/neo4j/manual/index.html',
                                       hrefApi: 'http://gorm.grails.org/latest/hibernate/api/index.html',
                                       hrefSource: 'https://github.com/grails/gorm-neo4j'),

         graphql:
                 new GormImplementation(name: 'Graphql GORM',
                         image: 'graphql.svg',
                         description: 'The GORM GraphQL library provides functionality to generate a GraphQL schema based on your GORM entities',
                         hrefDocumentation: 'https://grails.github.io/gorm-graphql/snapshot/guide/index.html',
                         hrefApi: 'https://grails.github.io/gorm-graphql/snapshot/api/index.html',
                         hrefSource: 'https://github.com/grails/gorm-graphql'),
        mongodb:
                new GormImplementation(name: 'Gorm for MongoDB',
                                       image: 'mongo.svg',
                                       description: 'Built on the MongoDB Java Driver GORM for MongoDB includes features like Geospatial querying, Text search and Schemaless access etc.',
                                       hrefDocumentation: 'http://gorm.grails.org/latest/mongodb/manual/index.html',
                                       hrefApi: 'http://gorm.grails.org/latest/mongodb/api/index.html',
                                       hrefSource: 'https://github.com/grails/gorm-mongodb'),
        cassandra:
                new GormImplementation(name: 'Gorm for Cassandra',
                                       image: 'cassandra.svg',
                                       description: 'Built for managing massive amounts of data in the cloud. GORM for Cassandra lets you map your objects to Cassandra tables.',
                                       hrefDocumentation: 'http://gorm.grails.org/latest/cassandra/manual/index.html',
                                       hrefApi: 'http://gorm.grails.org/latest/cassandra/api/index.html',
                                       hrefSource: 'https://github.com/grails/gorm-cassandra',
        ),
        rxgorm:
        new RxGORMImplementation(name: 'RxGORM',
                image: 'rxjava.svg',
                description: 'GORM for RxJava. Build Reactive data access code using the powerful GORM API!',
                hrefDocumentation: 'http://gorm.grails.org/latest/rx/manual/index.html',
                hrefApi: 'http://gorm.grails.org/latest/rx/api/index.html'),
        ]
    }


    @CompileDynamic
    String mainContent() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'homebar') {
            div(class: 'content') {
                h1 'GORM 6 - A powerful Groovy-based data access toolkit for the JVM'
            }
            div(class: 'content') {
                div(class: "twocolumns") {
                    div(class: "odd column") {
                        article(class: 'quote') {
                            h2 {
                                b 'GORM'
                                mkp.yield ' is the data access toolkit used by '
                                a href: 'http://grails.org', 'Grails'
                                mkp.yield ' and provides a rich set of APIs for accessing relational and non-relational data including implementations for Hibernate (SQL), MongoDB, Neo4j, Cassandra,  an in-memory ConcurrentHashMap for testing and an automatic GraphQL schema generator'
                            }
                        }
                        mkp.yieldUnescaped gormImplementations().mongodb.renderAsHtml()
                        mkp.yieldUnescaped gormImplementations().graphql.renderAsHtml()
                        mkp.yieldUnescaped gormImplementations().cassandra.renderAsHtml()
                    }
                    div(class: "column") {
                        mkp.yieldUnescaped gormImplementations().hibernate.renderAsHtml()
                        mkp.yieldUnescaped gormImplementations().neo4j.renderAsHtml()
                        mkp.yieldUnescaped gormImplementations().rxgorm.renderAsHtml()

                    }
                }
            }
        }
        writer.toString()
    }

    @Override
    Menu secondaryMenu() {
        new Menu(items: [
        ])
    }

    @Override
    Menu mainMenu() {
        new Menu(items: [
                new TextMenuItem(href: "${guidesUrl()}/categories/gorm.html",
                        title: 'Guides'),
                new TextMenuItem(href: 'http://gorm.grails.org/latest/whatsNew/manual/index.html',
                             title: 'What\'s new?'),
                new TextMenuItem(href: 'http://gorm.grails.org/latest/developer/manual/index.html',
                        title: 'Developer Guide'),
                Navigation.supportMenuItem(),
        ] as List<MenuItem>)
    }

    @Override
    Menu parternsMenu() {
        new Menu(items: [
                new TextMenuItem(intro: 'GORM\'s sources is available at ', href: 'https://github.com/grails/grails-data-mapping', title: 'Github'),
                new TextMenuItem(intro: 'GORM\'s repositories is hosted by', title: 'Artifactory', href: 'http://artifactoryonline.com/'),
                new TextMenuItem(intro: 'GORM is Open Source', title:'Apache 2 License', href: 'http://www.apache.org/licenses/LICENSE-2.0.html'),
        ] as List<MenuItem>)
    }

    @Override
    Menu socialMediaMenu() {
        new Menu(items: [
                new TextMenuItem(href: 'https://github.com/grails/grails-data-mapping',
                        title: ''),
                new TextMenuItem(href: 'https://github.com/grails/grails-data-mapping',
                        title: ''),
                new TextMenuItem(href: 'https://github.com/grails/grails-data-mapping',
                        title: ''),
                new TextMenuItem(href: 'https://github.com/grails/grails-data-mapping',
                        title: ''),
        ] as List<MenuItem>)
    }

}
