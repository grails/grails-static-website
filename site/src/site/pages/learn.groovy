import model.Book
import model.Video
import model.Course

layout 'layouts/main.groovy', true,
        pageTitle: 'Grails - Learn',
        extraStyles: ['book.css'],
        mainContent: contents {
            div(id: 'content', class: 'page-1') {
                div(class: 'row') {
                    div(class: 'row-fluid') {
                        div(class: 'col-lg-3') {
                            ul(class: 'nav-sidebar') {
                                li(class: 'active') {
                                    a(href: '#') { strong('Learn') }
                                }
                                li {
                                    a(href: '#guides', class: 'anchor-link', 'Guides')
                                }
                                li {
                                    a(href: '#onlinetraining', class: 'anchor-link', 'Online Training')
                                }
                                li {
                                    a(href: '#onsitetraining', class: 'anchor-link', 'On-site Training')
                                }
                                li {
                                    a(href: '#books', class: 'anchor-link', 'Books')
                                }
                                li {
                                    a(href: '#videos', class: 'anchor-link', 'Presentations')
                                }
                            }
                        }

                        div(class: 'col-lg-8 col-lg-pull-0') {
                            include template: 'includes/contribute-button.groovy'
                            h1 {
                                i(class: 'fa fa-graduation-cap') {}
                                yield ' Learn'
                            }
                            article {
                                p 'Welcome to the learning section of the Grails website.'
                                p """
                                    First of all, you will need to ${$a(href: 'documentation.html#gettingstarted', 'get started')}
                                    by installing Grails on your system or project.
                                """
                                p """
                                    Once all set up, we invite you to have a look at the Grails
                                    ${$a(href: 'documentation.html', 'documentation')}, which explains all the details about the framework, 
                                    such as how to use the ${$a(href: 'documentation.html#tools', 'tools')}
                                    that come with a Grails installation.
                                """
                                p """
                                    But there are other ways to learn more about Grails, thanks to ${$a(href: '#books', 'books')}
                                    and ${$a(href: '#videos', 'presentations')} given about Grails at conferences.
                                """
                                
                                hr(class: 'divider')

                                a(name: 'guides') {}
                                h2 {
                                    i(class: 'fa fa-tasks') {}
                                    yield ' Guides'
                                }
                                p """The Official Grails ${$a(href: 'http://docs.grails.org/', 'User guide')} provides pretty comprehensive coverage of the framework, but if that is is not enough checkout the great ${$a(href: 'http://guides.grails.org/', 'Guides')} section of the website for more focused tutorials with examples."""


                                hr(class: 'divider')

                                a(name: 'onlinetraining') {}

                                h2 {
                                    i(class: 'fa fa-graduation-cap') {}
                                    yield ' Online Training'
                                }

                                p {
                                    yield 'Jeff Scott Brown, Co-founder of the Grails framework, teaches a series of online '
                                    a(href: 'https://objectcomputing.com/training/catalog/grails/grails-workshops/', 'Grails training courses')
                                    yield '.'
                                }

                                onlineTrainingCatalogue.each {
                                    Course course = it.value
                                    div(class: 'course') {
                                        h2 { a(href: course.url, course.title) }
                                        if (course.instructor) {
                                            div {
                                                yield "Instructor: ${course.instructor}"
                                            }
                                        }
                                        if (course.hours) {
                                            div {
                                                i(class: 'fa fa-clock-o') {}
                                                yiedl " Hours: ${course.hours}"
                                            }
                                        }
                                        if (course.dates) {
                                            div {
                                                i(class: 'fa fa-calendar') {}
                                                yiedl " ${course.dates}"
                                            }
                                        }
                                    }
                                }

                                hr(class: 'divider')

                                a(name: 'onsitetraining') {}

                                h2 {
                                    i(class: 'fa fa-graduation-cap') {}
                                    yield ' On-site Training'
                                }

                                onsiteTrainingCatalogue.each {
                                    Course course = it.value
                                    div(class: 'course') {
                                        h2 { a(href: course.url, course.title) }
                                        if (course.instructor) {
                                            div {
                                                yield "Instructor: ${course.instructor}"
                                            }
                                        }
                                        if (course.location) {
                                            div {
                                                i(class: 'fa fa-map-marker') {}
                                                yiedl " ${course.location}"
                                            }
                                        }
                                        if (course.hours) {
                                            div {
                                                i(class: 'fa fa-clock-o') {}
                                                yiedl " Hours: ${course.hours}"
                                            }
                                        }
                                        if (course.dates) {
                                            div {
                                                i(class: 'fa fa-calendar') {}
                                                yiedl " ${course.dates}"
                                            }
                                        }
                                    }
                                }

                                hr(class: 'divider')

                                a(name: 'books') {}
                                h2 {
                                    i(class: 'fa fa-book') {}
                                    yield ' Books'
                                }
                                p '''
                                    Another great approach to learning Grails is to read the various books published
                                    on the language:'''

                                allBooks.each {
                                    String title = it.key
                                    Book book = it.value

                                    figure(class: 'book') {
                                        ul(class: 'hardcover_front') {
                                            li {
                                                img(src: book.cover, width: '100%', height: '100%')
                                            }
                                            li {}
                                        }
                                        ul(class: 'page') {
                                            li {}
                                            li {
                                                a(class: 'book-btn', href: book.url, target: '_blank', 'More info')
                                            }
                                            3.times { li {} }
                                        }
                                        ul(class: 'hardcover_back') {
                                            2.times { li {} }
                                        }
                                        ul(class: 'book_spine') {
                                            2.times { li {} }
                                        }
                                        figcaption {
                                            h1 { a(href: book.url, book.title) }
                                            span "By ${book.authors}"
                                            p book.description
                                        }
                                    }
                                }

                                hr(class: 'divider')

                                a(name: 'videos') {}
                                h2 {
                                    i(class: 'fa fa-film') {}
                                    yield ' Presentations'
                                }
                                p """
                                    Many Grails-related presentations have been recorded at ${$a(href: 'events.html', 'conferences')}
                                    that you might wish to have a look at, to learn more about Grails, delve into particular topics, and more.
                                """
                                p """
                                    Below are a few selected presentations given at the
                                    ${$a(href: 'http://springone2gx.com/', 'SpringOne2GX')},
                                    ${$a(href: 'http://gr8conf.eu/', 'GR8Conf')} and
                                    ${$a(href: 'http://greachconf.com/', 'Greach')} conferences.
                                """

                                /*videos.each { Video video ->
                                    div(class: 'presentations') {
                                        a(href: video.videoUrl) {
                                            img(class: 'screenshot', src: "img/videos/${video.pictureUrl}")
                                        }
                                        div(class: 'metadata') {
                                            a(href: video.videoUrl) {
                                                h1(class: 'title', video.title)
                                            }
                                            span(class: 'speaker', "By ${video.speaker}")
                                            if(video.slidesUrl || video.codeUrl) {
                                                p(class: 'urls') {
                                                    if (video.slidesUrl) {
                                                        i(class: 'fa fa-photo') {}
                                                        yield ' '
                                                        a(href: video.slidesUrl, 'slides')
                                                    }
                                                    if (video.slidesUrl && video.codeUrl) yield ' | '
                                                    if (video.codeUrl) {
                                                        i(class: 'fa fa-code') {}
                                                        yield ' '
                                                        a(href: video.codeUrl, 'source code')
                                                    }
                                                }
                                            }
                                            div(class: 'summary') {
                                                yieldUnescaped video.summary
                                            }
                                        }
                                    }
                                }*/

                                p "You can find more presentations:"
                                ul {
                                    li {
                                        a(href: 'https://www.youtube.com/channel/UCJXNOMywewNmau4hzAy4LjA/playlists', 'GR8Conf Europe Youtube channel')
                                    }
                                    li {
                                        a(href: 'https://www.youtube.com/user/GR8ConfUS/videos', 'GR8Conf US Youtube channel')
                                    }
                                    li {
                                        a(href: 'https://www.youtube.com/channel/UCE2m6tkpJBhDllQQEC3NSCw/playlists', 'GR8Conf India (To The New) Youtube channel')
                                    }
                                    li {
                                        a(href: 'http://www.infoq.com/SpringOne-2GX-2013/presentations/24', 'SpringOne2GX 2013 / InfoQ presentations')
                                    }
                                    li {
                                        a(href: 'https://www.youtube.com/user/TheGreachChannel/playlists', 'Greach YouTube channel')
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
