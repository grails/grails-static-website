menu {
    group('Grails') {
        item 'Learn',                       'learn.html'
        item 'Documentation',               'documentation.html'
        item 'Download',                    'download.html'
        item 'Plugins',                     'plugins.html'
        item 'Community',                   'community.html'
        item 'Ecosystem',                   'ecosystem.html'        
    }

    group('About') {
        item 'Contributing',                'contribute.html'
        item 'Source code',                 'https://github.com/grails/grails-core'
        item 'Build status',                'buildstatus.html'
        item 'Books',                       'learn.html#books'
        //item 'Sponsors',                    'sponsors.html'
        item 'FAQ',                         'faq.html'
        item 'Search',                      'search.html'
    }

    group('Socialize') {
        item 'Discuss on the Mailing List', 'mailing-lists.html',                               'fa-envelope'
        item 'Grails on Twitter',           'https://twitter.com/grailsframework',           'fa-twitter'
        item 'Events and conferences',      'events.html',                                      'fa-calendar'
        item 'Source code on GitHub',       'https://github.com/grails/grails-core',            'fa-github'
        item 'Report issues in Jira',       'contribute.html#reporting-issues',                 'fa-bug'
        item 'Stack Overflow questions',    'http://stackoverflow.com/questions/tagged/grails', 'fa-stack-overflow'
    }
}

pages {
    page 'index', 'index', [allEvents: allEvents]
    page 'search', 'search', [category: 'Search']
    page 'ecosystem', 'ecosystem', [category: 'Ecosystem', ecosys: ecosystem]
    page 'learn', 'learn', [category: 'Learn', docSections: documentationSections, allBooks: library, videos: videos]
    page 'documentation', 'documentation', [category: 'Documentation', docSections: documentationSections, allDocVersions: allDocVersions]
    page 'download', 'download', [category: 'Download', distributions: distributions]
    page 'versioning', 'versioning', [category: 'Download']
    page 'community', 'community', [category: 'Community']
    page 'usergroups', 'usergroups', [category: 'Community', userGroups: usergroups]
    page 'mailing-lists', 'mailing-lists', [category: 'Community']
    page 'contribute', 'contribute', [category: 'Community']
    page 'sponsors', 'sponsors', [category: 'Community']
    page 'buildstatus', 'buildstatus', [category: 'Community']
    page 'faq', 'faq', [category: 'Documentation', docSections: documentationSections]
    page 'events', 'events', [category: 'Community', allEvents: allEvents]
    page 'api', 'api', [category: 'Learn', iframeTarget: 'http://grails.org/doc/latest/api']
    page 'plugins', 'plugins', [category: 'Plugins', iframeTarget: 'http://plugins-grails-org-dev.cfapps.io/plugins']
    page 'singlepagedocumentation', 'single-page-documentation', [category: 'Learn', iframeTarget: 'http://grails.org/doc/latest/']
}

documentation {
    groovyDocumentationVersions([
            '1.2.0', '1.2.1', '1.2.2', '1.2.3', '1.2.4', '1.2.5', 
            '1.3.0', '1.3.1', '1.3.2', '1.3.3', '1.3.4', '1.3.5', '1.3.6', '1.3.7', '1.3.8', '1.3.9',
            '2.0.0', '2.0.1', '2.0.2', '2.0.3', '2.0.4', 
            '2.1.0', '2.1.1', '2.1.2', '2.1.3', '2.1.4', '2.1.5',
            '2.2.0', '2.2.1', '2.2.2', '2.2.3', '2.2.4', '2.2.5',
            '2.3.0', '2.3.1', '2.3.2', '2.3.3', '2.3.4', '2.3.5', '2.3.6', '2.3.7', '2.3.8', '2.3.9', '2.3.10', '2.3.11',
            '2.4.0', '2.4.1', '2.4.2', '2.4.3', '2.4.4'])

    section('User Guide','fa-graduation-cap') {
        //          NAME                                     TARGET HTML                DOCPAGE HTML                       GENERATE
        item 'Downloading Grails',                             'download',                 'download',                         false
        item 'User Guide',                                  'single-page-documentation',  'single-page-documentation'
    }

    section ('API documentation', 'fa-code') {
        item 'GroovyDoc API Reference',  'api',    'api'
    }
}

downloads {
    // distribution('Grails 3.0') {
    //     description {
    //         yield 'Grails 3.0 is currently in beta testing. If you want a stable version, please choose Grails 2.4 which is our latest official '
    //         a(href: 'versioning.html', 'version')
    //         yield ' of Groovy.'
    //     }

    //     version('2.4.0-beta-4') {
    //         releaseNotes 'http://jira.codehaus.org/secure/ReleaseNote.jspa?projectId=10242&version=20612'
    //     }
    // }
    distribution('Grails 2.4') {
        description {
            yield 'Grails 2.4 is the latest official release of Grails'
        }

        version('2.4.4') {
            stable true
            releaseNotes 'https://jira.grails.org/secure/ReleaseNote.jspa?projectId=10020&version=13903'
        }
    }
}

ecosystem {
    project('Groovy') {
        description 'Groovy is a powerful, optionally typed and dynamic language, with static-typing and static compilation capabilities, for the Java platform aimed at multiplying developers’ productivity thanks to a concise, familiar and easy to learn syntax. It integrates smoothly with any Java program, and immediately delivers to your application powerful features, including scripting capabilities, Domain-Specific Language authoring, runtime and compile-time meta-programming and functional programming.'
        url 'http://groovy-lang.org/'
        logo 'img/groovy-logo-colored.svg'
    }

    project('Gradle') {
        description 'Gradle is build automation evolved. Gradle can automate the building, testing, publishing, deployment and more of software packages or other types of projects such as generated static websites, generated documentation or indeed anything else.'
        url 'http://gradle.org'
        logo 'img/ecosystem/gradle.gif'
    }

    project('Spock') {
        description 'Spock is a testing and specification framework for Java and Groovy applications. What makes it stand out from the crowd is its beautiful and highly expressive specification language. Thanks to its JUnit runner, Spock is compatible with most IDEs, build tools, and continuous integration servers.'
        url 'https://code.google.com/p/spock/'
        logo ''
    }

    project('GPars') {
        description 'The GPars framework offers Java developers intuitive and safe ways to handle Java or Groovy tasks concurrently. Leveraging the enormous flexibility of the Groovy programing language and building on proven Java technologies, we aim to make concurrent programming for multi-core hardware intuitive, robust and enjoyable.'
        url 'http://gpars.codehaus.org/'
        logo 'img/ecosystem/gpars.png'
    }

    project('Ratpack') {
        description 'Ratpack is a simple, capable, toolkit for creating high performance web applications.'
        url 'http://www.ratpack.io/'
        logo 'img/ecosystem/ratpack.png'
    }

    project('Griffon') {
        description 'Griffon is an application framework for developing desktop applications in the JVM, with Groovy being the primary language of choice. Inspired by Grails, Griffon follows the Convention over Configuration paradigm, paired with an intuitive MVC architecture and a command line interface.'
        url 'http://griffon.codehaus.org/'
        logo 'img/ecosystem/griffon.png'
    }

    project('GVM') {
        description 'GVM is a tool for managing parallel Versions of multiple Software Development Kits on most Unix based systems. It provides a convenient command line interface for installing, switching, removing and listing Candidates.'
        url 'http://gvmtool.net/'
        logo 'img/ecosystem/gvmtool.png'
    }
}

allEvents {
    event('Greach 2015') {
        location 'Madrid, Spain'
        date 'April 10-11, 2015'
        url 'http://greachconf.com/'
        logo 'img/confs/greach2015.png'
        description '''
            <p>
            Greach, the Spanish gathering of enthusiasts of Groovy, Grails, Griffon, Gradle, Spock, Vert.x, Gaelyk,
            and many more. With inspirational talks from the makers and users of these projects, hands-on workshops with the rock stars,
            join the 150+ attendees, designers, students, designers, the best professionals together in a great atmosphere.
            </p>
        '''
    }
    event('GR8Conf EU 2015') {
        location 'Copenhagen, Denmark'
        date 'June 2-4, 2015'
        url 'http://gr8conf.eu/'
        logo 'img/confs/gr8confeu2015.png'
        description '''
            <p>
            We <strong>feed your brain</strong> with all the important stuff from the 
            <strong>Groovy</strong> and <strong>Grails</strong> community.
            </p>
            <p>
            We have <strong>in-depth</strong> talks covering all <strong>major technologies</strong> in the Groovy ecosystem. 
            All talks are performed by the <strong>brightest minds</strong> and <strong>core developers</strong> in their fields.
            </p>
        '''
    }
    event('GR8Conf US 2015') {
        location 'Minneapolis, MN, United States of America'
        date 'July 29-31, 2015'
        url 'http://gr8conf.us/'
        logo 'img/confs/gr8confus2015.png'
        description '''
            <p>
            Groovy, Grails and the related technologies have seen astounding growth in interest and adoption the past
            few years, and with good reason. To spread the word even more we have created GR8Conf.
            </p>
            <p>
            GR8Conf is an independent, affordable series of conferences.
            It's dedicated to the technologies in the Groovy ecosystem.
            </p>
        '''
    }
    event('Groovy Grails eXchange 2015') {
        location 'London, United Kingdom'
        date 'December 14-15, 2015'
        url 'https://skillsmatter.com/conferences/6863-groovy-grails-exchange-2015'
        logo 'img/confs/ggex2015.png'
        description '''
            <p>
            Do you love Groovy & Grails? Would you like to spend 2 days with 150+ Groovy & Grails developers, learning
            and sharing skills with the world's leading experts on Groovy, Grails and modern enterprise development?
            </p>
            <p>
            Then why not come to the 9th annual Groovy & Grails eXchange and be part of this passionate community of
            creators, founders, developers and enthusiasts.
            </p>
        '''
    }
}

books {
    book('The Definitive Guide to Grails 2') {
        authors "Jeff Scott Brown and Graeme Rocher"
        cover 'img/books/grocher_jbrown_cover.jpg'
        url 'http://www.apress.com/9781430243779'
        description 'Definitive reference on The Grails Framework, authored by core members of the development team.'
    }    
    book('Grails in Action') {
        authors "Glen Smith and Peter Ledbrook"
        cover 'img/books/gsmith2_cover150.jpg'
        url 'http://www.manning.com/gsmith2/'
        description 'Grails in Action, Second Edition is a comprehensive introduction to Grails 2 focused on making you super-productive fast.'
    }    

    book('Grails 2: A Quick-Start Guide') {
        authors "Dave Klein and Ben Klein"
        cover 'img/books/bklein_cover.jpg'
        url 'http://www.amazon.com/gp/product/1937785777?tag=misa09-20'
        description 'This revised and updated new edition shows you how to use Grails by iteratively building a unique, working application. '
    }    

    book('Programming Grails') {
        authors "Burt Beckwith"
        cover 'img/books/bbeckwith_cover.gif'
        url 'http://shop.oreilly.com/product/0636920024750.do'
        description 'Dig deeper into Grails architecture and discover how this application framework works its magic.'
    }   

    book('Groovy in Action, 2nd edition') {
        authors "Dierk König, Guillaume Laforge, Paul King, Cédric Champeau, Hamlet D'Arcy, Erik Pragt, and Jon Skeet"
        cover 'img/books/regina.png'
        url 'http://www.manning.com/koenig2/'
        description 'The undisputed definitive reference on The Groovy Language, authored by core members of the development team.'
    }

    book('Making Java Groovy') {
        authors 'Ken Kousen'
        cover 'img/books/Kousen-MJG.png'
        url 'http://www.manning.com/kousen/'
        description 'Make Java development easier by adding Groovy. Each chapter focuses on a task Java developers do, like building, testing, or working with databases or restful web services, and shows ways Groovy can help.'
    }

    book('Programming Groovy 2') {
        authors 'Venkat Subramaniam'
        cover 'img/books/vslg2.jpg'
        url 'http://pragprog.com/book/vslg2/programming-groovy-2'
        description 'Dynamic productivity for the Java developer'
    }

    book('Groovy 2 Cookbook') {
        authors 'Andrey Adamovitch, Luciano Fiandeso'
        cover 'img/books/g2cook.jpg'
        url 'http://www.packtpub.com/groovy-2-cookbook/book'
        description 'Over 90 recipes that provide solutions to everyday programming challenges using the powerful features of Groovy 2'
    }

    book('Groovy for Domain-Specific Languages') {
        authors 'Fergal Dearle'
        cover 'img/books/gdsl.jpg'
        url 'http://www.packtpub.com/groovy-for-domain-specific-languages-dsl/book'
        description 'Enhance and extend your Java applications with Domain-Specific Languages in Groovy'
    }

    book('Groovy Goodness Notebook') {
        authors 'Hubert A. Klein Ikkink'
        cover 'img/books/ggood.jpg'
        url 'https://leanpub.com/groovy-goodness-notebook'
        description 'Experience The Grails Framework through code snippets. Learn more about (hidden) Groovy features with code snippets and short articles. The articles and code will get you started quickly and will give more insight in Groovy.'
    }
}

usergroups {
    // Europe
    userGroup('Belgium Groovy/Grails User Group') {
        location 'Europe/Belgium'
        url 'http://www.meetup.com/Belgium-Groovy-Grails-User-Group/'
    }
    userGroup('Aarhus Groovy & Grails meetup - no homepage yet') {
        location 'Europe/Denmark'
    }
    userGroup('Paris Groovy Grails User Group') {
        location 'Europe/France'
        url 'http://www.meetup.com/Belgium-Groovy-Grails-User-Group/'
    }
    userGroup('Groovy & Grails Israel User Group') {
        location 'Europe/Israel'
        url 'http://www.meetup.com/Groovy-Grails-Israel-Meetup-Group/'
    }
    userGroup('Warsaw Groovy User Group') {
        location 'Europe/Poland'
        url 'http://www.meetup.com/Warsaw-Groovy-User-Group/'
    }
    userGroup('Madrid Groovy User Group') {
        location 'Europe/Spain'
        url 'http://www.meetup.com/madrid-gug/'
    }
    userGroup('Dutch Groovy and Grails User Group (NLGUG)') {
    	location 'Europe/The Netherlands'
    	url 'http://www.meetup.com/nl-gug/'
    }

    // North-America
    userGroup('Groovy Users of Minnesota') {
        location 'North-America/United States'
        url 'http://groovy.mn'
    }
    userGroup('Austin Groovy and Grails User Group (TX)') {
        location 'North-America/United States'
        url 'http://www.meetup.com/Austin-Groovy-and-Grails-Users/'
    }
    userGroup('Groovy and Grails Users of Columbus OH') {
        location 'North-America/United States'
        url 'http://www.meetup.com/Grails-and-Ales/'
    }
    userGroup('NYC Groovy / Grails Meetup') {
        location 'North-America/United States'
        url 'http://www.meetup.com/grails/'
    }
    userGroup('Scottsdale Groovy Brigade') {
        location 'North-America/United States'
        url 'http://www.scottsdale-groovy.com/'
    }
    userGroup('Coder Consortium of Sacramento') {
        location 'North-America/United States'
        url 'http://coderconsortium.com/'
    }
    userGroup('DC Groovy') {
        location 'North-America/United States'
        url 'http://www.dcgroovy.org'
    }

    // South-America
    userGroup('Grails Brasil - Groovy and Grails users group of Brazil') {
        location 'South-America/Brazil'
        url 'http://www.grailsbrasil.com.br'
    }

    // Asia
    userGroup('Bangalore Groovy Grails Meetup') {
        location 'Asia/India'
        url 'http://www.meetup.com/Bangalore-Groovy-Grails-Meetup/'
    }
    userGroup('Japan Grails/Groovy User Group') {
        location 'Asia/Japan'
        url 'http://www.jggug.org/'
    }

    // Oceania?
    /* userGroup('') { location 'Oceania/Australia' } */
}

videos {

    video('The Groovy ecosystem revisited') {
        speaker 'Andrés Almiray'
        summary '''
            <p>Groovy is a well established player in the JVM since a few years ago.
            It's increased popularity across the years has spawned several projects that conform the Groovy Ecosystem.
            You've probably heard of Grails, Gradle, Griffon and Spock.
            But what about the rest of projects that are just waiting around the corner to be discovered and make your life easier?
            This talk presents them tools and libraries that use Groovy as the main driving force to get the job done.</p>
        '''
        pictureUrl 'groovy-ecosystem-revisited.png'
        videoUrl 'https://www.youtube.com/watch?v=2NGeaIwmnC8&list=PLwxhnQ2Qv3xuE4JEKBpyE2AbbM_7G0EN1&index=5'
        slidesUrl 'http://fr.slideshare.net/aalmiray/gr8conf-groovy-ecosystem'
    }

}
