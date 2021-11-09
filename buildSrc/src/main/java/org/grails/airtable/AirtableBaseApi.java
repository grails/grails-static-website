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
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.HttpHeaderValues;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Sergio del Amo
 * @since 1.0.0
 */
public class AirtableBaseApi {

    private final AirtableClient airtableClient;
    private final AirtableConfiguration airtableConfiguration;
    private final AirtableBaseConfiguration airtableBaseConfiguration;

    public AirtableBaseApi(AirtableClient airtableClient,
                           AirtableConfiguration airtableConfiguration,
                           AirtableBaseConfiguration airtableBaseConfiguration) {
        this.airtableClient = airtableClient;
        this.airtableConfiguration = airtableConfiguration;
        this.airtableBaseConfiguration = airtableBaseConfiguration;
    }

    public RecordList list(@NonNull @NotBlank @PathVariable String table,
                                   @Nullable @QueryValue("fields[]") List<String> fields,
                                   @Nullable @QueryValue String filterByFormula,
                                   @Nullable @QueryValue Integer maxRecords,
                                   @Nullable @QueryValue Integer pageSize,
                                   @Nullable @QueryValue List<Sort> sort,
                                   @Nullable @QueryValue String view,
                                   @Nullable @QueryValue CellFormat cellFormat,
                                   @Nullable @QueryValue TimeZone timeZone,
                                   @Nullable @QueryValue UserLocale userLocale) {
        return airtableClient.list(HttpHeaderValues.AUTHORIZATION_PREFIX_BEARER + StringUtils.SPACE + airtableConfiguration.getApiKey(),
                airtableConfiguration.getApiVersion(),
                airtableBaseConfiguration.getId(),
                table,
                fields,
                filterByFormula,
                maxRecords,
                pageSize,
                sort,
                view,
                cellFormat,
                timeZone,
                userLocale);
    }

    public RecordList list(@NonNull @NotBlank @PathVariable String table) {
        return list(table,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
