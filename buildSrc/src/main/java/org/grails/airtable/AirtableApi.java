/*
 * Copyright 2017-2022 original authors
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
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Sergio del Amo
 * @since 1.0.0
 */
public interface AirtableApi {

    /**
     * @see <a href="https://support.airtable.com/hc/en-us/articles/203255215-Formula-Field-Reference">Formulas</a>.
     *
     * @param authorization Airtable API Key e.g. Bearer XXXX
     * @param baseId Airtable base Id
     * @param table Airtable Table
     * @param fields Only data for fields whose names are in this list will be included in the result. If you don't need every field, you can use this parameter to reduce the amount of data transferred.
     * @param filterByFormula A formula used to filter records. The formula will be evaluated for each record, and if the result is not 0, false, "", NaN, [], or #Error! the record will be included in the response. If combined with the view parameter, only records in that view which satisfy the formula will be returned.
     * @param maxRecords The maximum total number of records that will be returned in your requests. If this value is larger than pageSize (which is 100 by default), you may have to load multiple pages to reach this total.
     * @param pageSize The number of records returned in each request. Must be less than or equal to 100. Default is 100.
     * @param sort A list of sort objects that specifies how to record will be ordered.
     * @param view The name or ID of a view in the Focus table. If set, only the records in that view will be returned. The records will be sorted according to the order of the view unless the sort parameter is included, which overrides that order. Fields hidden in this view will be returned in the results. To only return a subset of fields, use the fields parameter.
     * @param cellFormat The format that should be used for cell values. Default value json
     * @param timeZone The time zone that should used to format dates when using string as cellFormat. This parameter is required when using string as the cell format
     * @param userLocale The user locale that should be used to format dates when using String as cellFormat.
     * @return A list records in a table
     */
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
