package org.grails.gorm

import groovy.transform.CompileStatic
import org.grails.gorm.pages.GormHomePage
import org.grails.pages.Page

@CompileStatic
class Main {
    static void main(String[] args) {
        Page page= new GormHomePage()
        String text = page.html()
        String folder = "build/site"
        String filename = "${folder}/${page.slug}"
        File f = new File(filename)
        f.createNewFile()
        f.text = text
    }
}