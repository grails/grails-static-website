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
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Micronaut declarative HTTP Client to consume Airtable API.
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Client(value = "${airtable.url:`" + AirtableConfigurationProperties.DEFAULT_URL + "`}", errorType = AirtableApiError.class)
public interface AirtableClient extends AirtableApi {

    @Override
    @Get("/{version}/{baseId}/{table}")
    RecordList list(@Header String authorization,
                            @NonNull @NotBlank @PathVariable String version,
                            @NonNull @NotBlank @PathVariable String baseId,
                            @NonNull @NotBlank @PathVariable String table,
                            @Nullable @QueryValue("fields[]") List<String> fields,
                            @Nullable @QueryValue String filterByFormula,
                            @Nullable @QueryValue Integer maxRecords,
                            @Nullable @QueryValue Integer pageSize,
                            @Nullable @QueryValue List<Sort> sort,
                            @Nullable @QueryValue String view,
                            @Nullable @QueryValue CellFormat cellFormat,
                            @Nullable @QueryValue TimeZone timeZone,
                            @Nullable @QueryValue UserLocale userLocale);
}
