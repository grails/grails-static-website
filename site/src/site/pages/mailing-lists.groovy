layout 'layouts/main.groovy', true,
        pageTitle: 'The Grails Framework - Mailing-lists',
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
                                i(class: 'fa fa-envelope-o') {}
                                yield ' Mailing Lists'
                            }
                            p '''The Grails mailing list is a great place to discuss Grails with the Grails core developers.'''
                            hr(class: 'divider')

                            article {
                                h1 'Subscribing to the lists'
                                p {
                                    yield '''
                                        The Grails mailing list is a Google Group which you can suscribe to using the '''
                                    a(href: 'https://groups.google.com/forum/#!forum/grails-dev-discuss', 'following link')
                                }
                                hr(class: 'divider')

                                h2 'Mailing List vs Stack Overflow'
                                p {
                                    yield 'The mailing list is for discussion around the framework\'s development. For questions we recommend '
                                    a(href: 'http://stackoverflow.com/questions/tagged/grails', 'StackOverflow')                                    

                                }
                                hr(class: 'divider')

                        }
                    }
                }
            }
        }