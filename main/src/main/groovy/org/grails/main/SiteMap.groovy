package org.grails.main

import groovy.transform.CompileStatic
import org.grails.SoftwareVersion
import org.grails.main.model.Book
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
import org.grails.model.GrailsAward
import org.grails.pages.HtmlPage
import org.grails.main.pages.QuestionPage
import org.grails.main.pages.SupportPage
import org.grails.pages.Page
import org.yaml.snakeyaml.Yaml

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
                    new Profile(title: 'Vue', docsHref: 'https://grails-profiles.github.io/vue/latest/guide/index.html'),                
    ])

    public final static ProfileGroup PLUGIN_PROFILES = new ProfileGroup(title: 'Plugin Profiles', image: 'images/profiles.svg',
            profiles: [
                    new Profile(title: 'Plugin', docsHref: 'https://grails-profiles.github.io/plugin/latest/guide/index.html'),
                    new Profile(title: 'REST API Plugin', docsHref: 'https://grails-profiles.github.io/rest-api-plugin/latest/guide/index.html'),
                    new Profile(title: 'Web Plugin', docsHref: 'https://grails-profiles.github.io/web-plugin/latest/guide/index.html'),
            ])

    public final static ProfileGroup THIRD_PARTY_PROFILES =  new ProfileGroup(title: 'Third-Party Profiles', image: 'images/profiles.svg',
            description: "Use the following documentation to learn how to <a href=\"${Page.docsUrl()}/latest/guide/single.html#creatingProfiles\">create your own application profiles</a>.",
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
            new LearningPage(),
    ] as List<HtmlPage>

    public static List<GrailsAward> GRAILS_AWARDS_LIST = [
            new GrailsAward(image: 'kenkousen.png', alt: 'Ken Kousen - 2018 Grails Rock Star Award'),
            new GrailsAward(image: 'eric_helgeson.png', alt: 'Eric Helgeson - 2017 Grails Rock Star Award'),
            new GrailsAward(image: 'graeme.png', alt: 'Graeme Rocher - Grails lifetime contributor Award'),
            new GrailsAward(image: 'jeff_scott_brown.png', alt: 'Jeff Scott Brown - Grails lifetime contributor Award'),
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
            new Event(image: 'confs/greach.png',
                    href: 'https://greachconf.com',
                    title: 'Greach 2020',
                    location: 'Madrid, Spain',
                    dates: 'March 26 - 28, 2020',
                    about: 'Enhance your knowledge and skills with some of the most recognizable names from the Groovy development scene from all around the world. Experience three days full of Groovy and Grails talks and networking ... plus our famous Friday Beers Party!'),
            new Event(image: 'confs/gr8confeu.png',
                    href: 'http://gr8conf.eu/',
                    title: 'GR8Conf EU 2020',
                    dates: 'June 2 - 4, 2020',
                    location: 'Copenhagen, Denmark',
                    about: '''Groovy, Grails, and related technologies have seen astounding growth in interest and adoption over the past few years, and with good reason. GR8Conf is a series of conferences founded to spread the word worldwide. The 2018 GR8Conf Europe is celebrating its 10th year, and it's expected to be a blast. As in 2017 the conference had a DevOps day, this year DevOps topics will be mixed with the rest of the topics. GR8Conf is an independent, affordable series of conferences and covers All Things Groovy'''),


    ]

    static SoftwareVersion latestVersion() {
        List<SoftwareVersion> versions = stableVersions()
        versions ? versions.get(0) : null
    }
    static List<SoftwareVersion> stableVersions() {
        (versions().findAll { SoftwareVersion softwareVersion ->
            !softwareVersion.isSnapshot()
        } as List<SoftwareVersion>).sort { a, b -> b <=> a }
    }

    static List<String> olderVersions() {
        stableVersions().reverse().drop(1).collect { it.versionText }
    }

    static List<SoftwareVersion> preReleaseVersions() {
        versions().findAll() { SoftwareVersion softwareVersion ->
            softwareVersion.snapshot?.isMilestone() || softwareVersion.snapshot?.isReleaseCandidate()
        }.sort { a, b -> b <=> a }
    }

    static List<SoftwareVersion> versions() {
        Yaml yaml = new Yaml()
        File f = new File('src/main/resources/releases.yml')
        assert f.exists()
        Map model = yaml.load(f.newDataInputStream())
        model['releases'].collect { Map release ->
            SoftwareVersion.build(release['version'] as String)
        }.sort()
    }

    static SoftwareVersion latestPreReleaseVersion() {
        List<SoftwareVersion> versions = preReleaseVersions()
        versions ? versions.get(0) : null
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
            new DocumentationLink([title: 'Upgrade Documentation', href: 'https://grails.github.io/grails-upgrade/latest/guide/index.html']),
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
    ]])

    public final static DocumentationGroup DOCUMENTATION_VIEWS = new DocumentationGroup([image: 'images/views.svg', title: 'Views', links: [
            new DocumentationLink([title: 'GSP', href: 'http://gsp.grails.org/']),
            new DocumentationLink([title: 'JSON Views', href: 'http://views.grails.org/latest/']),
            new DocumentationLink([title: 'Markup Views', href: 'http://views.grails.org/latest/#_markup_views']),
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
Yes, Grails is an Open Source project licensed under the [Apache License v2](http://www.apache.org/licenses/LICENSE-2.0).'''),
            new Question(slug: 'eccn', title: 'What is the Export Control Classification Number (ECCN) for Grails?', answer: 'Grails is an open source technology that is not on the Commerce Control List, and is therefore classified with the general purpose classification of EAR 99.'),
        new Question(slug: 'question_learn', title: 'What is the best way to learn about Grails?', answer: """
Read the [User Guide](https://grails.org/documentation.html) for the version you are planning to use. We recommend you start with the latest stable version of the framework. We have also written a collection of [Guides](${Page.guidesUrl()}), which contain step-by-step tutorials for solving common scenarios.
"""),
            new Question(slug: 'question_training', title: 'Do you offer Grails training?', answer: '''
[OCI](http://objectcomputing.com), sponsor of Grails' development, offers [Grails and GORM courses](https://objectcomputing.com/training/catalog/grails/) developed and delivered by Grails co-founders and the core engineering team.</p>
'''),
            new Question(slug: 'question_usage', title: 'Where can I ask questions about usage?', answer: '''
The best place to get community support is [Stack Overflow](http://stackoverflow.com/questions/tagged/grails) or [Slack](https://grails-slack.cfapps.io) Grails Community Slack.
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
            new Question(slug: 'question_docs', title: 'How can I contribute to improving the documentation?', answer: """
Grails documentation comes in several forms:

- the [reference documentation](${Page.docsUrl()}/${latestVersion().versionText}/guide/single.html) contains language specification, user guides, a getting started tutorial, and more.
- the [GroovyDoc APIs](api.html) documents the classes of the Grails code base
- [GitHub](https://github.com/grails/grails-static-website) allows users to contribute to this website.   

Contributing to the Grails.org website is fairly easy. Create a GitHub account or sign in with an existing account, then open [Grails.org](${Page.docsUrl()}/latest/guide/single.html) and select the "Improve this doc" button at the top of the page you wish to edit. Please don't hesitate to help us make improvements, fix typos or broken language, clarify complicated sections, add new material, and anything else you feel will be helpful to other Grails users.
"""),
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

    ./gradlew assemble
    
    ./gradlew publishToMavenLocal

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
- JetBrains supports open source projects with [IntelliJ IDEA](https://www.jetbrains.com/idea/), its full-featured Capable and Ergonomic IDE for JVM.
- YourKit supports open source projects with its full-featured Java Profiler. YourKit, LLC is the creator of [YourKit Java Profiler](https://www.yourkit.com/java/profiler/index.jsp) and [YourKit .NET Profiler](https://www.yourkit.com/.net/profiler/index.jsp), innovative and intelligent tools for profiling Java and .NET applications.
'''),
    ]

}
