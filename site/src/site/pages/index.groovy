import model.Event
import model.Course

layout 'layouts/main.groovy', true,
        pageTitle: 'The Grails Framework',
        mainContent: contents {
            div(id: 'band', class: 'band') {

            }
            div(id: 'content') {
                include unescaped: 'html/index.html'

                int numberOfEvents = 3
                if ( onlineTrainingCatalogue ) {
                    numberOfEvents--
                }
                if ( onsiteTrainingCatalogue ) {
                    numberOfEvents--
                }
                def upcomingEvents = allEvents.keySet().take(numberOfEvents)
                section(class: 'row colset-3-article first-event-row') {
                    h1 { strong "Groovy and Grails events you shouldn't miss!" }
                    upcomingEvents.each { String eventName ->
                        Event event = allEvents[eventName]
                        article {
                            div(class: 'content') {
                                // Note that the event image should be 257x180 to look nice
                                div(class: 'event-img', style: "background-image: url(${event.logo})") {}
                                h1 {
                                    a(href: event.url) {
                                        strong eventName
                                        br()
                                        em event.location
                                    }
                                }
                                time event.date
                                yieldUnescaped event.description
                            }
                        }
                    }
                    if ( onlineTrainingCatalogue ) {
                        article {
                            div(class: 'content') {
                                h2 {
                                    i(class: 'fa fa-graduation-cap') {}
                                    yield ' Online Training'
                                }
                                onlineTrainingCatalogue.each {
                                    Course course = it.value
                                    div(class: 'course') {
                                        h2 { a(href: course.url, course.title) }
                                        if (course.instructor) {
                                            div {
                                                yield "Instructor: ${course.instructor}"
                                            }
                                        }
                                        if (course.hours) {
                                            div {
                                                i(class: 'fa fa-clock-o') {}
                                                yiedl " Hours: ${course.hours}"
                                            }
                                        }
                                        if (course.dates) {
                                            div {
                                                i(class: 'fa fa-calendar') {}
                                                yiedl " ${course.dates}"
                                            }
                                        }
                                    }
                                }
                            }
                        }                    
                    }
                    if ( onsiteTrainingCatalogue ) {
                        article {
                            div(class: 'content') {
                                a(name: 'onsitetraining') {}

                                h2 {
                                    i(class: 'fa fa-graduation-cap') {}
                                    yield ' On-site Training'
                                }

                                onsiteTrainingCatalogue.each {
                                    Course course = it.value
                                    div(class: 'course') {
                                        h2 { a(href: course.url, course.title) }
                                        if (course.instructor) {
                                            div {
                                                yield "Instructor: ${course.instructor}"
                                            }
                                        }
                                        if (course.location) {
                                            div {
                                                i(class: 'fa fa-map-marker') {}
                                                yiedl " ${course.location}"
                                            }
                                        }
                                        if (course.hours) {
                                            div {
                                                i(class: 'fa fa-clock-o') {}
                                                yiedl " Hours: ${course.hours}"
                                            }
                                        }
                                        if (course.dates) {
                                            div {
                                                i(class: 'fa fa-calendar') {}
                                                yiedl " ${course.dates}"
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }    
                }
                    
                section(class: 'row  last-event-row') {
                    p(class: 'text-center') {
                        yield "For more events see the "
                        a(href: addBaseToUri("events.html", baseUri)) { strong('Events') }
                        yield " page"
                    }
                }

            }
            include unescaped: 'html/they-use-groovy.html'
        }
