layout 'layouts/main.groovy', true,
        pageTitle: 'The Grails Framework - Documentation',
        mainContent: contents {
            div(id: 'content', class: 'page-1') {
                div(class: 'row') {
                    div(class: 'row-fluid') {
                        div(class: 'col-lg-3') {
                            ul(class: 'nav-sidebar') {
                                li(class: 'active') {
                                    a(href: '#') { strong('Documentation') }
                                }
                                docSections.each { section ->
                                    li { a(href: "#${section.anchor}", class: 'anchor-link', section.name) }
                                }
                                li {
                                    a(href: "#old-docs", class: 'anchor-link', 'Documentation for older versions')
                                }
                                li {
                                    a(href: 'faq.html', 'FAQ')
                                }
                            }
                        }

                        div(class: 'col-lg-8 col-lg-pull-0') {
                            include template: 'includes/contribute-button.groovy'
                            h1 {
                                i(class: 'fa fa-university') {}
                                yield ' Documentation'
                            }
                            p {
                                yield 'The documentation is available as a '
                                a(href: "http://docs.grails.org/latest/guide/single.html", 'single-page document')
                                yield ', or feel free to pick at a direct section below.'
                            }
                            p "You can also browse ${$a(href: '#old-docs', 'documentation for older versions')}."
                            hr(class: 'divider')

                           div(class: 'row-fluid') {
                                article {
                                    div(class: 'col-md-6', style:'margin-top:20px') {
                                        a(name: "foo") {}
                                        h2 {
                                            i(class: 'fa fa-graduation-cap', " User Guide")
                                        }
                                        ul {
                                            li { a(href: "https://grails.org/download.html", "Downloading Grails") }
                                            li { a(href: "http://docs.grails.org/", "User Guide") }
                                            li { a(href: "http://docs.grails.org/latest/api/", "API Reference") }
                                        }
                                    }
                                }
                            } 

                           div(class: 'row-fluid') {
                                article {
                                    div(class: 'col-md-6', style:'margin-top:20px') {
                                        a(name: "foo") {}
                                        h2 {
                                            i(class: 'fa fa-database', " GORM")
                                        }
                                        ul {
                                            li { a(href: "http://gorm.grails.org/", "Website") }
                                            li { a(href: "http://docs.grails.org/latest/guide/GORM.html", "GORM for Hibernate User Guide") }
                                            li { a(href: "http://gorm.grails.org/latest/api/", "API Reference") }
                                        }
                                    }
                                }
                            }                            
                            div(class: 'col-md-12') {
                                hr(class: 'divider')

                                a(name: 'old-docs') {}
                                article {
                                    h2 'User guide for older versions'
                                    p 'You can browse the documentation of previous versions of Grails since Grails 1.2.0:'
                                    def allVersions =  [*allDocVersions, 'Select a version'].reverse()

                                    select(class: 'form-control', onchange: "window.location.href='http://grails.org/doc/' + this.value ") {
                                        allVersions.each { String version ->
                                            option version
                                        }
                                    }
                                }
                                br()
                                article {
                                    h2 'API for older versions'
                                    p 'You can browse the API of previous versions of Grails since Grails 1.2.0:'
                                    def allVersions =  [*allDocVersions, 'Select a version'].reverse()

                                    select(class: 'form-control', onchange: "window.location.href='http://grails.org/doc/' + this.value + '/api' ") {
                                        allVersions.each { String version ->
                                            option version
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
