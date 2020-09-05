package org.grails.questions

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.markdown.MarkdownUtil
import org.yaml.snakeyaml.Yaml

@CompileStatic
class QuestionsPage {
    @CompileDynamic
    static String mainContent(File questions) {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)
        Yaml yaml = new Yaml()
        Map model = yaml.load(questions.newDataInputStream())
        List<Question> questionList = model['questions'].collect {
            new Question(title: it['title'], answer: it['answer'], slug: it['slug'])
        }
        html.div(class: 'headerbar chalicesbg') {
            html.div(class: 'content') {
                h1 'Questions'
            }
        }
        html.div(class: 'content') {
            article(id: 'questions') {
                for( Question question : questionList) {
                    div(class: 'question', id: question.slug) {
                        h2(class: 'columnheader', question.title)
                        mkp.yieldUnescaped(MarkdownUtil.htmlFromMarkdown(question.answer.replace('\n', '<br/>'))
                                )
                    }
                }
            }
        }
        writer.toString()
    }
}
