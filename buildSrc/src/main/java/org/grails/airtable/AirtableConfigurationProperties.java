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
import io.micronaut.context.annotation.ConfigurationProperties;
import javax.validation.constraints.NotBlank;

/**
 * {@link ConfigurationProperties} implementation of {@link AirtableConfiguration}.
 * @author Sergio del Amo
 * @since 1.0.0
 */
@ConfigurationProperties(AirtableConfigurationProperties.PREFIX)
public class AirtableConfigurationProperties implements AirtableConfiguration {

    public static final String PREFIX = "airtable";

    public static final String V0 = "v0";

    public static final String DEFAULT_URL = "https://api.airtable.com/";

    @NonNull
    @NotBlank
    private String url = DEFAULT_URL;

    @NonNull
    @NotBlank
    private String apiKey;

    @NonNull
    @NotBlank
    private String apiVersion = V0;

    @Override
    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    @Override
    @NonNull
    public String getApiKey() {
        return apiKey;
    }

    /**
     *
     * @param apiKey Sets Airtable API key
     */
    public void setApiKey(@NonNull String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    @NonNull
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     *
     * @param apiVersion Airtable API Version {@value AirtableConfigurationProperties#V0}.
     */
    public void setApiVersion(@NonNull String apiVersion) {
        this.apiVersion = apiVersion;
    }
}
