menu {
    group('Grails') {
        item 'Learn',                       'learn.html'
        item 'Guides',                     'http://guides.grails.org'
        item 'Documentation',               'documentation.html'        
        item 'Download',                    'download.html'
        item 'Plugins',                     'http://plugins.grails.org'
        item 'Community',                   'community.html'    
        item 'Support',                    'support.html'    
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
        item 'Discuss on Slack',    'http://slack-signup.grails.org', 'fa-slack'
        item 'Grails on Twitter',           'https://twitter.com/grailsframework',           'fa-twitter'
        item 'Events and conferences',      'events.html',                                      'fa-calendar'
        item 'Source code on GitHub',       'https://github.com/grails/grails-core',            'fa-github'
        item 'Report issues on Github',       'contribute.html#reporting-issues',                 'fa-bug'
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
    page 'support', 'support', [category: 'Support']
    page 'usergroups', 'usergroups', [category: 'Community', userGroups: usergroups]
    page 'mailing-lists', 'mailing-lists', [category: 'Community']
    page 'contribute', 'contribute', [category: 'Community']
    page 'sponsors', 'sponsors', [category: 'Community']
    page 'buildstatus', 'buildstatus', [category: 'Community']
    page 'faq', 'faq', [category: 'Documentation', docSections: documentationSections]
    page 'events', 'events', [category: 'Community', allEvents: allEvents]
    page 'api', 'api', [category: 'Documentation', iframeTarget: 'https://docs.grails.org/latest/api/']
    page 'singlepagedocumentation', 'single-page-documentation', [category: 'Documentation', iframeTarget: 'https://docs.grails.org/latest/']
    page 'plugins-template', 'templates/plugins', [:]
    page 'plugins', 'plugins', [category: 'Documentation', iframeTarget: 'https://sheehan.github.io/grails3-plugins/']
}

def readVersions = getClass().getResource("versions").text.split('\n')
def previousVersions = []
previousVersions.addAll( readVersions[0..-2])
def currentStableVersion = readVersions[-1]
def allVersions = previousVersions + [currentStableVersion]
    
documentation {
    groovyDocumentationVersions(allVersions)

    section('User Guide','fa-graduation-cap') {
        //          NAME                                     TARGET HTML                DOCPAGE HTML                       GENERATE
        item 'Downloading Grails',                             'download',                 'download',                         false
        item 'User Guide',                                  'single-page-documentation',  'single-page-documentation'
    }

    section ('API documentation', 'fa-code') {
        item 'Grails API Reference',  'api',    'api'
    }
}

downloads {
    //placing last to use pop on the download page
    // def currentBetaVersion = '3.0.0.RC3'
    // distribution('Grails 3.0') {
    //     description {
    //         yield "Grails 3.0 is currently in beta testing. If you want a stable version, please choose Grails $currentStableVersion which is our latest official "
    //         a(href: 'versioning.html', 'version')
    //         yield ' of Grails.'
    //     }
    //
    //     version(currentBetaVersion) {
    //         stable false
    //         releaseNotes "https://github.com/grails/grails-core/releases/tag/v$currentBetaVersion"
    //     }
    // }


    previousVersions.each{ versionNumber ->
        distribution("${versionNumber}"){
            version("${versionNumber}"){
                releaseNotes "https://github.com/grails/grails-core/releases/tag/v${versionNumber}"
                stable true
            }
        }
    }
    distribution("Grails $currentStableVersion") {
        description {
            yield "Grails $currentStableVersion is the latest official release of Grails"
        }

        version(currentStableVersion) {
            stable true
            releaseNotes "https://github.com/grails/grails-core/releases/tag/v$currentStableVersion"
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
        logo 'img/ecosystem/gradle.svg'
    }

    project('Spock') {
        description 'Spock is a testing and specification framework for Java and Groovy applications. What makes it stand out from the crowd is its beautiful and highly expressive specification language. Thanks to its JUnit runner, Spock is compatible with most IDEs, build tools, and continuous integration servers.'
        url 'http://spockframework.org/'
        logo ''
    }

    project('GPars') {
        description 'The GPars framework offers Java developers intuitive and safe ways to handle Java or Groovy tasks concurrently. Leveraging the enormous flexibility of the Groovy programing language and building on proven Java technologies, we aim to make concurrent programming for multi-core hardware intuitive, robust and enjoyable.'
        url 'http://www.gpars.org/'
        logo 'img/ecosystem/gpars.png'
    }

    project('Ratpack') {
        description 'Ratpack is a simple, capable, toolkit for creating high performance web applications.'
        url 'http://www.ratpack.io/'
        logo 'img/ecosystem/ratpack.png'
    }

    project('Griffon') {
        description 'Griffon is an application framework for developing desktop applications in the JVM, with Groovy being the primary language of choice. Inspired by Grails, Griffon follows the Convention over Configuration paradigm, paired with an intuitive MVC architecture and a command line interface.'
        url 'http://griffon-framework.org/'
        logo 'img/ecosystem/griffon.png'
    }

    project('Geb') {
        description 'Geb is a powerful browser functional testing framework that lets you quickly and easily write functional tests in Groovy. It brings together the power of WebDriver, the elegance of jQuery content selection, the robustness of Page Object modelling and the expressiveness of the Groovy language.'
        url 'http://www.gebish.org/'
        logo 'img/ecosystem/geb.png'
    }

    project('SDK') {
        description 'Sdkman is a tool for managing parallel versions of multiple Software Development Kits on most Unix based systems. It provides a convenient Command Line Interface (CLI) and API for installing, switching, removing and listing Candidates. Formerly known as GVM the Groovy enVironment Manager, it was inspired by the very useful RVM and rbenv tools, used at large by the Ruby community.'
        url 'http://sdkman.io/'
        logo 'img/ecosystem/sdkman.png'
    }
}

allEvents {

    // Note that the event image should be 257x180 to look nice
    event('Greach 2017') {
        location 'Madrid, Spain'
        date 'March 30 - April 1, 2017'
        url 'http://greachconf.com/'
        logo 'img/confs/greach2017.png'
        description '''
            <p>
            Greach, the Spanish gathering of enthusiasts of Groovy, Grails, Griffon, Gradle, Spock, Vert.x, Gaelyk,
            and many more. With inspirational talks from the makers and users of these projects, hands-on workshops with the rock stars,
            join the 150+ attendees, designers, students, designers, the best professionals together in a great atmosphere.
            </p>
        '''
    }
    event('GR8Conf EU 2017') {
        location 'Copenhagen, Denmark'
        date 'May 31 - June 2, 2017'
        url 'http://gr8conf.eu/'
        logo 'img/confs/gr8confeu.png'
        description '''
            <p>
            Groovy, Grails and the related technologies have seen astounding growth in interest and adoption the past
            few years, and with good reason. To spread the word even more we have created GR8Conf.
            </p>
            <p>
            The 2017 Edition of GR8Conf Europe will feature a DevOps day. Focus will be on technologies to support your
            everyday DevOps needs.
            </p>
            <p>
            GR8Conf is an independent, affordable series of conferences.
            It's dedicated to the technologies in the Groovy ecosystem.
            </p>
        '''
    }
    event('GR8Conf US 2017') {
        location 'Minneapolis, MN, United States of America'
        date 'July 2017'
        url 'http://gr8conf.us/'
        logo 'img/confs/gr8confus.png'
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
    event('GR8Conf India 2018') {
        location 'New Delhi, India'
        date 'January, 2018'
        url 'http://gr8conf.in/'
        logo 'img/confs/gr8confin.png'
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


}

books {
    book("Grails 3: A Practical Guide to Application Development") {
        authors "Eric Helgeson"
        cover 'img/books/pratical-grails-3-book-cover.png'
        url 'https://www.grails3book.com/'
        description "The first book dedicated to Grails 3. You will learn the concepts behind building Grails applications. Real, up-to-date code examples are provided so you can easily follow along."
    }
    book("Falando de Grails") {
		authors "Henrique Lobo Weissmann"
		cover 'img/books/grails_weissmann.png'
		url 'http://www.casadocodigo.com.br/products/livro-grails'
		description "The best reference on Grails (2.5 and 3.0) in portuguese. A great guide to the framework, dealing with details of the framework that many times are ignored by it's users. "
	}
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

    book('Grails Goodness Notebook') {
        authors 'Hubert A. Klein Ikkink'
        cover 'img/books/grailsgood.png'
        url 'https://leanpub.com/grails-goodness-notebook'
        description 'Experience the Grails platform through code snippets. Learn more about (hidden) Grails features with code snippets and short articles. The articles and code will get you started quickly and will give more insight in Grails.'
    }

    book('Groovy Goodness Notebook') {
        authors 'Hubert A. Klein Ikkink'
        cover 'img/books/ggood.jpg'
        url 'https://leanpub.com/groovy-goodness-notebook'
        description 'Experience the Groovy programming language through code snippets. Learn more about (hidden) Groovy features with code snippets and short articles. The articles and code will get you started quickly and will give more insight in Groovy.'
    }
}

usergroups {
    // Europe
	 // does not appear to be active
    userGroup('Belgium Groovy/Grails User Group') {
        location 'Europe/Belgium'
        url 'http://www.meetup.com/Belgium-Groovy-Grails-User-Group/'
    }
    userGroup('Aarhus Groovy & Grails meetup - no homepage yet') {
        location 'Europe/Denmark'
    }
	 // wrong url for Paris group, is using the Belgium link
	 // Paris has no url on the Groovy website, probably should match that
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
    userGroup('Berlin Groovy User Group') {
    	location 'Europe/Germany'
    	url 'http://www.meetup.com/Berlin-Groovy-User-Group/'
    }

    // North-America
    userGroup('Austin Groovy and Grails User Group (TX)') {
        location 'North-America/United States'
        url 'http://www.meetup.com/Austin-Groovy-and-Grails-Users/'
    }
    userGroup('Coder Consortium of Sacramento') {
        location 'North-America/United States'
        url 'http://coderconsortium.com/'
    }
	 // does not appear to be active
    userGroup('DC Groovy') { 
        location 'North-America/United States'
        url 'http://www.dcgroovy.org'
    }
    userGroup('DFW Groovy & Grails User Group') {
        location 'North-America/United States'
        url 'http://dfw2gug.org'
    }
	 // does not appear to be active
    userGroup('Groovy and Grails Users of Columbus OH') {
        location 'North-America/United States'
        url 'http://www.meetup.com/Grails-and-Ales/'
    }
    userGroup('Groovy Users of Minnesota') {
        location 'North-America/United States'
        url 'http://groovy.mn'
    }
    userGroup('Los Angeles Groovy Users Group') {
        location 'North-America/United States'
        url 'https://www.meetup.com/Los-Angeles-GUG/'
    }
    userGroup('NYC Groovy / Grails Meetup') {
        location 'North-America/United States'
        url 'http://www.meetup.com/grails/'
    }
	 // does not appear to be active
    userGroup('Scottsdale Groovy Brigade') {
        location 'North-America/United States'
        url 'http://www.scottsdale-groovy.com/'
    }
    userGroup('SF Bay Groovy and Grails Meetup Group') {
        location 'North-America/United States'
        url 'http://www.meetup.com/java-161/'
    }
    userGroup('Silicon Valley Groovy/Grails Centro') {
        location 'North-America/United States'
        url 'http://www.meetup.com/San-Francisco-Grails-Centro/'
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
