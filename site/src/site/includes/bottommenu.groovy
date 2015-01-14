
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
                            li { a(href: menuItem.link, menuItem.name) }
                        }
                    }
                }
            }
            /*div(class: 'col-right') {
                p {
                    yield "The Grails Framework"; br()
                    yield "is supported by "; a(href: 'http://gopivotal.com', 'Pivotal'); br()
                    yield "and the Groovy community"; br()
                }
                img(src: 'img/pivotal.png', title: 'Pivotal', alt: 'Pivotal')
            }*/
        }
        div(class: 'clearfix', "&copy; 2005-${Calendar.instance[Calendar.YEAR]} the Grails project &mdash; Grails is Open Source, ${$a(href: 'http://www.apache.org/licenses/LICENSE-2.0.html', 'Apache 2 License')}")
    }
}