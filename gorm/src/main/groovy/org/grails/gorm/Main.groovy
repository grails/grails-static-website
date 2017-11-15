package org.grails.gorm

import groovy.transform.CompileStatic
import org.grails.WebsiteGenerator
import org.grails.gorm.pages.GormHomePage
import org.grails.pages.Page

@CompileStatic
class Main {
    static void main(String[] args) {

        String timestamp = WebsiteGenerator.timestamp()
        WebsiteGenerator.copyAssetsWithTimestamp(timestamp)

        Page page = new GormHomePage()
        page.timestamp = timestamp

        String text = page.html()
        String folder = "build/site"
        String filename = "${folder}/${page.slug}"
        File f = new File(filename)
        f.createNewFile()
        f.text = text
    }
}