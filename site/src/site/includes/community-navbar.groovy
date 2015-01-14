
ul(class: 'nav-sidebar') {
    [
            'community': 'Community',
            'contribute': 'Contribute',
            'sponsors': 'Sponsors',
            'mailing-lists': 'Mailing-lists',
            'events': 'Events',
            'usergroups': 'User groups'
    ].each { page, label ->
        if (currentPage == page) {
            li(class: 'active') { a(href: "${page}.html") { strong(label) } }
        } else {
            li { a(href: "${page}.html", label) }
        }
    }
}
br()
include unescaped: 'html/twittersearch.html'
