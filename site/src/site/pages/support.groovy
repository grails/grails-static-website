import model.Book
import model.Video

layout 'layouts/main.groovy', true,
        pageTitle: 'Grails - Commercial Support',
        extraStyles: ['book.css'],
        mainContent: contents {
            div(id: 'content', class: 'page-1') {
                div(class: 'row') {
                    div(class: 'row-fluid') {
                        div(class: 'col-lg-3') {
                            ul(class: 'nav-sidebar') {
                                li(class: 'active') {
                                    a(href: '#') { strong('Support') }
                                }
                                li {
                                    a(href: 'https://objectcomputing.com/products/grails/consulting-support/', class: 'anchor-link', 'Information')
                                }
                                li {
                                    a(href: '#contact', class: 'anchor-link', 'Contact')
                                }
                            }
                        }

                        div(class: 'col-lg-8 col-lg-pull-0') {
                            
                            h1 {
                                i(class: 'fa') {
                                    img src:"http://objectcomputing.com/files/4814/3109/5657/e-sig-logo.png"
                                }
                                yield ' Commercial Support'
                            }
                            article {
                                p 'OCI’s Subject Matter Experts (SMEs) are available to provide up to 24/7 real-time technical support for Grails. The OCI team can assist you with:'

                                hr(class: 'divider')
                                ul {
                                    li "Architecture and design review;"
                                    li "Rapid prototyping, troubleshooting and debugging;"
                                    li "Customizing, modifying and extending the product;"
                                    li "Integration assistance and support;"
                                    li "Offering mentors, architects, and engineers to work alongside your team providing assistance and thought leadership throughout the life cycle of your project."
                                }

                                p "OCI offers flexible, customizable open source support service with direct access to a dedicated team of architects and engineers, whom developed the product and/or have spent their careers supporting and maturing it."

                                p {
                                    a(href:"https://objectcomputing.com/products/grails/consulting-support/", "https://objectcomputing.com/products/grails/consulting-support/")
                                }

                                p {

                                    yield "We build high-performance, real-time, mission-critical middleware systems and integration solutions. Our goal is to make solutions more open, scalable, reusable, interoperable, and affordable. Please visit "
                                    a( href:"http://objectcomputing.com", "objectcomputing.com")
                                    yield " to learn more about our "
                                    a(href:"https://objectcomputing.com/services/software-engineering/", "engineering services")
                                    yield ", "
                                    a(href:"https://objectcomputing.com/products/", "Open Source middleware technologies")
                                    yield ", and "
                                    a(href:"https://objectcomputing.com/training/catalog/grails/", "Grails training")
                                    yield "."
                                }

                                a( name:"contact" ) {}
                                h2 {
                                    i(class: 'fa') {}
                                    yield 'Contact'
                                }
                                p {
                                    strong "If you would like to find out more about OCI’s engineering services, please contact us."
                                }

                                
                                ul {
                                    li "12140 Woodcrest Executive Drive, Suite 250 Saint Louis, MO 63141, USA"
                                    li {
                                        yield "Tel: "
                                        a(href:"tel:+1-314-579-0066", "01*314*579*0066")
                                    }
                                    li  {
                                        yield "Email: "
                                        a(href:"mailto:info@objectcomputing.com", "info@objectcomputing.com")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
