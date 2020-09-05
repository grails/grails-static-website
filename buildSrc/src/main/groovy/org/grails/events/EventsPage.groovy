package org.grails.events

import groovy.transform.CompileDynamic

@CompileDynamic
class EventsPage {

    @CompileDynamic
    static String eventsTable(List<Event> events, boolean showLocation = false) {
        String msg = """\
<div class="training"><table>
<colgroup>
<col>
<col>
${showLocation ? '<col>': ''}
<col>
</colgroup>
<thead>
<tr><th>Event</th><th>Date(s)</th>${showLocation ? '<th>Location</th>': ''}<th>Speakers</th></tr>
</thead>
<tbody>
"""
        for (Event event : events) {
            msg += tableRowForEvent(event, showLocation)
        }
        msg += '</tbody>'
        msg += '</table></div>'
        msg
    }

    static String tableRowForEvent(Event event, boolean showLocation) {
        String msg = '<tr>'
        if (event.link) {
            msg += "<td><a href=\"${event.link}\">${event.name ?: ''}</a></td>"
        } else {
            msg += "<td>${event.name ?: ''}</td>"
        }
        msg += "<td>${event.date ?: ''}</td>"
        if (showLocation) {
            msg += "<td>${event.location ?: ''}</td>"
        }
        msg += "<td>${(event.speakers ? event.speakers.join(', ') : '')}</td>"
        msg += "</tr>"
        msg
    }

}
