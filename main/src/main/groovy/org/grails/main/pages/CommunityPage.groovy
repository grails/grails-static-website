package org.grails.main.pages

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.Navigation
import org.grails.main.SiteMap
import org.grails.main.model.Event
import org.grails.main.model.UserGroup
import org.grails.model.GuideGroup
import org.grails.model.GuideGroupItem
import org.grails.model.MenuItem
import org.grails.model.PageElement
import org.grails.model.TextMenuItem
import org.grails.pages.Page

@CompileStatic
class CommunityPage extends Page {
    String slug = 'community.html'
    String title = 'Community'
    String bodyClass = ''

    @Override
    MenuItem menuItem() {
        Navigation.communityMenuItem(grailsUrl())
    }

    GuideGroup guideGroupWithUserGroupRegion(String region) {
        List<GuideGroupItem> items = SiteMap.USER_GROUPS.findAll { it.region == region
        }.collect { UserGroup userGroup ->
            new GuideGroupItem(title: "${userGroup.country} - ${userGroup.title}", href: userGroup.href)
        }
        new GuideGroup(title: region,
                image: "${getImageAssetPreffix()}usergroup.svg",
                items: items)
    }

    @CompileDynamic
    @Override
    String mainContent() {
        StringWriter writer = new StringWriter()
        MarkupBuilder html = new MarkupBuilder(writer)

        List<Event> events = SiteMap.EVENTS
        events.each {
            it.image = "${getImageAssetPreffix()}${it.image}"
        }
        html.div(class: "content") {
            article {
                h3 class: "columnheader", 'Grails Rock Star Wall of Fame'
                div(class: 'threecolumns') {
                    div(class: 'column') {
                        img class: 'center', height: 200, src: "${getImageAssetPreffix()}graeme.png", alt: 'Graeme Rocher - Grails lifetime contributor Award'
                    }
                    div(class: 'column') {
                        img class: 'center', height: 200, src: "${getImageAssetPreffix()}jeff_scott_brown.png", alt: 'Jeff Scott Brown - Grails lifetime contributor Award'
                    }
                    div(class: 'column') {
                        img class: 'center', height: 200, src: "${getImageAssetPreffix()}eric_helgeson.png", alt: 'Eric Helgeson - 2017 Grails Rock Star Award'
                    }
                }
            }
            article {
                h3 class: "columnheader", 'Conferences'
                p 'Grails and its ecosystem are often represented at various Java-oriented conferences, but there are particular events fully dedicated to the Groovy/Grails ecosystem. Here are the upcoming events you might be interested in learning about.'
                mkp.yieldUnescaped new TwoColumnsPageElement(events as List<PageElement>).renderAsHtml()
            }
            h3 class: "columnheader", 'User Groups', style: 'margin-bottom: 0'
            div(class: 'twocolumns') {
                div(class: 'column') {
                    mkp.yieldUnescaped guideGroupWithUserGroupRegion('North-America').renderAsHtml()
                }
                div(class: 'column') {
                    mkp.yieldUnescaped guideGroupWithUserGroupRegion('South-America').renderAsHtml()
                    mkp.yieldUnescaped guideGroupWithUserGroupRegion('Asia').renderAsHtml()
                    mkp.yieldUnescaped guideGroupWithUserGroupRegion('Europe').renderAsHtml()
                }
            }
        }
        writer.toString()
    }
}
