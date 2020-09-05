package org.grails.airtable

import io.micronaut.context.ApplicationContext
import io.micronaut.core.io.socket.SocketUtils
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

abstract class ApplicationContextSpecification extends Specification implements ConfigurationFixture {

    @Shared
    int mockAirtablePort = SocketUtils.findAvailableTcpPort()

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run(configuration)
}
