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
            li(class: 'active') { a(href: addBaseToUri("${page}.html", baseUri)) { strong(label) } }
        } else {
            li { a(href: addBaseToUri("${page}.html", baseUri), label) }
        }
    }
}
br()
include unescaped: 'html/twittersearch.html'
