package model

import groovy.transform.CompileStatic

@CompileStatic
class Course {
    String title
    String dates
    int hours
    String instructor
    String location
    String url

    void dates(String dates) {
        this.dates = dates
    }

    void hours(int hours) {
        this.hours = hours
    }

    void instructor(String instructor) {
        this.instructor = instructor
    }

    void location(String location) {
        this.location = location
    }

    void url(String url) {
        this.url = url
    }

}
