package org.grails.airtable

import groovy.transform.CompileStatic
import groovy.transform.Memoized

import java.time.LocalDate

@CompileStatic
class MicronautAirtable {

    public static final String FIELD_NAME = 'Name'
    public static final String FIELD_LINK = 'link'
    public static final String FIELD_NAME_LOWERCASE = 'name'
    public static final String FIELD_DATE = 'start/publish date'
    public static final String FIELD_SPEAKERS = 'speaker(s)'
    public static final String FIELD_PRACTICE_AREA = 'practice area'
    public static final String TABLE_PRACTICE_AREAS = 'Practice Areas'
    public static final String TABLE_FOCUS = 'Focus'
    public static final String TABLE_SMES = 'SMEs'

    AirtableBaseApi api

    MicronautAirtable(AirtableBaseApi api) {
        this.api = api
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
                null).records
        records.findAll { Record record ->
            (record.fields[FIELD_PRACTICE_AREA] && (record.fields[FIELD_PRACTICE_AREA] as List<String>).contains(practiceId))
        }.collect { Record record ->
            String d = record.fields[FIELD_DATE]
            Event e = new Event()
            e.link = record.fields[FIELD_LINK]
            e.name = record.fields[FIELD_NAME_LOWERCASE]
            e.date = LocalDate.of(d.substring(0, 4) as Integer , d.substring(5, 7) as Integer, d.substring(8, 10) as Integer)
            e.speakers = record.fields[FIELD_SPEAKERS].collect { speakerName(it as String) }
            e
        }
    }

    @Memoized
    String speakerName(String speakerId) {
        List<Record> records = api.list(TABLE_SMES,
                [FIELD_NAME],
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null).records
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
                null).records
        practiceAreaRecords.find { Record record ->
            (record.fields[FIELD_NAME] as String).equalsIgnoreCase(practiceName)
        }?.id
    }
}
