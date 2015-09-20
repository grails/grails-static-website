layout 'layouts/main.groovy', true,
        pageTitle: 'Grails Framework - Download',
        mainContent: contents {
            div(id: 'content', class: 'page-1') {
                div(class: 'row') {
                    div(class: 'row-fluid') {
                        div(class: 'col-lg-3') {
                            ul(class: 'nav-sidebar') {
                                li(class: 'active') {
                                    a(href: 'download.html') { strong('Download Grails') }
                                }
                                li {
                                    a(href: '#distro', class: 'anchor-link', 'Distributions')
                                }
                                li {
                                    a(href: '#sdkman', class: 'anchor-link', 'Through SDKMAN!')
                                }
                                li {
                                    a(href: 'https://github.com/grails/grails-core/releases', class: 'anchor-link', 'Release Notes')
                                }
                            }
                        }

                        div(class: 'col-lg-8 col-lg-pull-0') {
                            include template: 'includes/contribute-button.groovy'
                            h1 {
                                i(class: 'fa fa-cloud-download') {}
                                yield ' Download'
                            }
                            def linkVersionToDownload = distributions.reverse().collect { it.packages }.flatten().find { it.stable }.version
                            button(id: 'big-download-button', type: 'button', class: 'btn btn-default',
                                    title: "Download Grails ${linkVersionToDownload}",
                                    onclick: "window.location.href=\"https://github.com/grails/grails-core/releases/download/v${linkVersionToDownload}/grails-${linkVersionToDownload}.zip\"") {
                                i(class: 'fa fa-download') {}
                                yield ' Download'
                            }
                            article {
                                p {
                                    yield 'In this download area, you will be able to download the binary distribution and the documentation for Grails.'
                                }
                                p {
                                    yield 'For a quick and effortless start on Mac OSX, Linux or Cygwin, you can use '
                                    a(href: 'http://sdkman.io', 'SDKMAN! (The Software Development Kit Manager)')
                                    yield ' to download and configure any Grails version of your choice. Basic '
                                    a(href: '#sdkman', 'instructions')
                                    yield ' can be found below. '
                                    br()
                                    yield 'Windows users can use '
                                    a(href: 'https://github.com/flofreud/posh-gvm', 'Posh-GVM')
                                    yield ' (POwerSHell Groovy enVironment Manager), a PowerShell clone of the GVM CLI.'
                                }
                            }
                            hr(class: 'divider')

                            def renderDistribution = { dist ->
                                h2 {
                                    i(class: 'fa grails-icon') {
                                        img src:"img/grails-cupsonly-logo-black.svg"
                                    }

                                    yield " ${dist.name}"
                                }
                                if (dist.description) {
                                    p {
                                        dist.description.rehydrate(this, this, this)()
                                    }
                                }
                                dist.packages.each { pkg ->
                                    h3 "${pkg.version} distributions"
                                    table(width: '100%', class: 'download-table') {
                                        tr {
                                            td {
                                                a(href: "https://github.com/grails/grails-core/releases/download/v${pkg.version}/grails-${pkg.version}.zip") {
                                                    i(class: 'fa fa-gears fa-4x') {}
                                                    br()
                                                    yield 'binary'
                                                }
                                            }

                                            td {

                                                def majorVersion = pkg.version[0].toInteger()
                                                def subProject = (majorVersion >= 3 || pkg.version > '2.4.4' || pkg.version >= '2.5.0') ? 'doc' : 'core'
                                                a(href: "https://github.com/grails/grails-${subProject}/releases/download/v${pkg.version}/grails-docs-${pkg.version}.zip") {
                                                    i(class: 'fa fa-file-text fa-4x') {}
                                                    br()
                                                    yield ' documentation'
                                                }
                                            }

                                            td {
                                                a(href: "https://github.com/grails/grails-core/releases/tag/v${pkg.version}") {
                                                    i(class: 'fa fa-file-text fa-4x') {}
                                                    br()
                                                    yield ' release notes'
                                                }
                                            }
                                        }
                                    }
                                    p {
                                        yield 'Consult the '
                                        a(href: pkg.releaseNotes, ' release notes')
                                        yield ' for more information. For historical release notes, refer to '
                                        a(href: 'https://github.com/grails/grails-core/releases', ' Github')
                                    }
                                }
                            }

                            def stableDistributions = distributions.findAll() { it.packages.every {  it.stable } }

                            a(name: 'distro') {}
                            article {
                                h1 'Distributions'
                                p 'You can download a binary or a documentation bundle.'




                                stableDistributions.pop().each renderDistribution
                                def betaDistribution = distributions.find() { it.packages.any {  !it.stable } }
                                if(betaDistribution) {
                                    renderDistribution betaDistribution
                                }
                            }



                            a(name: 'versions') {}
                            article{
                                h2 'Previous Versions'
                                p 'You can browse the downloads of previous versions of Grails since Grails 1.2.0:'

                                distributions = stableDistributions.reverse()

                                select(class: 'form-control', onchange: "window.location.href='https://github.com/grails/grails-core/releases/download/v'+ this.value +'/grails-' + this.value + '.zip'") {
                                    option 'Select a version'
                                    distributions.each{ dist ->
                                       option "${dist.packages.first().version}"
                                    }
                                }
                            }

                            hr(class: 'divider')

                            a(name: 'sdkman') {}
                            article {
                                h1 'SDKMAN! (The Software Development Kit Manager)'
                                p {
                                    yield 'This tool makes installing Grails on any Bash platform (Mac OSX, Linux, Cygwin, Solaris or FreeBSD) very easy.'
                                    br()
                                    yield 'Simply open a new terminal and enter:'
                                }
                                pre { code '$ curl -s get.sdkman.io | bash' }
                                p {
                                    yield 'Follow the instructions on-screen to complete installation.'
                                    br()
                                    yield 'Open a new terminal or type the command:'
                                }
                                pre { code '$ source "$HOME/.sdkman/bin/sdkman-init.sh"' }
                                p 'Then install the latest stable Grails:'
                                pre { code '$ sdk install grails' }
                                p 'After installation is complete and you\'ve made it your default version, test it with:'
                                pre { code '$ grails -version' }
                                p 'That\'s all there is to it!'
                            }
                            hr(class: 'divider')

                    }
                }
            }
        }
    }
