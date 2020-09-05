package org.grails.documentation

import groovy.transform.CompileDynamic
import groovy.xml.MarkupBuilder
import org.grails.PageElement

trait GuideGroupHtml implements PageElement {

    abstract String getUlId()

    abstract String getImage()

    abstract String getTitle()

    abstract  List<GuideGroupItem> getItems()

    abstract String getDescription()

    @CompileDynamic
    @Override
    String renderAsHtml() {
        renderAsHtmlWithStyleAttr(null)
    }

    String renderAsHtmlWithStyleAttr(String styleAttr) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        final int imageMarginRight = 10
        html.div(class: "guidegroup", style: "${styleAttr ?: ''}") {
            div(class: "guidegroupheader") {
                img src: image, alt: title
                h2 {
                    html.mkp.yieldUnescaped title
                }
            }
            ul(id: getUlId() ?: '') {
                if ( description ) {
                    li(class: 'legend', description)
                }
                if ( items ) {
                    for ( GuideGroupItem item : items ) {
                        if ( item.href ) {
                            li {
                                if (item.image) {
                                    img width: 70, class: 'align-left', style: "margin-right: ${imageMarginRight}px;", src: "${item.image}" as String, alt: item.title
                                }
                                a href: item.href, item.title
                                if (item.legend) {
                                    p(item.legend)
                                }
                            }
                        } else {
                            li(item.title)
                        }
                    }
                }
            }
        }
        writer.toString()
    }
}
