package org.grails.gorm.model

import groovy.transform.CompileStatic

@CompileStatic
class GormImplementation implements GormImplementationHtml {
    String name
    String image
    String hrefDocumentation
    String hrefApi
    String hrefSource
    String description

}
