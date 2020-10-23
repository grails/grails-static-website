package org.grails.events

import groovy.transform.CompileStatic
import groovy.transform.Memoized
import org.grails.airtable.AirtableBaseApi
import org.grails.airtable.Record

import java.time.LocalDate

@CompileStatic
class GrailsAirtable {

    public static final String FIELD_NAME = 'Name'
    public static final String FIELD_LINK = 'link'
    public static final String FIELD_LOCATION = 'location'
    public static final String FIELD_LINK_OCI = 'our webpage'
    public static final String FIELD_EVENT_NAME = 'asset or event'
    public static final String FIELD_DATE = 'start/publish date'
    public static final String FIELD_OCI_SPEAKERS = 'OCI speaker(s)'
    public static final String FIELD_GUEST_SPEAKERS = 'Guest Speakers'
    public static final String FIELD_PRACTICE_AREA = 'practice area'
    public static final String TABLE_PRACTICE_AREAS = 'Practice Areas'
    public static final String TABLE_FOCUS = 'Focus'
    public static final String TABLE_OCI_TEAM = 'OCI Team'
    public static final String TABLE_GUEST_SPEAKERS = 'Guest Speakers'
    public static final String FIELD_STATUS = 'status'
    public static final String STATUS_SCHEDULED = 'scheduled'
    public static final String FIELD_EVENTS = 'events'

    AirtableBaseApi api

    GrailsAirtable(AirtableBaseApi api) {
        this.api = api
    }

    List<Event> fetchGrailsByPracticeName(String practiceName) {
        String areaId = practiceId(practiceName)
        fetchEvents(areaId)
    }

    @Memoized
    List<Event> fetchEvents(String practiceId) {
        List<Record> records = api.list(TABLE_FOCUS,
                null,
                null,
                null,
                null,
                null,
                "All Upcoming Events",
                null,
                null,
                null).blockingGet().records
        records.findAll { Record record ->
            (record.fields[FIELD_PRACTICE_AREA] && (record.fields[FIELD_PRACTICE_AREA] as List<String>).contains(practiceId)) &&
                    (record.fields[FIELD_STATUS] == STATUS_SCHEDULED) &&
                    (record.fields[FIELD_EVENTS] as boolean)
        }.collect { Record record ->
            String d = record.fields[FIELD_DATE]
            Event e = new Event()
            e.link = record.fields[FIELD_LINK] ?: record.fields[FIELD_LINK_OCI]
            e.location = record.fields[FIELD_LOCATION]
            e.name = record.fields[FIELD_EVENT_NAME]
            e.date = LocalDate.of(d.substring(0, 4) as Integer , d.substring(5, 7) as Integer, d.substring(8, 10) as Integer)
            List<String> ociSpeakers = record.fields[FIELD_OCI_SPEAKERS].collect { ociSpeakerName(it as String) }
            List<String> guestSpeakers = record.fields[FIELD_GUEST_SPEAKERS].collect { guestSpeakerName(it as String) }
            e.speakers = ociSpeakers + guestSpeakers
            e
        }
    }

    String ociSpeakerName(String speakerId) {
        speakerName(TABLE_OCI_TEAM, speakerId)
    }

    String guestSpeakerName(String speakerId) {
        speakerName(TABLE_GUEST_SPEAKERS, speakerId)
    }

    @Memoized
    String speakerName(String speakerTable, String speakerId) {
        List<Record> records = api.list(speakerTable,
                [FIELD_NAME],
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null).blockingGet().records
        records.find { Record record ->
            record.id == speakerId
        }?.fields[FIELD_NAME] as String
    }

    @Memoized
    String practiceId(String practiceName) {
        List<Record> practiceAreaRecords = api.list(TABLE_PRACTICE_AREAS,
                [FIELD_NAME],
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null).blockingGet().records
        practiceAreaRecords.find { Record record ->
            (record.fields[FIELD_NAME] as String).equalsIgnoreCase(practiceName)
        }?.id
    }
}
