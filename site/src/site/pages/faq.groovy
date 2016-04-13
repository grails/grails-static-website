layout 'layouts/main.groovy', true,
        pageTitle: 'The Grails Framework - FAQ - Frequently Asked Questions',
        mainContent: contents {
            div(id: 'content', class: 'page-1') {
                div(class: 'row') {
                    div(class: 'row-fluid') {
                        div(class: 'col-lg-3') {
                            ul(class: 'nav-sidebar') {
                                li {
                                    a(href: 'documentation.html', 'Documentation')
                                }
                                docSections.each { section ->
                                    li { a(href: "documentation.html#${section.anchor}", class: 'anchor-link', section.name) }
                                }
                                li {
                                    a(href: "documentation.html#old-docs", class: 'anchor-link', 'Documentation for older versions')
                                }
                                li(class: 'active') {
                                    a(href: 'faq.html') { strong('FAQ') }
                                }
                            }
                        }

                        div(class: 'col-lg-8 col-lg-pull-0') {
                            include template: 'includes/contribute-button.groovy'
                            h1 {
                                i(class: 'fa fa-question-circle') {}
                                yieldUnescaped ' Frequently Asked Questions'
                            }
                            hr(class: 'divider')
                            article {
                                h2 'Is Grails an Open Source project?'
                                p {
                                    yield 'Yes, Grails is an Open Source project, licensed under the '
                                    a(href: 'http://www.apache.org/licenses/LICENSE-2.0', 'Apache License v2')
                                    yield '. You can see the license header in all the source files of the project, as well as a '
                                    a(href: 'https://github.com/grails/grails-core/blob/master/LICENSE', 'license file')
                                    yield ' at the root of the project'
                                }

                                h2 'Where can I ask questions?'
                                p {
                                    yield 'The best place to get community support is '
                                    a(href: 'http://stackoverflow.com/questions/tagged/grails', 'Stack Overflow')
                                    yield ' or '
                                    a(href: 'http://slack-signup.grails.org', 'Slack')
                                    yield '. If you wish to discuss the development of the framework itself or the Grails source code, then checkout the'
                                    a(href: 'https://grails.org/mailing-lists.html', ' Mailing lists')
                                    yield '.'
                                }
                            }
                        }
                    }
                }
            }
        }
