package org.grails.guides.single

import groovy.transform.CompileStatic
import org.grails.pages.Page

@CompileStatic
class Main {
    static void main(String[] args) {
        Page page= new GuidePage()
        String text = page.html()
        String folder = "/Users/sdelamo/git/grails/grails-guides/src/main/resources/style"
        //String folder = "build/site/style"
        String filename = "${folder}/${page.slug}"
        File f = new File(filename)
        f.createNewFile()
        f.text = text
    }
}