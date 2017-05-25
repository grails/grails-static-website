package model

import groovy.transform.CompileStatic

@CompileStatic
class CourseCatalogue extends LinkedHashMap<String, Course> {
    void course(String title, Closure courseClosure) {
        def course = new Course(title: title)
        def clone = courseClosure.rehydrate(course, course, course)
        clone()
        put(title, course)
    }
}
