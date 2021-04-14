package org.grails.gradle


import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import groovy.xml.MarkupBuilder
import org.grails.ContentAndMetadata
import org.grails.HtmlMinutes
import org.grails.MarkdownMinutes
import org.grails.MinutesMetadata
import org.grails.MinutesMetadataAdaptor
import org.grails.documentation.SiteMap
import org.grails.documentation.SoftwareVersion
import org.grails.markdown.MarkdownUtil
import io.micronaut.rss.DefaultRssFeedRenderer
import io.micronaut.rss.RssChannel
import io.micronaut.rss.RssFeedRenderer
import io.micronaut.rss.RssItem
import org.gradle.api.Action
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.CopySpec
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

@CompileStatic
class MinutesTask extends DefaultTask {
    static final SimpleDateFormat MMM_D_YYYY_HHMM = new SimpleDateFormat("MMM d, yyyy HH:mm")
    static final SimpleDateFormat MMM_D_YYYY = new SimpleDateFormat("MMM d, yyyy")
    static final SimpleDateFormat MMMM_D_YYYY = new SimpleDateFormat("MMMM d, yyyy")
    public static final String RSS_FILE = 'rss.xml'
    final static String SPAN_CLOSE = "</span>"
    public static final String IMAGES = 'images'
    public static final String MINUTES_BG = 'grails-blog-index-6.png'
    public static final String MINUTES = 'foundation/minutes'
    public static final String INDEX = 'index.html'

    public static final int MAX_TITLE_LENGTH = 45

    @Input
    final Property<File> document = project.objects.property(File)

    @Input
    final Property<String> title = project.objects.property(String)

    @Input
    final Property<String> about = project.objects.property(String)

    @Input
    final Property<String> url = project.objects.property(String)

    @Input
    final ListProperty<String> keywords = project.objects.listProperty(String)

    @Input
    final Property<String> robots = project.objects.property(String)

    @InputDirectory
    final Property<File> minutes = project.objects.property(File)

    @OutputDirectory
    final Property<File> output = project.objects.property(File)

    @InputDirectory
    final Property<File> assets = project.objects.property(File)

    File dist() {
        new File(output.get().absolutePath + "/" + RenderSiteTask.DIST)
    }

    @Input
    final Property<File> releases = project.objects.property(File)

    @TaskAction
    void renderMinutes() {
        File template = document.get()
        final String templateText = template.text
        File o = dist()

        File releasesFile = releases.get()
        SoftwareVersion latest = SiteMap.latestVersion(releasesFile)
        List<String> olderVersions = SiteMap.olderVersions(releasesFile).reverse()
        String versions = olderVersions.collect { version -> "<option>${version}</option>" }.join(' ')

        Map<String, String> m = RenderSiteTask.siteMeta(title.get(),
                about.get(),
                url.get(),
                keywords.get() as List<String>,
                robots.get(),
                latest.versionText,
                versions
        )
        copyBackgroundImages()
        List<MarkdownMinutes> listOfMinutes = parseMinutes(minutes.get())
        listOfMinutes = listOfMinutes.sort { a, b ->
            parseDate(a.date).after(parseDate(b.date)) ? -1 : 1
        }
        List<HtmlMinutes> htmlMinutes = processMinutes(m, listOfMinutes)
        File blog = new File(o.absolutePath + '/' + MINUTES)
        blog.mkdirs()
        renderMinutes(m, htmlMinutes, blog, templateText)
    }

    static Date parseDate(String date) throws ParseException {

        try {
            return MMM_D_YYYY_HHMM.parse(date)
        } catch (ParseException e) {
            try {
                return MMM_D_YYYY.parse(date)
            } catch (ParseException ex) {
                throw new GradleException("Could not parse date $date")
            }
        }
    }


    void copyBackgroundImages() {
        File images = new File(assets.get().absolutePath + "/" + "bgimages")
        File outputImages = new File(dist().absolutePath + '/images')
        outputImages.mkdir()
        project.copy(new Action<CopySpec>() {
            @Override
            void execute(CopySpec copySpec) {
                copySpec.from(images)
                copySpec.into(outputImages)
                copySpec.include(CopyAssetsTask.IMAGE_EXTENSIONS)
            }
        })
    }

    static RssItem rssItemWithPage(String title,
                                   Date pubDate,
                                   String link,
                                   String guid,
                                   String html) {
        String htmlWithoutTitleAndDate = html
        if (htmlWithoutTitleAndDate.contains('<span class="date">')) {
            htmlWithoutTitleAndDate = htmlWithoutTitleAndDate.substring(htmlWithoutTitleAndDate.indexOf('<span class="date">'))
            htmlWithoutTitleAndDate = htmlWithoutTitleAndDate.substring(htmlWithoutTitleAndDate.indexOf('</span>') + '</span>'.length())
        }
        RssItem.Builder builder = RssItem.builder()
                .title(title)
                .pubDate(ZonedDateTime.of(Instant.ofEpochMilli(pubDate.time)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime(), ZoneId.of("GMT")))
                .link(link)
                .guid(guid)
                .description(htmlWithoutTitleAndDate)
        builder.build()
    }

    @CompileDynamic
    static String renderMinutesHtml(HtmlMinutes htmlMinutes,
                                    String templateText,
                                    List<HtmlMinutes> minutes) {

        def groupedMinutes = minutes.groupBy {
            parseDate(it.metadata.date)[Calendar.YEAR]
        }

        StringWriter writer = new StringWriter()
        MarkupBuilder mb = new MarkupBuilder(writer)
        mb.div(class: 'headerbar chalicesbg') {
            div(class: 'content') {
                h1 {
                    a(href: '[%url]/foundation/index.html', 'Foundation')
                }
            }
        }

        mb.article(class: 'content container') {
            mb.section(class: 'largegoldenratio align-left foundation-description') {
                mb.div {
                    mkp.yieldUnescaped(htmlMinutes.html)
                }
            }

            mb.section(class: 'smallgoldenratio align-left foundation-boards') {

                mb.div(class: 'meeting-archive-list') {
                    h2 { mkp.yield("Meeting Minutes Archive") }

                    mb.div {
                        groupedMinutes.each { year, yearMinutes ->
                            mb.h3 { mkp.yield(year) }
                            mb.ul {
                                yearMinutes.each { m ->
                                    mb.li {
                                        a(href: minutesLink(m), "${parseDate(m.metadata.date).format("MMM dd")} - ${m.metadata.title}")
                                    }
                                    //<li><a href="/foundation/minutes/20210321-tab.html">Mar 21 - Technology Advisory Board</a></li>
                                }
                            }
                        }
                    }
                }
            }
        }

        String html = writer.toString()
        Map<String, String> metadata = htmlMinutes.metadata.toMap()
        html = RenderSiteTask.renderHtmlWithTemplateContent(html, metadata, templateText)
        html = RenderSiteTask.highlightMenu(html, metadata, htmlMinutes.path)
        metadata['body'] = metadata['body'] ? metadata['body'] : 'foundation minutes'
        if (metadata['body']) {
            html = html.replace("<body>", "<body class='${metadata['body']}'>")
        }
        html
    }

    static List<HtmlMinutes> processMinutes(Map<String, String> globalMetadata, List<MarkdownMinutes> markdownMinutes) {
        markdownMinutes.collect { MarkdownMinutes mdMinutes ->
            Map<String, String> metadata = RenderSiteTask.processMetadata(globalMetadata + mdMinutes.metadata)
            MinutesMetadata minutesMetadata = new MinutesMetadataAdaptor(metadata)
            String markdown = mdMinutes.content
            if (metadata.containsKey('slides')) {
                markdown = markdown + "\n\n[Slides](${metadata['slides']})\n\n"
            }
            if (metadata.containsKey('code')) {
                markdown = markdown + "\n\n[Code](${metadata['code']})\n\n"
            }
            String html = MarkdownUtil.htmlFromMarkdown(markdown)
            String iframe = RenderSiteTask.parseVideoIframe(metadata)
            if (iframe) {
                html = html + iframe
            }

            new HtmlMinutes(metadata: minutesMetadata, html: html, path: mdMinutes.path)
        }
    }

    static void renderMinutes(Map<String, String> globalMetadata,
                              List<HtmlMinutes> listOfMinutes,
                              File outputDir,
                              final String templateText) {
        List<String> minuteCards = []
        List<RssItem> rssItems = []

        for (HtmlMinutes htmlMinutes : listOfMinutes) {
            minuteCards << minutesCard(htmlMinutes)
            String html = renderMinutesHtml(htmlMinutes, templateText, listOfMinutes)
            File pageOutput = new File(outputDir.absolutePath + "/" + htmlMinutes.path)
            pageOutput.createNewFile()
            pageOutput.text = html

            String minutesLink = minutesLink(htmlMinutes)
            rssItems.add(rssItemWithPage(htmlMinutes.metadata.title,
                    parseDate(htmlMinutes.metadata.date),
                    minutesLink,
                    htmlMinutes.path.replace(".html", ""),
                    htmlMinutes.html))
        }
        renderArchive(new File(outputDir.absolutePath + "/index.html"), minuteCards, globalMetadata, templateText)
        renderRss(globalMetadata, rssItems, new File(outputDir.absolutePath + "/../" + RSS_FILE))
    }

    static String minutesLink(HtmlMinutes minutes) {
        minutes.metadata.url + '/' + MINUTES + '/' + minutes.path
    }

    @CompileDynamic
    private static String minutesCard(HtmlMinutes htmlMinutes) {
        StringWriter writer = new StringWriter()
        MarkupBuilder mb = new MarkupBuilder(writer)
        mb.article(class: 'blogcard', style: "margin-bottom: 0; background-image: url(${htmlMinutes.metadata.url + '/' + IMAGES}/${MINUTES_BG})") {
            a(href: minutesLink(htmlMinutes)) {
                h3 {
                    mkp.yield RenderSiteTask.formatDate(htmlMinutes.metadata.date)
                }
                h2 {
                    String title = htmlMinutes.metadata.title
                    if (title.length() > MAX_TITLE_LENGTH) {
                        title = "${title.substring(0, MAX_TITLE_LENGTH)}..."
                    }
                    mkp.yield title
                }
            }
        }
        writer.toString()
    }

    @CompileDynamic
    private static String rssCard(String url) {
        StringWriter writer = new StringWriter()
        MarkupBuilder mb = new MarkupBuilder(writer)
        String imageUrl = url + "/images/feedicon.svg"
        mb.article(class: 'blogcard desktop', style: "background-image: url('${imageUrl}')".toString()) {
            h3 'Feeds'
            h2 {
                a(href: '[%url]/' + RSS_FILE, 'RSS Feed')
            }
        }
        return writer.toString()
    }

    @CompileDynamic
    private static String subscribeCard() {
        StringWriter writer = new StringWriter()
        MarkupBuilder mb = new MarkupBuilder(writer)
        mb.article(class: 'blogcard desktop') {
            mkp.yieldUnescaped('''
<!--[if lte IE 8]>
<script charset="utf-8" type="text/javascript" src="//js.hsforms.net/forms/v2-legacy.js"></script>
<![endif]-->
<script charset="utf-8" type="text/javascript" src="//js.hsforms.net/forms/v2.js"></script>
<script>
  hbspt.forms.create({
\tportalId: "4547412",
\tformId: "a675210e-7748-44bf-b603-3363d613ffb1"
});
</script>
''')
        }
        writer.toString()
    }

    @CompileDynamic
    private static void renderArchive(File f,
                                      List<String> minuteCards,
                                      Map<String, String> sitemeta,
                                      String templateText) {

        List<String> cards = []
        cards.addAll(minuteCards)
        Map<String, String> resolvedMetadata = RenderSiteTask.processMetadata(sitemeta)
        String html = cardsHtml(cards, resolvedMetadata)
        resolvedMetadata['title'] = 'Foundation | Grails Framework'
        html = RenderSiteTask.renderHtmlWithTemplateContent(html, resolvedMetadata, templateText)

        html = RenderSiteTask.highlightMenu(html, resolvedMetadata, "/" + MINUTES + "/" + INDEX)
        f.createNewFile()
        f.text = html
    }


    @CompileDynamic
    static String cardsHtml(List<String> cards, Map<String, String> meta, String title = null) {
        StringWriter writer = new StringWriter()
        MarkupBuilder mb = new MarkupBuilder(writer)

        mb.div(class: 'headerbar chalicesbg') {

            div(class: 'content') {
                if (title) {
                    mkp.yieldUnescaped(title)
                } else {
                    h1 {
                        a(href: '[%url]/foundation/index.html', 'Foundation')
                    }
                }
            }
        }
        mb.div(class: 'clear content container') {
            h3 class: 'columnheader', style: 'margin-bottom: 0', 'Meeting Minutes'
            p('Archive of past meeting minutes of the Technology Advisory Board')
            div(class: 'light') {
                div(class: 'padded', style: 'padding-top: 0;') {
                    for (int i = 0; i < cards.size(); i++) {
                        if (i == 0) {
                            mkp.yieldUnescaped('<div class="threecolumns">')
                        }
                        div(class: 'column') {
                            mkp.yieldUnescaped(cards[i])
                        }

                        if (i != 0 && ((i + 1) % 3 == 0)) {
                            mkp.yieldUnescaped('</div>')
                            if (i != (cards.size() - 1)) {
                                mkp.yieldUnescaped('<div class="threecolumns">')
                            }
                        }
                    }
                }
            }
        }
        writer.toString()
    }

    private static void renderRss(Map<String, String> sitemeta, List<RssItem> rssItems, File outputFile) {
        RssChannel.Builder builder = RssChannel.builder(sitemeta['title'], sitemeta['url'], sitemeta['description'])
        builder.pubDate(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("GMT")))
        builder.lastBuildDate(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("GMT")))
                .docs("https://blogs.law.harvard.edu/tech/rss")
                .generator("Micronaut RSS")
                .managingEditor("delamos@objectcomputing.com")
                .webMaster("delamos@objectcomputing.com")
        for (RssItem item : rssItems) {
            builder.item(item)
        }
        FileWriter writer = new FileWriter(outputFile)
        RssFeedRenderer rssFeedRenderer = new DefaultRssFeedRenderer()
        rssFeedRenderer.render(writer, builder.build())
        writer.close()
    }


    static List<MarkdownMinutes> parseMinutes(File minutes) {
        List<MarkdownMinutes> listOfMinutes = []
        minutes.eachFile { file ->
            if (file.path.endsWith(".md") || file.path.endsWith(".markdown")) {
                ContentAndMetadata contentAndMetadata = RenderSiteTask.parseFile(file)
                listOfMinutes << new MarkdownMinutes(filename: file.name, content: contentAndMetadata.content, metadata: contentAndMetadata.metadata)
            }
        }
        listOfMinutes
    }


}
