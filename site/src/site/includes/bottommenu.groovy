// footer
footer(id: 'footer') {
    div(class: 'row') {
        div(class: 'colset-3-footer') {
            menu.entrySet().eachWithIndex { entry, i ->
                def (name, menu) = [entry.key, entry.value]
                div(class: "col-${i+1}") {
                    h1(name)
                    ul {
                        menu.each { menuItem ->
                            li { a(href: addBaseToUri(menuItem.link, baseUri), menuItem.name) }
                        }
                    }
                }
            }
            div(class: 'col-right') {
                p {
                    yield "The Grails Framework"; br()
                    yield "repository is hosted by "; a(href: 'http://artifactoryonline.com', 'Artifactory'); br()
                    yield "Website hosting is provided by "; a(href: 'http://run.pivotal.io', 'Pivotal'); br()
                }
                a(href: 'http://artifactoryonline.com') {
                    img(width:150,src: "${baseUri}img/logos/artifactory.png", title: 'Artifactory Online', alt: 'Aritfactory Online')
                }; br()

                a(href: 'http://run.pivotal.io') {
                    img(src: "${baseUri}img/pws-thumb.png", title: 'Pivotal Web Service', alt: 'Pivotal Web Service')
                }; br()


            }
        }
        div(class: 'clearfix', "&copy; 2005-${Calendar.instance[Calendar.YEAR]} the Grails project &mdash; Grails is Open Source, ${$a(href: 'http://www.apache.org/licenses/LICENSE-2.0.html', 'Apache 2 License')}")
    }
}
