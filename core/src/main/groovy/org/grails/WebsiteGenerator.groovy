package org.grails

import groovy.transform.CompileStatic
import org.grails.pages.HtmlPage

import java.text.SimpleDateFormat

@CompileStatic
class WebsiteGenerator {

    static savePage(HtmlPage page, String filename) {
        String text = page.html()
        String preffix = "build/site/"
        File f = new File("${preffix}${filename}")
        f.createNewFile()
        f.text = text
    }

    static copyAsset(String assetType, String fileName, String timestamp) {
        File f = new File("../plugin/grails-app/assets/${assetType}/${fileName}")
        String text = f.text
        String preffix = "build/site/${assetType}/"
        File output = new File("${preffix}${timestamp}.${fileName}")
        output.createNewFile()
        output.text = text
    }

    static String timestamp() {
        SimpleDateFormat f = new SimpleDateFormat("MddHHmmss");
        f.setTimeZone(TimeZone.getTimeZone("UTC"));
        f.format(new Date())
    }

    static void copyAssetsWithTimestamp(String timestamp) {
        WebsiteGenerator.copyAsset('stylesheets', 'screen.css', timestamp)
        WebsiteGenerator.copyAsset('javascripts', 'navigation.js', timestamp)
        WebsiteGenerator.copyAsset('javascripts', 'oci-training.js', timestamp)
        WebsiteGenerator.copyAsset('javascripts', 'search.js', timestamp)
    }
}