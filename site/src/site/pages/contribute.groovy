layout 'layouts/main.groovy', true,
        pageTitle: 'The Grails Framework - Contribute',
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
                                i(class: 'fa fa-pencil-square-o') {}
                                yield ' Contribute'
                            }
                            article {
                                p '''
                                    There are many ways you can help the Grails development team improve The Grails Framework.
                                    And all help is welcome to make a difference, by:

                                '''
                                ul {
                                    li {
                                        yield 'helping other users on '
                                        a href:'http://stackoverflow.com/tags/grails/info', 'StackOverflow'
                                    }
                                    li {
                                        yield 'contributing ideas to the '
                                        a(href: 'mailing-lists.html', 'mailing-lists')
                                    }
                                    li {
                                        a(href: '#reporting-issues', 'reporting issues')
                                        yield ' you encounter in our '
                                        a(href: 'https://github.com/grails/grails-core/issues', 'bug tracker')
                                    }
                                    li {
                                        a(href: '#documenting', 'documenting')
                                        yield ' various aspects of the language or its APIs'
                                    }
                                    li {
                                        yield 'improving this '
                                        a(href: 'https://github.com/grails/grails-static-website', 'website')
                                    }
                                    li 'covering the code base with more tests to avoid future regressions, '
                                    li {
                                        yield 'and of course, by '
                                        a(href: '#code', 'contributing bug fixes or new features')
                                    }
                                }
                                hr(class: 'divider')

                                h2 'Discussing on the mailing-lists'
                                p {
                                    yield '''
                                        If you want to discuss a new feature,
                                        share interesting findings, and more, then the '''
                                    a(href: 'mailing-lists.html', 'mailing-lists')
                                    yield ' are the place to go to to start a conversation with the Grails developers and other Grails users.'
                                    yield ' For general usage questions, posting a question on '
                                    a href:'http://stackoverflow.com/tags/grails/info', ' StackOverflow '
                                    yield ' is the way to go.'
                                }
                                hr(class: 'divider')

                                a(name: 'reporting-issues') {}
                                h2 'Reporting issues'
                                p {
                                    yield 'The Grails project uses '
                                    a(href: 'https://github.com/grails/grails-core/issues', 'Github issues')
                                    yield '''
                                        to report and track issues, feature enhancements, and new features.
                                        Be sure to be signed-up and logged-in, as explained below, before proceeding.
                                    '''
                                }
                                p {
                                    button(type: 'button', class: 'btn btn-default', 'Report an issue',
                                            onclick: 'window.location.href="https://github.com/grails/grails-core/issues/new"')
                                }
                                hr(class: 'divider')

                                a(name: 'documenting') {}
                                h2 'Improving the documentation'
                                p 'The documentation of The Grails Framework comes in various forms:'
                                ul {
                                    li {
                                        yield 'the '
                                        a(href: 'single-page-documentation.html', 'reference documentation')
                                        yield ' covering the language specification, the user guides, getting started, and more.'
                                    }
                                    li {
                                        yield 'the '
                                        a(href: 'api.html', 'GroovyDoc APIs')
                                        yield ' documenting the classes of the Grails code base'
                                    }
                                    li {
                                        yield 'this '
                                        a(href: 'https://github.com/grails/grails-static-website', 'website')
                                    }
                                }

                                p """
                                    Contributing to this website is fairly easy, if you have a Github account already,
                                    as you can click on the ${$em('Improve this doc')} buttons that you can see on all the pages of this website.
                                    So don't hesitate to help us improve it, fix typos, broken language, clarify complicated sections,
                                    add new material, etc.
                                """
                                p 'Please check the following section for more information on how to contribute to our codebase.'
                                hr(class: 'divider')

                                a(name: 'code') {}
                                h2 'Contributing code'
                                p {
                                    yield '''
                                        If you know the area you want to contribute to, this is great, but if you are looking to make an initial contribution just raise your hand on the Grails developer '''
                                    a(href: 'mailing-lists.html', 'mailing-list')
                                    yield ''' to tell us about your desire to work on a particular problem.'''
                                }
                                p '''
                                    For more complicated tasks, the best approach is also to bring that to the attention of the Grails developers,
                                    so they can give you some guidance on how best to tackle a particular problem, discuss implementation ideas
                                    and the semantics or scope of the proposed change.
                                '''

                                h3 'Cloning the code base'
                                p {
                                    yield 'To work on the Grails code base, you should be proficient enough in '
                                    a(href: 'http://git-scm.com/', 'git')
                                    yield ' and you should ideally have an account on '
                                    a(href: 'https://github.com/', 'Github')
                                    yield ' to be able to create '
                                    a(href: 'https://help.github.com/articles/creating-a-pull-request', 'pull requests')
                                    yield ' with your changes.'
                                }
                                p 'If you have git installed on your machine, you should be able to clone the Grails repository with the following command:'
                                pre { code 'git clone git://github.com/grails/grails-core.git' }
                                p 'Make sure you configure Git appropriately with the same email that you registered with on Github:'
                                pre { code 'git config --global user.name "YOUR NAME"\n' +
                                        'git config --global user.email "YOUR EMAIL"' }
                                p 'You can verify these are configured appropriately by running:'
                                pre { code 'git config --list' }

                                h3 'Working on the code base'
                                p {
                                    yield 'If you are working with the IntelliJ IDEA development environment then you can import the project using the Intellij '
                                    yield 'Gradle Tooling ( "File / Import Project" and select the "build.gradle" file).'
                                }
                                p 'The most important command you will have to run before sending your changes is the test command:'
                                pre { code './gradlew test' }
                                p 'For a successful contribution, all tests should be green!'

                                h3 'Creating a pull request'
                                p 'Once you are satisfied with your changes:'
                                ul {
                                    li 'commit your changes in your local branch'
                                    li 'push your changes to your remote branch on Github'
                                    li {
                                        yield 'send us a '
                                        a(href: 'https://help.github.com/articles/creating-a-pull-request', 'pull requests')
                                    }
                                }
                            }
                            hr(class: 'divider')

                            h2('Build status')

                            p "The Grails sources are tested thanks our ${$a(href:'buildstatus.html','continuous integration server')}."
                        }
                    }
                }
            }
        }
