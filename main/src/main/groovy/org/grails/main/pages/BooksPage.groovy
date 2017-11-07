package org.grails.main.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.main.SiteMap
import org.grails.main.model.Book
import org.grails.model.MenuItem
import org.grails.model.TextMenuItem
import org.grails.model.PageElement
import org.grails.pages.Page

@CompileStatic
class BooksPage extends Page {
    String slug = 'books.html'
    String bodyClass = ''
    String title = 'Books'

    @Override
    MenuItem menuItem() {
        new TextMenuItem(href: "${grailsUrl()}/books.html", title: 'Books')
    }

    @CompileDynamic
    @Override
    String mainContent() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        html.div(class: 'content') {
            setOmitEmptyAttributes(true)
            setOmitNullAttributes(true)
            h3 class: "columnheader", 'Grails Books'
            mkp.yieldUnescaped new TwoColumnsPageElement(booksWithImagePath(SiteMap.GRAILS_BOOKS) as List<PageElement>).renderAsHtml()
            h3 class: "columnheader", 'Groovy Books'
            mkp.yieldUnescaped new TwoColumnsPageElement(booksWithImagePath(SiteMap.GROOVY_BOOKS) as List<PageElement>).renderAsHtml()
        }
        writer.toString()
    }

    List<Book> booksWithImagePath(List<Book> books) {
        books.each {
            it.image = "${getImageAssetPreffix()}${it.image}"
        }
        books
    }
}
