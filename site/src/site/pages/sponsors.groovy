layout 'layouts/main.groovy', true,
        pageTitle: 'The Grails Framework - Sponsors',
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
                                i(class: 'fa fa-building-o') {}
                                yield ' Sponsors'
                            }
                            article {
                                p "The Grails project is generously sponsored by several companies:"
                                ul {
                                    li "${$a(href: 'http://www.ociweb.com/products/grails/', 'OCI')} employs key members of the Grails team"        
                                    li "${$a(href: 'http://pivotal.io/', 'Pivotal')} provide website hosting"
                                    li """
                                        ${$a(href: 'http://www.jfrog.com/', 'JFrog')} provide the infrastructure
                                        for deploying and hosting our snapshots and releases,
                                        thanks to the ${$a(href: 'https://bintray.com/', 'Bintray')} social platform for distribution,
                                        and the OSS ${$a(href: 'http://www.jfrog.com/home/v_artifactory_opensource_overview', 'Artifactory')} repository.
                                    """

                                    li """
YourKit supports open source projects with its full-featured Java Profiler. 
YourKit, LLC is the creator of <a href="https://www.yourkit.com/java/profiler/index.jsp">YourKit Java Profiler</a> 
and <a href="https://www.yourkit.com/.net/profiler/index.jsp">YourKit .NET Profiler</a>, 
innovative and intelligent tools for profiling Java and .NET applications. 
                                    """
                                }
                            }
                            hr(class: 'divider')
                        }
                    }
                }
            }
        }
