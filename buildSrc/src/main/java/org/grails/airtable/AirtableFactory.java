/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.airtable;

import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;

/**
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Factory
public class AirtableFactory {

    private final AirtableClient airtableClient;
    private final AirtableConfiguration airtableConfiguration;

    public AirtableFactory(AirtableClient airtableClient,
                           AirtableConfiguration airtableConfiguration) {
        this.airtableClient = airtableClient;
        this.airtableConfiguration = airtableConfiguration;
    }


    @EachBean(AirtableBaseConfiguration.class)
    public AirtableBaseApi buildEngine(AirtableBaseConfiguration baseConfiguration) {
        return new AirtableBaseApi(airtableClient, airtableConfiguration, baseConfiguration);
    }
}
