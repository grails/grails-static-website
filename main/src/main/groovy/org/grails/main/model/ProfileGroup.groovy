package org.grails.main.model

import groovy.transform.CompileStatic

@CompileStatic
class ProfileGroup implements ProfileGroupHtml {
    String image
    String title
    String description
    List<Profile> profiles
}
