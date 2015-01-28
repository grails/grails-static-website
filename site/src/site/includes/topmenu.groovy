def addBaseToUri(uri, baseUri) {
    if(uri.startsWith('/') || uri.contains('://')) {
        return uri
    }
    return baseUri + uri
}

div(class: 'navbar navbar-default navbar-static-top', role: 'navigation') {
    div(class: 'container') {
        div(class: 'navbar-header') {
            button(type: 'button', class: 'navbar-toggle', 'data-toggle': 'collapse', 'data-target': '.navbar-collapse') {
                span(class: 'sr-only') {}
                span(class: 'icon-bar') {}
                span(class: 'icon-bar') {}
                span(class: 'icon-bar') {}
            }
            a(class: 'navbar-brand', href: "${baseUri}index.html") {
                i(class: 'fa grails-icon') {
                    img src:"${baseUri}img/grails-cupsonly-logo-white.svg"
                }
                yield ' Grails'
            }
        }
        div(class: 'navbar-collapse collapse') {
            ul(class: 'nav navbar-nav navbar-right') {
                menu['Grails'].each { menuItem ->
                    li(class: category == menuItem.name ? 'active' : '') { a(href: addBaseToUri(menuItem.link, baseUri), menuItem.name) }
                }
                li {
                    a('data-effect': 'st-effect-9', class: 'st-trigger', href: '#', 'Socialize')
                }
                li(class: (category == 'Search') ? 'active' : '') {
                    a(href: "${baseUri}search.html") {
                        i(class: 'fa fa-search') {}
                    }
                }
            }
        }
    }
}