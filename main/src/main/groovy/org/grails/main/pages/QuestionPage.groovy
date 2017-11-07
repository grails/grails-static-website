package org.grails.main.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.main.SiteMap
import org.grails.model.MenuItem
import org.grails.model.TextMenuItem
import org.grails.main.model.Question
import org.grails.pages.Page

@CompileStatic
class QuestionPage extends Page {
    String title = 'Questions'
    String slug  = 'faq.html'
    String bodyClass = ''

    @Override
    MenuItem menuItem() {
        new TextMenuItem(href: "${grailsUrl()}/faq.html", title: 'FAQ')
    }

    @CompileDynamic
    @Override
    String mainContent() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        List<Question> questionList = SiteMap.QUESTIONS
        html.div(class: 'content') {
            article(id: 'questions') {
                for( Question question : questionList) {
                    mkp.yieldUnescaped question.renderAsHtml()
                }
            }
        }
        writer.toString()
    }
}
