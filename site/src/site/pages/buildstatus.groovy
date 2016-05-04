layout 'layouts/main.groovy', true,
        pageTitle: 'The Grails Framework - Continuous integration',
        mainContent: contents {
            div(id: 'content', class: 'page-1') {
                div(class: 'row') {
                    div(class: 'row-fluid') {
                        div(class: 'col-lg-3') {
                            include template: 'includes/community-navbar.groovy'
                        }

                        div(class: 'col-lg-8 col-lg-pull-0') {
                            include template: 'includes/contribute-button.groovy'
                            h1 {
                                i(class: 'fa fa-circle-o-notch') {}
                                yield ' Continuous integration'
                            }
                            article {
                                p """
                                    Our ${
                                    $a(href: 'http://travis-ci.org/grails/grails-core', 'continuous integration server')
                                },
                                    runs on ${$a(href: 'http://www.travis-ci.org', 'Travis CI')},
                                    and builds Grails and Grails plugins.
                                """
                                hr(class: 'divider')

                                h2 'Grails builds'

                                def renderBuilds = { Map builds ->
                                    table(class: 'table table-stripped') {
                                        thead {
                                            tr {
                                                th('Build name')
                                                th('Status')
                                            }
                                        }
                                        tbody {
                                            builds.each { name, ref ->
                                                def (id, branch) = ref
                                                tr {
                                                    td(name)
                                                    td {
                                                        a(href: "https://travis-ci.org/${id}?branch=$branch") {
                                                            img(src: """https://travis-ci.org/${id}.svg?branch=$branch""")
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                renderBuilds(['Grails Master': ['grails/grails-core','master'],
                                        'Grails 3.1.x Branch': ['grails/grails-core', '3.1.x'],
                                        'Grails 3.0.x Branch': ['grails/grails-core', '3.0.x'],
                                        'Grails 2.5.x Branch': ['grails/grails-core', '2.5.x'],
                                        'Grails 2.4.x Branch': ['grails/grails-core', '2.4.x'],
                                        'Grails 2.3.x Branch': ['grails/grails-core', '2.3.x']
                                        ])

                            }
                        }
                    }
                }
            }
        }
