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

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.context.annotation.EachProperty;
import io.micronaut.context.annotation.Parameter;

import javax.validation.constraints.NotBlank;

/**
 * @author Sergio del Amo
 * @since 1.0.0
 */
@EachProperty(AirtableConfigurationProperties.PREFIX + ".bases")
public class AirtableBaseConfigurationProperties implements AirtableBaseConfiguration {

    private final String name;

    public AirtableBaseConfigurationProperties(@Parameter String name) {
        this.name = name;
    }

    @NonNull
    @NotBlank
    private String id;

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    /**
     *
     * @param id Sets Airtable base id
     */
    public void setId(@NonNull String id) {
        this.id = id;
    }
}
