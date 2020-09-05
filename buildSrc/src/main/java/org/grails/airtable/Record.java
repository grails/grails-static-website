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
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Airtable records
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Introspected
public class Record {

    @NonNull
    @NotBlank
    private String id;

    @NonNull
    @NotNull
    private Map<String, Object> fields;

    @NonNull
    @NotBlank
    private String createdTime;

    public Record() {
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public Map<String, Object> getFields() {
        return fields;
    }

    public void setFields(@NonNull Map<String, Object> fields) {
        this.fields = fields;
    }

    @NonNull
    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(@NonNull String createdTime) {
        this.createdTime = createdTime;
    }
}
