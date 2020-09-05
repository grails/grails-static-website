package org.grails.airtable

class AirtableConfigurationSpec extends ApplicationContextSpecification {
    @Override
    Map<String, Object> getConfiguration() {
        super.configuration + [
                'airtable.api-key': 'secret',
                'airtable.bases.2020.id': 'appxxxyyy',
        ]
    }

    void "bean of type AirtableConfiguration exists"() {
        expect:
        applicationContext.containsBean(AirtableConfiguration)

        and:
        applicationContext.getBean(AirtableConfiguration).getApiVersion() == 'v0'

        and:
        applicationContext.getBean(AirtableConfiguration).getApiKey()
    }
}
