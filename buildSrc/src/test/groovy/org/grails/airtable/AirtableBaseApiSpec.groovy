package org.grails.airtable

import io.micronaut.context.ApplicationContext
import io.micronaut.context.annotation.Requires
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.inject.qualifiers.Qualifiers
import io.micronaut.runtime.server.EmbeddedServer
import io.reactivex.Single
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Ignore

class AirtableBaseApiSpec extends ApplicationContextSpecification {

    @Override
    Map<String, Object> getConfiguration() {
        println "http://localhost:$mockAirtablePort"
        super.configuration + [
                'airtable.url': "http://localhost:$mockAirtablePort".toString(),
                'airtable.api-key': 'secret',
                'airtable.bases.2020.id': 'appxxxyyy',
        ]
    }

    @AutoCleanup
    @Shared
    EmbeddedServer mockAirtable = ApplicationContext.run(EmbeddedServer, ['spec.name': 'Airtable',
                                                                          'micronaut.server.port': mockAirtablePort])

    @Ignore
    void "bean of type AirtableBaseApi named 2020 exists"() {
        when:
        AirtableBaseApi api = applicationContext.getBean(AirtableBaseApi, Qualifiers.byName("2020"))

        then:
        noExceptionThrown()

        when:
        MicronautAirtable micronautAirtable = new MicronautAirtable(api)
        List<Event> events = micronautAirtable.fetchMicronautEvents()

        then:
        events
        events.each { Event event ->
            assert event.name
            assert event.date
            println event.toString()
        }
    }

    @Requires(property = 'spec.name', value = 'Airtable')
    @Controller
    static class AirtableController {

        @Get("/v0/appxxxyyy/{table}")
        Single<RecordList> list(@PathVariable String table) {
            if (table == MicronautAirtable.TABLE_PRACTICE_AREAS) {
                RecordList recordList = new RecordList()
                recordList.records = new ArrayList<>()
                Record record = new Record()
                record.id = "recaaa"
                record.fields = ["Name": "MICRONAUT"]
                record.createdTime = "2018-05-08T16:21:17.000Z"
                recordList.records << record
                return Single.just(recordList)
            } else if (table == MicronautAirtable.TABLE_FOCUS) {
                RecordList recordList = new RecordList()
                recordList.records = new ArrayList<>()
                Record record = new Record()
                record.id = "recbbb"
                record.fields = ["name": "Introduction to Micronaut 2.0", "category": "meetup", "events": true, "status": "scheduled (confirmed)", "link": "https://www.meetup.com/Stuttgart-Cloud-Foundry-Meetup/events/271983604/", "practice area": ["recaaa"], "start/publish date": "2020-07-30", "time": "5:45 to 8:45 pm GMT+2", "speaker(s)": ["recccc"], "location": "online", "Meetup": "Stuttgart Cloud Foundry Meetup", "month": "07 July", "quarter": "3"]
                record.createdTime = "2020-07-17T15:57:31.000Z"
                recordList.records << record
                return Single.just(recordList)
            } else if (table == MicronautAirtable.TABLE_SMES) {
                RecordList recordList = new RecordList()
                recordList.records = new ArrayList<>()
                Record record = new Record()
                record.id = "recccc"
                record.fields = ["Name":"Sergio del Amo Caballero"]
                record.createdTime = "2020-07-17T15:57:31.000Z"
                recordList.records << record
                return Single.just(recordList)
            }
            return Single.just(new RecordList())
        }
    }
}
