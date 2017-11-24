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
import org.grails.main.pages.ProfilesPage
import org.grails.main.pages.SearchPage
import org.grails.main.pages.CommunityPage
import org.grails.model.GrailsVersion
import org.grails.pages.HtmlPage
import org.grails.main.pages.QuestionPage
import org.grails.main.pages.SupportPage

@CompileStatic
class SiteMap {

    public final static ProfileGroup PROFILES = new ProfileGroup(title: 'Profiles', image: 'images/profiles.svg',
            description: 'Profiles encapsulate the project commands, templates, and plugins that are designed to work with a given profile. The source for the profiles can be found on <a href="http://github.com/grails-profiles/">GitHub</a>, while the profiles themselves are published as JAR files in the Grails central repository.',
            profiles: [
                    new Profile(title: 'Web', docsHref: 'https://grails-profiles.github.io/web/latest/guide/index.html'),
                    new Profile(title: 'REST API', docsHref: 'https://grails-profiles.github.io/rest-api/latest/guide/index.html'),
                    new Profile(title: 'AngularJS', docsHref: 'https://grails-profiles.github.io/angularjs/latest/guide/index.html'),
                    new Profile(title: 'Angular', docsHref: 'https://grails-profiles.github.io/angular/latest/guide/index.html'),
                    new Profile(title: 'React', docsHref: 'https://grails-profiles.github.io/react/latest/guide/index.html'),
                    new Profile(title: 'React-Webpack', docsHref: 'https://grails-profiles.github.io/react-webpack/latest/guide/index.html'),
                    new Profile(title: 'Webpack', docsHref: 'https://grails-profiles.github.io/webpack/latest/guide/index.html'),
    ])

    public final static ProfileGroup PLUGIN_PROFILES = new ProfileGroup(title: 'Plugin Profiles', image: 'images/profiles.svg',
            profiles: [
                    new Profile(title: 'Plugin', docsHref: 'https://grails-profiles.github.io/plugin/latest/guide/index.html'),
                    new Profile(title: 'REST API Plugin', docsHref: 'https://grails-profiles.github.io/rest-api-plugin/latest/guide/index.html'),
                    new Profile(title: 'Web Plugin', docsHref: 'https://grails-profiles.github.io/web-plugin/latest/guide/index.html'),
            ])

    public final static ProfileGroup THIRD_PARTY_PROFILES =  new ProfileGroup(title: 'Third-Party Profiles', image: 'images/profiles.svg',
            description: 'Use the following documentation to learn how to <a href="http://docs.grails.org/latest/guide/single.html#creatingProfiles">create your own application profiles</a>.',
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
            new Book(title: 'Grails 3 - Step by Step', author: 'Cristian Olaru', href: 'https://grailsthreebook.com/', image: 'books/grails_3_step_by_step.png', about: 'Learn how a complete greenfield application can be implemented quickly and efficiently with Grails 3 using profiles and plugins. Use the sample application that accompanies the book as an example.'),
            new Book(title: 'Practical Grails 3', author: ' Eric Helgeson', href: 'https://www.grails3book.com/', image: 'books/pratical-grails-3-book-cover.png', about: 'Learn the fundamental concepts behind building Grails applications with the first book dedicated to Grails 3. Real, up-to-date code examples are provided, so you can easily follow along.'),
            new Book(title: 'Falando de Grails', author: 'Henrique Lobo Weissmann', href: 'http://www.casadocodigo.com.br/products/livro-grails', image: 'books/grails_weissmann.png', about: 'This is the best reference on Grails 2.5 and 3.0 written in Portuguese. It&#39;s a great guide to the framework, dealing with details that many users tend to ignore.'),
            new Book(title: 'Grails Goodness Notebook', author: 'Hubert A. Klein Ikkink', href: 'https://leanpub.com/grails-goodness-notebook', image: 'books/grailsgood.png', about: 'Experience the Grails framework through code snippets. Discover (hidden) Grails features through code examples and short articles. The articles and code will get you started quickly and provide deeper insight into Grails.'),
            new Book(title: 'The Definitive Guide to Grails 2', author: 'Jeff Scott Brown and Graeme Rocher', href: 'http://www.apress.com/9781430243779', image: 'books/grocher_jbrown_cover.jpg', about: 'As the title states, this is the definitive reference on the Grails framework, authored by core members of the development team.'),
            new Book(title: 'Grails in Action', author: 'Glen Smith and Peter Ledbrook', href: 'http://www.manning.com/gsmith2/', image: 'books/gsmith2_cover150.jpg', about: 'The second edition of Grails in Action is a comprehensive introduction to Grails 2 focused on helping you become super-productive fast.'),
            new Book(title: 'Grails 2: A Quick-Start Guide', author: 'Dave Klein and Ben Klein', href: 'http://www.amazon.com/gp/product/1937785777?tag=misa09-20', image: 'books/bklein_cover.jpg', about: 'This revised and updated edition shows you how to use Grails by iteratively building a unique, working application.'),
            new Book(title: 'Programming Grails', author: 'Burt Beckwith', href: 'http://shop.oreilly.com/product/0636920024750.do', image: 'books/bbeckwith_cover.gif', about: 'Dig deeper into Grails architecture and discover how this application framework works its magic.'),
    ]


    public final static List<Book> GROOVY_BOOKS = [
            new Book(title: 'Making Java Groovy', author: 'Ken Kousen', href: 'http://www.manning.com/kousen/', image: 'books/Kousen-MJG.png', about: 'Make Java development easier by adding Groovy. Each chapter focuses on a task Java developers do, like building, testing, or working with databases or restful web services, and shows ways Groovy can make those tasks easier.'),
            new Book(title: 'Groovy in Action, 2nd Edition', author: 'Dierk König, Guillaume Laforge, Paul King, Cédric Champeau, Hamlet D\'Arcy, Erik Pragt, and Jon Skeet', href: 'http://www.manning.com/koenig2/', image: 'books/regina.png', about: 'This is the undisputed, definitive reference on the Groovy language, authored by core members of the development team.'),
            new Book(title: 'Groovy for Domain-Specific Languages', author: 'Fergal Dearle', href: 'http://www.packtpub.com/groovy-for-domain-specific-languages-dsl/book', image: 'books/gdsl.jpg', about: 'Learn how Groovy can help Java developers easily build domain-specific languages into their applications.'),
            new Book(title: 'Groovy 2 Cookbook', author: 'Andrey Adamovitch, Luciano Fiandeso', href: 'http://www.packtpub.com/groovy-2-cookbook/book', image: 'books/g2cook.jpg', about: 'This book contains more than 90 recipes that use the powerful features of Groovy 2 to develop solutions to everyday programming challenges.'),
            new Book(title: 'Programming Groovy 2', author: 'Venkat Subramaniam', href: 'http://pragprog.com/book/vslg2/programming-groovy-2', image: 'books/vslg2.jpg', about: 'This book helps experienced Java developers learn to use Groovy 2, from the basics of the language to its latest advances.'),
    ]

    public final static List<Event> EVENTS = [
            new Event(image: 'confs/g3summit2017.png',
                    href: 'http://g3summit.com/',
                    title: 'G3 Summit 2017',
                    location: 'Austin, TX, United States',
                    dates: 'Nov 28 - Dec 1, 2017',
                    about: '''
Save $200 with this [G3 Summit discount code](https://objectcomputing.com/news/2017/09/08/weve-got-your-200-discount-g3-summit).

The G3 Summit is an annual event for the Apache Groovy, Grails, and Gradle (G3) community. This is your chance to explore Apache Groovy/Grails/Gradle technologies with the guidance of project leaders, committers, authors, and industry experts.
'''),
            new Event(image: 'confs/greach.png',
                    href: 'http://greachconf.com',
                    title: 'Greach: The Groovy Spanish Conference',
                    location: 'Madrid, Spain',
                    dates: 'March 15 - 17, 2018',
                    about: 'Enhance your knowledge and skills with some of the most recognizable names from the Groovy development scene from all around the world. Experience three days full of Groovy and Grails talks and networking ... plus our famous Friday Beers Party!'),
            new Event(image: 'confs/gr8confeu.png',
                    href: 'http://gr8conf.eu/',
                    title: 'GR8Conf EU 2018',
                    location: 'Copenhagen, Denmark',
                    dates: 'May 30 - June 1, 2018',
                    about: '''
Groovy, Grails, and related technologies have seen astounding growth in interest and adoption over the past few years, and with good reason. The GR8Conf was founded to spread the word worldwide. The 2018 GR8Conf Europe is celebrating its 10th year, and it's expected to be a blast. As in 2017, the conference will feature a DevOps day with a focus on technologies designed to support your everyday DevOps needs. GR8Conf is an independent, affordable series of conferences and covers all things Groovy'''),
    ]

    public final static List<String> VERSIONS = [
            '3.3.2',
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
    static List<String> olderVersions() {
        List<GrailsVersion> grailsVersionList = OLDER_VERSIONS.collect { String version ->
            GrailsVersion.build(version)
        }
        Collections.sort(grailsVersionList)
        grailsVersionList = grailsVersionList.reverse()
        grailsVersionList.collect { GrailsVersion grailsVersion -> grailsVersion.versionText }
    }
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

    public final static DocumentationGroup DOCUMENTATION_PROFILES = new DocumentationGroup([image: 'images/profiles.svg', title: 'Grails Profiles', description: 'Grails Profiles encapsulate  project commands, templates, and plugins. They facilitate the construction of REST APIs, web applications with a JavaScript frontend, and more.', links: [
            new DocumentationLink([title: 'Grails Profiles', href: '/profiles.html']),
    ]])
    public final static DocumentationGroup DOCUMENTATION_UPGRADE = new DocumentationGroup([image: 'images/upgrade.svg', title: 'Upgrade', links: [
            new DocumentationLink([title: 'Upgrade Documenation', href: 'https://grails.github.io/grails-upgrade/latest/guide/index.html']),
    ]])
    public final static DocumentationGroup DOCUMENTATION_ASYNC = new DocumentationGroup([image: 'images/async.svg', title: 'Async', links: [
        new DocumentationLink([title: 'Async Grails', href: 'https://async.grails.org/latest/guide/index.html']),
    ]])
    public final static DocumentationGroup DOCUMENTATION_TESTING = new DocumentationGroup([image: 'images/testing.svg', title: 'Testing', links: [
            new DocumentationLink([title: 'Testing Framework', href: 'https://testing.grails.org/latest/guide/index.html'])
    ]])

    public final static DocumentationGroup DOCUMENTATION_GORM = new DocumentationGroup([image: 'images/gorm.svg', href: 'http://gorm.grails.org', title: 'GORM - Data Access Toolkit', links: [
            new DocumentationLink([title: 'GORM Hibernate', href: 'http://gorm.grails.org/latest/hibernate/manual/index.html']),
            new DocumentationLink([title: 'GORM MongoDb', href: 'http://gorm.grails.org/latest/mongodb/manual/index.html']),
            new DocumentationLink([title: 'GORM Neo4j', href: 'http://gorm.grails.org/latest/neo4j/manual/index.html']),
            new DocumentationLink([title: 'Graphql', href: 'https://grails.github.io/gorm-graphql/snapshot/guide/index.html']),
            new DocumentationLink([title: 'More about GORM', href: 'http://gorm.grails.org/']),
    ]])

    public final static DocumentationGroup DOCUMENTATION_VIEWS = new DocumentationGroup([image: 'images/views.svg', title: 'Views', links: [
            new DocumentationLink([title: 'GSP', href: 'http://gsp.grails.org/']),
            new DocumentationLink([title: 'JSON Views', href: 'http://views.grails.org/latest/']),
            new DocumentationLink([title: 'Markup Views', href: 'http://views.grails.org/latest/#_markup_views']),
            new DocumentationLink([title: 'Grails + React', href: 'https://grails-profiles.github.io/react/latest/guide/index.html']),
            new DocumentationLink([title: 'Grails + Angular', href: 'https://grails-profiles.github.io/angular/latest/guide/index.html']),
            new DocumentationLink([title: 'Grails + AngularJS', href: 'https://grails-profiles.github.io/angularjs/latest/guide/index.html'])
    ]])

    public final static DocumentationGroup DOCUMENTATION_SECURITY = new DocumentationGroup([image: 'images/security.svg', title: 'Security', links: [
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
    ]])

    public final static DocumentationGroup DOCUMENTATION_DATABASE = new DocumentationGroup([image: 'images/relationaldb.svg', title: 'Database', links: [
            new DocumentationLink(title: 'Database Migration Plugin',
                    href: 'http://grails-plugins.github.io/grails-database-migration/3.0.x/index.html',
                    vcs: 'https://github.com/grails-plugins/grails-database-migration'),
    ]])


    public final static DocumentationGroup DOCUMENTATION_BUILDSTATUS = new DocumentationGroup([image: 'images/buildstatus.svg', title: 'Build Status', links: [
            new DocumentationLink(title: 'Build Status', href: '/buildstatus.html'),
    ]])

    public final static List<Question> QUESTIONS = [
            new Question(slug: 'question_opensource', title: 'Is Grails an Open Source project?', answer: '''
Yes, Grails is an Open Source project licensed under the [Apache License v2](http://www.apache.org/licenses/LICENSE-2.0). You can see the license header in all the source files, as well as a [license file](https://github.com/grails/grails-core/blob/master/LICENSE) at the root of the project
'''),
            new Question(slug: 'question_learn', title: 'What is the best way to learn about Grails?', answer: '''
Read the [User Guide](https://grails.org/documentation.html) for the version you are planning to use. We recommend you start with the latest stable version of the framework. We have also written a collection of [Guides](http://guides.grails.org), which contain step-by-step tutorials for solving common scenarios.
'''),
            new Question(slug: 'question_training', title: 'Do you offer Grails training?', answer: '''
[OCI](http://objectcomputing.com), sponsor of Grails' development, offers [Grails and GORM courses](https://objectcomputing.com/training/catalog/grails/) developed and delivered by Grails co-founders and the core engineering team.</p>
'''),
            new Question(slug: 'question_usage', title: 'Where can I ask questions about usage?', answer: '''
The best place to get community support is [Stack Overflow](http://stackoverflow.com/questions/tagged/grails) or Slack- [Login](http://grails.slack.com) or [Join](https://grails.signup.team/) Grails Community Slack.
'''),
            new Question(slug: 'question_socialmedia', title: 'Where can I find Grails on social media?', answer: '''
You can find Grails on [Twitter](http://twitter.com/grailsframework) and [LinkedIn](https://www.linkedin.com/groups/39757).
'''),
            new Question(slug: 'question_mailinglist',
                    title: 'Do you have a mailing list?',
                    answer: '''
Yes. Subscribe to our Google Group [here](https://groups.google.com/forum/#!forum/grails-dev-discuss). 
'''),
            new Question(slug: 'question_feature',
                         title: 'Where can I suggest a new feature or share an interesting finding?',
                         answer: '''
The [Grails mailing list](https://groups.google.com/forum/#!forum/grails-dev-discuss) is a great place to start a conversation with the Grails developers and other Grails users. 
'''),
            new Question(slug: 'question_mailinglist_vs_stackoverflow',
                    title: 'What\'s the difference between the mailing list and interaction on Stack Overflow?',
                    answer: '''
Use the [mailing list](https://groups.google.com/forum/#!forum/grails-dev-discuss) for discussion around the framework's development. For questions we recommend [Stack Overflow](http://stackoverflow.com/questions/tagged/grails)
'''),
            new Question(slug: 'question_issue', title: 'How do I report an issue with the Grails framework?', answer: '''
The Grails project uses [Github issues](https://github.com/grails/grails-core/issues) to report and track issues, feature enhancements, and new features. Make sure you're logged-in before proceeding. [Report an issue](https://github.com/grails/grails-core/issues/new).
'''),
            new Question(slug: 'question_docs', title: 'How can I contribute to improving the documentation?', answer: '''
Grails documentation comes in several forms:

- the [reference documentation](http://docs.grails.org/latest/guide/single.html) contains language specification, user guides, a getting started tutorial, and more.
- the [GroovyDoc APIs](api.html) documents the classes of the Grails code base
- [GitHub](https://github.com/grails/grails-static-website) allows users to contribute to this website.   

Contributing to the Grails.org website is fairly easy. Create a GitHub account or sign in with an existing account, then open [Grails.org](http://docs.grails.org/latest/guide/single.html) and select the "Improve this doc" button at the top of the page you wish to edit. Please don't hesitate to help us make improvements, fix typos or broken language, clarify complicated sections, add new material, and anything else you feel will be helpful to other Grails users.
'''),
            new Question(slug: 'question_code', title: 'How can I contribute to Grails code?', answer: '''
If you are looking to make an initial contribution, just raise your hand on the Grails developer [mailing-list](https://groups.google.com/forum/#!forum/grails-dev-discuss) and tell us about your desire to work on a particular problem.

For more complicated tasks, the best approach is to bring your interest to the attention of the Grails developers. They can provide guidance on how best to tackle a particular problem, collaborate on implementation ideas, and discuss the semantics or scope of the proposed change.

### Cloning the code base

To work on the Grails code base, you should be proficient in [Git](http://git-scm.com/) and (ideally) have an account on [GitHub](https://github.com/), so you can create [pull requests](https://help.github.com/articles/creating-a-pull-request) with your changes.

If you have Git installed on your machine, you should be able to clone the Grails repository with the following command:

    git clone git://github.com/grails/grails-core.git

Make sure you configure Git appropriately with the same email that you registered with on GitHub.

    git config --global user.name "YOUR NAME"
    git config --global user.email "YOUR EMAIL"</code></pre>

You can verify these are configured appropriately by running:

    git config --list

### Working on the code base

If you are working with the IntelliJ IDEA development environment, you can import the project using the Intellij Gradle Tooling ( "File / Import Project" and select the "build.gradle" file).

To get a local development version of Grails working, first run the install task.

    ./gradlew install

Then install [SDKman](http://sdkman.io), which is the quickest way to set up a development environment.

Once you have SDKman installed, point SDKman to your local development version of Grails.

    sdk install grails dev /path/to/checkout
    sdk use grails dev

Now the "grails" command will be using your development version!

The most important command you will have to run before sending your changes is the test command.

    ./gradlew test

For a successful contribution, all tests should be green!

### Creating a pull request

Once you are satisfied with your changes:

- commit your changes in your local branch
- push your changes to your remote branch on GitHub
- send us a [pull request](https://help.github.com/articles/creating-a-pull-request)
'''),
            new Question(slug: 'question_buildstatus', title: 'Where can I view the build status?', answer: '''
Grails sources are tested using our [continuous integration server](buildstatus.html).
'''),
            new Question(slug: 'question_sponsors', title: 'Who sponsors Grails development?', answer: '''

The Grails project is generously sponsored by several companies:

- [OCI](http://objectcomputing.com/products/grails/) maintains the Grails framework and employs key members of the Grails team.
- [Pivotal](http://pivotal.io/) provides website hosting for Grails.org.
- [JFrog](http://www.jfrog.com/) provides the infrastructure for deploying and hosting our snapshots and releases, thanks to the [Bintray](https://bintray.com/) social platform for distribution and the OSS [Artifactory](http://www.jfrog.com/home/v_artifactory_opensource_overview) repository.
- YourKit supports open source projects with its full-featured Java Profiler. YourKit, LLC is the creator of [YourKit Java Profiler](https://www.yourkit.com/java/profiler/index.jsp) and [YourKit .NET Profiler](https://www.yourkit.com/.net/profiler/index.jsp), innovative and intelligent tools for profiling Java and .NET applications.                                     
'''),
    ]

}
