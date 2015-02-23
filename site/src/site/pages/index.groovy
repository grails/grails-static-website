import model.Event

layout 'layouts/main.groovy', true,
        pageTitle: 'The Grails Framework',
        mainContent: contents {
            div(id: 'band', class: 'band') {

            }
            div(id: 'content') {
                include unescaped: 'html/index.html'

                int eventsPerRow = 3
                def allEventNames = allEvents.keySet() as List
                int rows=allEventNames.size()/eventsPerRow + (allEventNames.size()%eventsPerRow != 0 ? 1 : 0)
                for(int rownum = 1; rownum <= rows; rownum++) {
                  int offset = (rownum-1) * eventsPerRow
                  def cssClasses = "row colset-3-article"
                  if(rownum==1) {
                    cssClasses += " first-event-row"
                  }
                  if(rownum==rows) {
                    cssClasses += " last-event-row"
                  }
                  section(class: cssClasses) {
                      if(rownum == 1) {
                        h1 { strong "Groovy and Grails events you shouldn't miss!" }
                      }
                      allEventNames[offset..(Math.min(offset + (eventsPerRow-1), allEventNames.size()-1))].each { String eventName ->
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
                  }
                }
            }
            include unescaped: 'html/they-use-groovy.html'
        }
