package org.grails

import groovy.json.JsonSlurper
import groovy.time.TimeCategory
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.grails.model.GrailsMayorVersion
import org.grails.model.GrailsVersionedGuide
import org.grails.model.Guide
import org.grails.model.SingleGuide

import java.text.DateFormat
import java.text.SimpleDateFormat

@CompileStatic
class GuidesFetcher {

    public static final String VERSION_NAME = 'grailsVersion'

    static String versionNumber(String githubSlug) {
        Properties props = new Properties()
        String uri = "https://raw.githubusercontent.com/${githubSlug}/master/complete/gradle.properties"
        try {
            props.load(new URL(uri).newInputStream())
            return props.getProperty(VERSION_NAME)
        } catch(FileNotFoundException e) {
            println "file not found exception thrown at uri: " + uri
            return null
        }
    }

    public static final String GUIDES_JSON = 'https://raw.githubusercontent.com/grails/grails-guides/gh-pages/guides.json'

    @CompileDynamic
    static List<Guide> fetchGuides(boolean skipFuture = true) {
        URL url = new URL(GUIDES_JSON)
        def jsonArr = new JsonSlurper().parseText(url.text)
        DateFormat dateFormat = new SimpleDateFormat('dd MMM yyyy',
                Locale.US)

        Map<String, Set<String>> githubSlugsAndBranches = [:]
        jsonArr.each { it ->
            if (githubSlugsAndBranches.containsKey(it.githubSlug)) {
                Set<String> arr = githubSlugsAndBranches[it.githubSlug]
                arr << it.githubBranch ?: 'master'
                githubSlugsAndBranches[it.githubSlug] = arr
            } else {
                githubSlugsAndBranches[it.githubSlug] = [it.githubBranch ?: 'master'] as HashSet<String>
            }
        }
        List<Guide> guides = []
        for (String githubSlug : githubSlugsAndBranches.keySet()) {
            if (githubSlugsAndBranches[githubSlug].size() == 1) {
                def guideArr = jsonArr.find {
                    it.githubSlug == githubSlug &&
                            (!it.githubBranch || githubSlugsAndBranches[githubSlug].any { branch -> branch == it.githubBranch})
                }
                if (guideArr) {
                    Guide guide = new SingleGuide(
                            versionNumber: guideArr.grailsVersion,
                            authors: guideArr.authors as List,
                            category: guideArr.category,
                            githubSlug: guideArr.githubSlug,
                            githubBranch: guideArr.githubBranch,
                            name: guideArr.name,
                            title: guideArr.title,
                            subtitle: guideArr.subtitle,
                            tags: guideArr.tags as List
                    )
                    if (guideArr.publicationDate) {
                        guide.publicationDate = dateFormat.parse(guideArr.publicationDate as String)
                    }
                    guides << guide
                }
            } else if (githubSlugsAndBranches[githubSlug].size() > 1) {
                GrailsVersionedGuide guide
                for (String githubBranch : githubSlugsAndBranches[githubSlug]) {
                    def guideArr = jsonArr.find {
                        it.githubSlug == githubSlug &&
                                it.githubBranch == githubBranch
                    }
                    if (guideArr) {
                        if (!guide) {
                            guide = new GrailsVersionedGuide()
                        }
                        guide.versionNumber = guideArr.grailsVersion
                        guide.authors = guideArr.authors as List
                        guide.category = guideArr.category
                        guide.githubSlug = guideArr.githubSlug
                        guide.githubBranch = guideArr.githubBranch
                        guide.name = guideArr.name
                        guide.title = guideArr.title
                        guide.subtitle = guideArr.subtitle
                        if (githubBranch == 'grails3') {
                            guide.grailsMayorVersionTags[GrailsMayorVersion.GRAILS_3] = guideArr.tags
                        } else if (githubBranch == 'master') {
                            guide.grailsMayorVersionTags[GrailsMayorVersion.GRAILS_4] = guideArr.tags
                        }
                        if (guideArr.publicationDate) {
                            guide.publicationDate = dateFormat.parse(guideArr.publicationDate as String)
                        }
                    }
                }
                if (guide) {
                    guides << guide
                }
            }
        }
        if(skipFuture) {
            guides = guides.findAll { it.publicationDate.before(tomorrow()) }
        }
        guides.sort { Guide a, Guide b ->
            b.publicationDate <=> a.publicationDate
        }
    }

    @CompileDynamic
    static Date tomorrow() {
        Date tomorrow = new Date()
        use(TimeCategory) {
            tomorrow = tomorrow += 1.day
        }
        tomorrow
    }
}
