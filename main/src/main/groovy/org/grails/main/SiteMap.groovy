package org.grails.main

import groovy.transform.CompileStatic
import org.grails.main.model.Book
import org.grails.main.model.BuildStatus
import org.grails.main.model.DocumentationGroup
import org.grails.main.model.DocumentationLink
import org.grails.main.model.Event
import org.grails.main.model.Profile
import org.grails.main.model.ProfileGroup
import org.grails.main.model.Question
import org.grails.main.model.UserGroup
import org.grails.main.pages.BooksPage
import org.grails.main.pages.DocumentationPage
import org.grails.main.pages.DownloadPage
import org.grails.main.pages.HomePage
import org.grails.main.pages.LearningPage
import org.grails.main.pages.ProfilesPage
import org.grails.main.pages.SearchPage
import org.grails.main.pages.CommunityPage
import org.grails.pages.HtmlPage
import org.grails.main.pages.QuestionPage
import org.grails.main.pages.SupportPage

@CompileStatic
class SiteMap {

    public final static ProfileGroup PROFILES = new ProfileGroup(title: 'Profiles', image: 'images/profiles.svg',
            description: 'Profiles encapsulate the project commands, templates and plugins that are designed to work for a given profile. The source for the profiles can be found on <a href="http://github.com/grails-profiles/">Github</a>, whilst the profiles themselves are published as JAR files to the Grails central repository.',
            profiles: [
                    new Profile(title: 'Web', docsHref: 'https://grails-profiles.github.io/web/latest/guide/index.html'),
                    new Profile(title: 'Rest API', docsHref: 'https://grails-profiles.github.io/rest-api/latest/guide/index.html'),
                    new Profile(title: 'AngularJS', docsHref: 'https://grails-profiles.github.io/angularjs/latest/guide/index.html'),
                    new Profile(title: 'Angular', docsHref: 'https://grails-profiles.github.io/angular/latest/guide/index.html'),
                    new Profile(title: 'React', docsHref: 'https://grails-profiles.github.io/react/latest/guide/index.html'),
                    new Profile(title: 'Webpack', docsHref: 'https://grails-profiles.github.io/webpack/latest/guide/index.html'),
    ])

    public final static ProfileGroup PLUGIN_PROFILES = new ProfileGroup(title: 'Plugin Profiles', image: 'images/profiles.svg',
            profiles: [
                    new Profile(title: 'Plugin', docsHref: 'https://grails-profiles.github.io/plugin/latest/guide/index.html'),
                    new Profile(title: 'Rest API Plugin', docsHref: 'https://grails-profiles.github.io/rest-api-plugin/latest/guide/index.html'),
                    new Profile(title: 'Web Plugin', docsHref: 'https://grails-profiles.github.io/web-plugin/latest/guide/index.html'),
            ])

    public final static ProfileGroup THIRD_PARTY_PROFILES =  new ProfileGroup(title: 'Third-Party Profiles', image: 'images/profiles.svg',
            description: 'It is possible to create your own Application Profiles. Check the documentation to learn how to <a href="http://docs.grails.org/latest/guide/single.html#creatingProfiles">Create Profiles</a>.',
            profiles: [
            new Profile(title: 'Vaadin', href: 'https://github.com/macprzepiora/web-vaadin8'),
            new Profile(title: 'Ember', href: 'https://github.com/hgarfer/grails-profile-ember'),
    ])

    public final static List<HtmlPage> PAGES = [
            new HomePage(),
            new DocumentationPage(),
            new DownloadPage(),
            new QuestionPage(),
            new SupportPage(),
            new CommunityPage(),
            new LearningPage(),
            new BooksPage(),
            new ProfilesPage(),
            new SearchPage(),
    ] as List<HtmlPage>

    public final static List<BuildStatus> BUILDS = [
            new BuildStatus([title: 'Grails Master',
                             href: "https://travis-ci.org/grails/grails-core?branch=master", badge: "https://travis-ci.org/grails/grails-core.svg?branch=master"]),
            new BuildStatus([title: 'Grails 3.1.x Branch',
                             href: 'https://travis-ci.org/grails/grails-core?branch=3.1.x', badge: "https://travis-ci.org/grails/grails-core.svg?branch=3.1.x"]),
            new BuildStatus([title: 'Grails 3.0.x Branch',
                             href: "https://travis-ci.org/grails/grails-core?branch=3.0.x", badge: "https://travis-ci.org/grails/grails-core.svg?branch=3.0.x"]),
            new BuildStatus([title: 'Grails 2.5.x Branch',
                             href: "https://travis-ci.org/grails/grails-core?branch=2.5.x", badge: "https://travis-ci.org/grails/grails-core.svg?branch=2.5.x"]),
            new BuildStatus([title: 'Grails 2.4.x Branch',
                             href: "https://travis-ci.org/grails/grails-core?branch=2.4.x", badge: "https://travis-ci.org/grails/grails-core.svg?branch=2.4.x"]),
            new BuildStatus([title: 'Grails 2.3.x Branch',
                             href: "https://travis-ci.org/grails/grails-core?branch=2.3.x", badge: "https://travis-ci.org/grails/grails-core.svg?branch=2.3.x"]),
    ]

    public final static List<Book> GRAILS_BOOKS = [
            new Book(title: 'Grails 3 - Step by Step', author: 'Cristian Olaru', href: 'https://grailsthreebook.com/', image: 'books/grails_3_step_by_step.png', about: 'We try to describe in this book how a complete greenfield application can be implemented with Grails 3 in a fast way using profiles and plugins - and we do this in the sample application that is accompanying this book.'),
            new Book(title: 'Practical Grails 3', author: ' Eric Helgeson', href: 'https://www.grails3book.com/', image: 'books/pratical-grails-3-book-cover.png', about: 'The first book dedicated to Grails 3. You will learn the concepts behind building Grails applications. Real, up-to-date code examples are provided so you can easily follow along.'),
            new Book(title: 'Falando de Grails', author: 'Henrique Lobo Weissmann', href: 'http://www.casadocodigo.com.br/products/livro-grails', image: 'books/grails_weissmann.png', about: 'The best reference on Grails (2.5 and 3.0) in portuguese. A great guide to the framework, dealing with details of the framework that many times are ignored by it\'s users.'),
            new Book(title: 'Grails Goodness Notebook', author: 'Hubert A. Klein Ikkink', href: 'https://leanpub.com/grails-goodness-notebook', image: 'books/grailsgood.png', about: 'Experience the Grails platform through code snippets. Learn more about (hidden) Grails features with code snippets and short articles. The articles and code will get you started quickly and will give more insight in Grails.'),
            new Book(title: 'The Definitive Guide to Grails 2', author: 'Jeff Scott Brown and Graeme Rocher', href: 'http://www.apress.com/9781430243779', image: 'books/grocher_jbrown_cover.jpg', about: 'Definitive reference on The Grails Framework, authored by core members of the development team.'),
            new Book(title: 'Grails in Action', author: 'Glen Smith and Peter Ledbrook', href: 'http://www.manning.com/gsmith2/', image: 'books/gsmith2_cover150.jpg', about: 'Grails in Action, Second Edition is a comprehensive introduction to Grails 2 focused on making you super-productive fast.'),
            new Book(title: 'Grails 2: A Quick-Start Guide', author: 'Dave Klein and Ben Klein', href: 'http://www.amazon.com/gp/product/1937785777?tag=misa09-20', image: 'books/bklein_cover.jpg', about: 'This revised and updated new edition shows you how to use Grails by iteratively building a unique, working application.'),
            new Book(title: 'Programming Grails', author: 'Burt Beckwith', href: 'http://shop.oreilly.com/product/0636920024750.do', image: 'books/bbeckwith_cover.gif', about: 'Dig deeper into Grails architecture and discover how this application framework works its magic.'),
    ]


    public final static List<Book> GROOVY_BOOKS = [
            new Book(title: 'Making Java Groovy', author: 'Ken Kousen', href: 'http://www.manning.com/kousen/', image: 'books/Kousen-MJG.png', about: 'Make Java development easier by adding Groovy. Each chapter focuses on a task Java developers do, like building, testing, or working with databases or restful web services, and shows ways Groovy can help.'),
            new Book(title: 'Groovy in Action, 2nd edition', author: 'Dierk König, Guillaume Laforge, Paul King, Cédric Champeau, Hamlet D\'Arcy, Erik Pragt, and Jon Skeet', href: 'http://www.manning.com/koenig2/', image: 'books/regina.png', about: 'The undisputed definitive reference on The Groovy Language, authored by core members of the development team.'),
            new Book(title: 'Groovy for Domain-Specific Languages', author: 'Fergal Dearle', href: 'http://www.packtpub.com/groovy-for-domain-specific-languages-dsl/book', image: 'books/gdsl.jpg', about: 'Enhance and extend your Java applications with Domain-Specific Languages in Groovy'),
            new Book(title: 'Groovy 2 Cookbook', author: 'Andrey Adamovitch, Luciano Fiandeso', href: 'http://www.packtpub.com/groovy-2-cookbook/book', image: 'books/g2cook.jpg', about: 'Over 90 recipes that provide solutions to everyday programming challenges using the powerful features of Groovy 2'),
            new Book(title: 'Programming Groovy 2', author: 'Venkat Subramaniam', href: 'http://pragprog.com/book/vslg2/programming-groovy-2', image: 'books/vslg2.jpg', about: 'Dynamic productivity for the Java developer'),
    ]

    public final static List<Event> EVENTS = [
            new Event(image: 'confs/g3summit2017.png',
                    href: 'http://g3summit.com/',
                    title: 'G3 Summit 2017',
                    location: 'Austin, TX, United States',
                    dates: 'Nov 28 - Dec 1, 2017',
                    about: '''
Save $200 with this [G3 Summit discount code](https://objectcomputing.com/news/2017/09/08/weve-got-your-200-discount-g3-summit).

The G3 Summit is an annual event for the Apache Groovy, Grails, and Gradle (G3) community. This is your chance to learn awesome Apache Groovy/Grails/Gradle technologies from project leaders, committers, authors, and industry experts.
'''),
            new Event(image: 'confs/greach.png',
                    href: 'http://greachconf.com',
                    title: 'GR8Conf EU 2018',
                    location: 'Madrid, Spain',
                    dates: 'March 16 - 17, 2018',
                    about: 'Some of the most recognizable names from the Groovy development scene from all around the world. Three days of full of Groovy and Grails talks and interesting people to talk and our famous Friday Beers Party!'),
            new Event(image: 'confs/gr8confeu.png',
                    href: 'http://gr8conf.eu/',
                    title: 'GR8Conf EU 2018',
                    location: 'Copenhagen, Denmark',
                    dates: 'May 30 - June 1, 2018',
                    about: '''
Groovy, Grails and the related technologies have seen astounding growth in interest and adoption the past few years, and with good reason. To spread the word even more we have created GR8Conf.
The 2018 Edition of GR8Conf Europe is the 10th edition and will be a blast. Like in 2017 it will feature a DevOps day. Focus will be on technologies to support your everyday DevOps needs.
GR8Conf is an independent, affordable series of conferences and covers All Things Groovy'''),
    ]

    public final static List<String> VERSIONS = [
            '3.3.1',
            '3.3.0',
            '3.3.0.RC1',
            '3.3.0.M2',
            '3.3.0.M1',
            '3.2.9',
            '3.2.8',
            '3.2.7',
            '3.2.6',
            '3.2.5',
            '3.2.4',
            '3.2.3',
            '3.2.2',
            '3.2.11',
            '3.2.10',
            '3.2.1',
            '3.2.0.RC2',
            '3.2.0.RC1',
            '3.2.0.M2',
            '3.2.0.M1',
            '3.2.0',
            '3.1.9',
            '3.1.8',
            '3.1.7',
            '3.1.6',
            '3.1.5',
            '3.1.4',
            '3.1.3',
            '3.1.2',
            '3.1.16',
            '3.1.15',
            '3.1.14',
            '3.1.13',
            '3.1.12',
            '3.1.11',
            '3.1.10',
            '3.1.1',
            '3.1.0.RC2',
            '3.1.0.RC1',
            '3.1.0.M3',
            '3.1.0.M2',
            '3.1.0',
            '3.0.9',
            '3.0.8',
            '3.0.7',
            '3.0.6',
            '3.0.5',
            '3.0.4',
            '3.0.3',
            '3.0.2',
            '3.0.17',
            '3.0.16',
            '3.0.15',
            '3.0.14',
            '3.0.13',
            '3.0.12',
            '3.0.11',
            '3.0.10',
            '3.0.1',
            '3.0.0',
            '2.5.6',
            '2.5.5',
            '2.5.4',
            '2.5.3',
            '2.5.2',
            '2.5.1',
            '2.5.0',
            '2.4.5',
            '2.4.4',
            '2.4.3',
            '2.4.2',
            '2.4.1',
            '2.4.0',
            '2.3.9',
            '2.3.8',
            '2.3.7',
            '2.3.6',
            '2.3.5',
            '2.3.4',
            '2.3.3',
            '2.3.2',
            '2.3.11',
            '2.3.10',
            '2.3.1',
            '2.3.0',
            '2.2.5',
            '2.2.4',
            '2.2.3',
            '2.2.2',
            '2.2.1',
            '2.2.0',
            '2.1.5',
            '2.1.4',
            '2.1.3',
            '2.1.2',
            '2.1.1',
            '2.1.0',
            '2.0.4',
            '2.0.3',
            '2.0.2',
            '2.0.1',
            '2.0.0',
            '1.3.9',
            '1.3.8',
            '1.3.7',
            '1.3.6',
            '1.3.5',
            '1.3.4',
            '1.3.3',
            '1.3.2',
            '1.3.1',
            '1.3.0',
            '1.2.5',
            '1.2.4',
            '1.2.3',
            '1.2.2',
            '1.2.1',
            '1.2.0'
    ]

    public final static String LATEST_VERSION = VERSIONS[0]
    public final static List<String> OLDER_VERSIONS = VERSIONS.drop(1)

    public final static List<UserGroup> USER_GROUPS = [
            new UserGroup(region: 'North-America', country: 'United States', title: 'Austin Groovy and Grails User Group (TX)', href: 'http://www.meetup.com/Austin-Groovy-and-Grails-Users/'),
            new UserGroup(region: 'North-America', country: 'United States', title: 'Boston Groovy, Grails, Spring Meetup (B2GS)', href: 'https://www.meetup.com/Grails-Boston/'),
            new UserGroup(region: 'North-America', country: 'United States', title: 'Coder Consortium of Sacramento', href: 'http://coderconsortium.com/'),
            new UserGroup(region: 'North-America', country: 'United States', title: 'DC Groovy', href: 'http://www.dcgroovy.org/'),
            new UserGroup(region: 'North-America', country: 'United States', title: 'DFW Groovy & Grails User Group', href: 'http://dfw2gug.org/'),
            new UserGroup(region: 'North-America', country: 'United States', title: 'Groovy and Grails Users of Columbus OH', href: 'http://www.meetup.com/Grails-and-Ales/'),
            new UserGroup(region: 'North-America', country: 'United States', title: 'Groovy Users of Minnesota', href: 'http://groovy.mn/'),
            new UserGroup(region: 'North-America', country: 'United States', title: 'Los Angeles Groovy Users Group', href: 'https://www.meetup.com/Los-Angeles-GUG/'),
            new UserGroup(region: 'North-America', country: 'United States', title: 'NYC Groovy / Grails Meetup', href: 'http://www.meetup.com/grails/'),
            new UserGroup(region: 'North-America', country: 'United States', title: 'Scottsdale Groovy Brigade', href: 'http://www.scottsdale-groovy.com/'),
            new UserGroup(region: 'North-America', country: 'United States', title: 'SF Bay Groovy and Grails Meetup Group', href: 'http://www.meetup.com/java-161/'),
            new UserGroup(region: 'North-America', country: 'United States', title: 'Silicon Valley Groovy/Grails Centro', href: 'http://www.meetup.com/San-Francisco-Grails-Centro/'),
            new UserGroup(region: 'North-America', country: 'United States', title: 'St. Louis Groovy & Grails Meetup', href: 'https://www.meetup.com/st-louis-groovy-and-grails-meetup/'),

            new UserGroup(region: 'South-America', country: 'Brazil', title: 'Grails Brasil - Groovy and Grails users group', href: 'http://www.grailsbrasil.com.br/'),

            new UserGroup(region: 'Asia', country: 'India', title: 'Bangalore Groovy Grails Meetup', href: 'http://www.meetup.com/Bangalore-Groovy-Grails-Meetup/'),
            new UserGroup(region: 'Asia', country: 'Japan', title: 'Japan Grails/Groovy User Group', href: 'http://www.jggug.org/'),

            new UserGroup(region: 'Europe', country: 'Belgium', title: 'Belgium Groovy/Grails User Group', href: 'http://www.meetup.com/Belgium-Groovy-Grails-User-Group/'),
            new UserGroup(region: 'Europe', country: 'Denmark', title: 'Aarhus Groovy & Grails meetup', href: ''),
            new UserGroup(region: 'Europe', country: 'France', title: 'Paris Groovy Grails User Group', href: 'http://www.meetup.com/Belgium-Groovy-Grails-User-Group/'),
            new UserGroup(region: 'Europe', country: 'Germany', title: 'Berlin Groovy User Group', href: 'http://www.meetup.com/Berlin-Groovy-User-Group/'),
            new UserGroup(region: 'Europe', country: 'Israel', title: 'Groovy & Grails Israel User Group', href: 'http://www.meetup.com/Groovy-Grails-Israel-Meetup-Group/'),
            new UserGroup(region: 'Europe', country: 'Poland', title: 'Warsaw Groovy User Group', href: 'http://www.meetup.com/Warsaw-Groovy-User-Group/'),
            new UserGroup(region: 'Europe', country: 'Spain', title: 'Madrid Groovy User Group', href: 'http://www.meetup.com/madrid-gug/'),
            new UserGroup(region: 'Europe', country: 'Holland', title: 'Dutch Groovy and Grails User Group (NLGUG)', href: 'http://www.meetup.com/nl-gug/'),
    ]

    public final static List<DocumentationGroup> DOCUMENTATION = [
            new DocumentationGroup([image: 'images/profiles.svg', title: 'Grails Profiles', description: 'Grails Profiles encapsulate  project commands, templates and plugins. They facilitate the construction of REST APIs, Web applications with a Javascript frontend etc.', links: [
                    new DocumentationLink([title: 'Grails Profiles', href: '/profiles.html']),
            ]]),
            new DocumentationGroup([image: 'images/upgrade.svg', title: 'Upgrade', links: [
                    new DocumentationLink([title: 'Upgrade Documenation', href: 'https://grails.github.io/grails-upgrade/latest/guide/index.html']),
            ]]),
            new DocumentationGroup([image: 'images/async.svg', title: 'Async', links: [
                    new DocumentationLink([title: 'Async Grails', href: 'https://async.grails.org/latest/guide/index.html']),
            ]]),
            new DocumentationGroup([image: 'images/testing.svg', title: 'Testing', links: [
                    new DocumentationLink([title: 'Testing Framework', href: 'https://testing.grails.org/latest/guide/index.html'])
            ]]),
            new DocumentationGroup([image: 'images/gorm.svg', href: 'http://gorm.grails.org', title: 'GORM - Data Access Toolkit', links: [
                    new DocumentationLink([title: 'GORM Hibernate', href: 'http://gorm.grails.org/latest/hibernate/manual/index.html']),
                    new DocumentationLink([title: 'GORM MongoDb', href: 'http://gorm.grails.org/latest/mongodb/manual/index.html']),
                    new DocumentationLink([title: 'GORM Neo4j', href: 'http://gorm.grails.org/latest/neo4j/manual/index.html']),
                    new DocumentationLink([title: 'Graphql', href: 'https://grails.github.io/gorm-graphql/snapshot/guide/index.html']),
                    new DocumentationLink([title: 'More about GORM', href: 'http://gorm.grails.org/']),
            ]]),
            new DocumentationGroup([image: 'images/views.svg', title: 'Views', links: [
                    new DocumentationLink([title: 'GSP', href: 'http://gsp.grails.org/']),
                    new DocumentationLink([title: 'JSON Views', href: 'http://views.grails.org/latest/']),
                    new DocumentationLink([title: 'Markup Views', href: 'http://views.grails.org/latest/#_markup_views']),
                    new DocumentationLink([title: 'Grails + React', href: 'https://grails-profiles.github.io/react/latest/guide/index.html']),
                    new DocumentationLink([title: 'Grails + Angular', href: 'https://grails-profiles.github.io/angular/latest/guide/index.html']),
                    new DocumentationLink([title: 'Grails + AngularJS', href: 'https://grails-profiles.github.io/angularjs/latest/guide/index.html'])
            ]]),
            new DocumentationGroup([image: 'images/security.svg', title: 'Security', links: [
                    new DocumentationLink(title: 'Spring Security Core',
                                          href: 'https://grails-plugins.github.io/grails-spring-security-core/',
                                          vcs: 'https://github.com/grails-plugins/grails-spring-security-core'),
                    new DocumentationLink(title: 'Spring Security REST',
                            href: 'https://github.com/alvarosanchez/grails-spring-security-rest',
                            vcs: 'http://alvarosanchez.github.io/grails-spring-security-rest/latest/docs/'),

                    new DocumentationLink(title: 'Spring Security - AppInfo',
                            href: 'https://grails-plugins.github.io/grails-spring-security-appinfo/',
                            vcs: 'https://github.com/grails-plugins/grails-spring-security-appinfo'),
                    new DocumentationLink(title: 'Spring Security - UI',
                            href: 'https://grails-plugins.github.io/grails-spring-security-ui/latest/',
                            vcs: 'https://github.com/grails-plugins/grails-spring-security-ui'),
                    new DocumentationLink(title: 'Spring Security - ACL',
                            href: 'https://grails-plugins.github.io/grails-spring-security-acl/',
                            vcs: 'https://github.com/grails-plugins/grails-spring-security-acl'),
                    new DocumentationLink(title: 'Spring Security - LDAP',
                            href: 'https://grails-plugins.github.io/grails-spring-security-ldap/',
                            vcs: 'https://github.com/grails-plugins/grails-spring-security-ldap'),
                    new DocumentationLink(title: 'Spring Security - CAS',
                            href: 'https://grails-plugins.github.io/grails-spring-security-cas/',
                            vcs: 'https://github.com/grails-plugins/grails-spring-security-cas'),
                    new DocumentationLink(title: 'Spring Security - Kerberos',
                            href: 'https://grails-plugins.github.io/grails-spring-security-kerberos/',
                            vcs: 'https://grails-plugins.github.io/grails-spring-security-kerberos/'),
            ]]),

            new DocumentationGroup([image: 'images/relationaldb.svg', title: 'Database', links: [
                    new DocumentationLink(title: 'Database Migration Plugin',
                            href: 'http://grails-plugins.github.io/grails-database-migration/3.0.x/index.html',
                            vcs: 'https://github.com/grails-plugins/grails-database-migration'),
            ]]),
            new DocumentationGroup([image: 'images/buildstatus.svg', title: 'Build Status', links: [
                    new DocumentationLink(title: 'Build Status', href: '/buildstatus.html'),
            ]]),
    ]

    public final static List<Question> QUESTIONS = [
            new Question(slug: 'question_opensource', title: 'Is Grails an Open Source project?', answer: '''
Yes, Grails is an Open Source project, licensed under the [Apache License v2](http://www.apache.org/licenses/LICENSE-2.0). You can see the license header in all the source files of the project, as well as a [license file](https://github.com/grails/grails-core/blob/master/LICENSE) at the root of the project
'''),
            new Question(slug: 'question_learn', title: 'What is the best way to learn about Grails?', answer: '''
Read the [User Guide](https://grails.org/documentation.html) of the version you are planning to use. We recommend you to start with the latest stable version of Grails.

Moreover, we have written a collection of [Guides](http://guides.grails.org) which are step-by-step tutorials to solve common scenarios.
'''),
            new Question(slug: 'question_training', title: 'Do you offer Grails Training?', answer: '''
[OCI](http://objectcomputing.com) (sponsor of Grails' development) offers [Grails and GORM courses](https://objectcomputing.com/training/catalog/grails/) developed and delivered by Grails co-founders and the core engineering team.</p>
'''),
            new Question(slug: 'question_usage', title: 'Where can I ask questions about usage?', answer: '''
The best place to get community support is [Stack Overflow](http://stackoverflow.com/questions/tagged/grails) or [Slack](http://grails.slack.com).
'''),
            new Question(slug: 'question_socialmedia', title: 'Where can I find Grails on Social Media?', answer: '''
You can find Grails on [Twitter](http://twitter.com/grailsframework) or [Linked-In](https://www.linkedin.com/groups/39757).
'''),
            new Question(slug: 'question_mailinglist',
                    title: 'Do you have a Mailing List?',
                    answer: '''
Yes, the Grails mailing list is a Google Group which you can suscribe to using the [following link](https://groups.google.com/forum/#!forum/grails-dev-discuss). 
'''),
            new Question(slug: 'question_feature',
                         title: 'Where can I suggest a new feature or interesting finding?',
                         answer: '''
The Grails mailing list is is a great place to go to to start a conversation with the Grails developers and other Grails users. 
'''),
            new Question(slug: 'question_mailinglist_vs_stackoverflow',
                    title: 'Mailing List vs Stack Overflow',
                    answer: '''
The mailing list is for discussion around the framework's development. For questions we recommend [StackOverflow](http://stackoverflow.com/questions/tagged/grails)
'''),
            new Question(slug: 'question_issue', title: 'Reporting issues', answer: '''
The Grails project uses [Github issues](https://github.com/grails/grails-core/issues) to report and track issues, feature enhancements, and new features. Be sure to be signed-up and logged-in, as explained below, before proceeding. [Report an issue](https://github.com/grails/grails-core/issues/new&quot;).
'''),
            new Question(slug: 'question_docs', title: 'Improving the documentation', answer: '''
The documentation of The Grails Framework comes in various forms:

- the [reference documentation](http://docs.grails.org/latest/guide/single.html) covering the language specification, the user guides, getting started, and more.
- the [GroovyDoc APIs](api.html) documenting the classes of the Grails code base
- this [website](https://github.com/grails/grails-static-website)

Contributing to this website is fairly easy, if you have a Github account already, as you can click on the **Improve this doc** buttons that you can see on all the pages of this website. So don't hesitate to help us improve it, fix typos, broken language, clarify complicated sections, add new material, etc.
'''),
            new Question(slug: 'question_code', title: 'Contributing code', answer: '''
If you know the area you want to contribute to, this is great, but if you are looking to make an initial contribution just raise your hand on the Grails developer [mailing-list](mailing-lists.html) to tell us about your desire to work on a particular problem.

For more complicated tasks, the best approach is also to bring that to the attention of the Grails developers, so they can give you some guidance on how best to tackle a particular problem, discuss implementation ideas and the semantics or scope of the proposed change.

### Cloning the code base

To work on the Grails code base, you should be proficient enough in [git](http://git-scm.com/) and you should ideally have an account on [Github](https://github.com/) to be able to create [pull requests](https://help.github.com/articles/creating-a-pull-request) with your changes.

If you have git installed on your machine, you should be able to clone the Grails repository with the following command:

    git clone git://github.com/grails/grails-core.git

Make sure you configure Git appropriately with the same email that you registered with on Github:

    git config --global user.name "YOUR NAME"
    git config --global user.email "YOUR EMAIL"</code></pre>

You can verify these are configured appropriately by running:

    git config --list

### Working on the code base

If you are working with the IntelliJ IDEA development environment then you can import the project using the Intellij Gradle Tooling ( "File / Import Project" and select the "build.gradle" file).

To get a local development version of Grails working first run the install task

    ./gradlew install

Then install [SDKman](http://sdkman.io) which is the quickest way to setup a development environment.

Once you have SDKman installed point SDKman to your local development version of Grails:

    sdk install grails dev /path/to/checkout
    sdk use grails dev

Now the "grails" command will be using your development version!

The most important command you will have to run before sending your changes is the test command:

    ./gradlew test

For a successful contribution, all tests should be green!

### Creating a pull request

Once you are satisfied with your changes:

- commit your changes in your local branch
- push your changes to your remote branch on Github
- send us a [pull requests](https://help.github.com/articles/creating-a-pull-request)
'''),
            new Question(slug: 'question_buildstatus', title: 'Build Status', answer: '''
The Grails sources are tested thanks our [continuous integration server](buildstatus.html)
'''),
            new Question(slug: 'question_sponsors', title: 'Who sponsors Grails development?', answer: '''

The Grails project is generously sponsored by several companies:

- [OCI](http://objectcomputing.com/products/grails/) employs key members of the Grails team,
- [Pivotal](http://pivotal.io/) provides website hosting.
- [JFrog](http://www.jfrog.com/) provides the infrastructure for deploying and hosting our snapshots and releases, thanks to the [Bintray](https://bintray.com/) social platform for distribution and the OSS [Artifactory](http://www.jfrog.com/home/v_artifactory_opensource_overview) repository.
- YourKit supports open source projects with its full-featured Java Profiler. YourKit, LLC is the creator of [YourKit Java Profiler](https://www.yourkit.com/java/profiler/index.jsp) and [YourKit .NET Profiler](https://www.yourkit.com/.net/profiler/index.jsp) innovative and intelligent tools for profiling Java and .NET applications.                                     
'''),
    ]

}
