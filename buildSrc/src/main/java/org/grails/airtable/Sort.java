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

import io.micronaut.core.annotation.Introspected;

/**
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Introspected
public class Sort {

    public static final Direction DEFAULT_DIRECTION = Direction.ASC;

    /**
     *  key specifying the name of the field to sort on.
     */
    private String field;
    private Direction direction = DEFAULT_DIRECTION;

    /**
     * Constructor.
     */
    public Sort() {
    }

    /**
     * Constructor.
     * @param field key specifying the name of the field to sort on.
     */
    public Sort(String field) {
        this.field = field;
    }

    /**
     * Constructor.
     * @param field key specifying the name of the field to sort on.
     * @param direction "asc" or "desc" direction key.
     */
    public Sort(String field, Direction direction) {
        this.field = field;
        this.direction = direction;
    }

    /**
     *
     * @return key specifying the name of the field to sort on.
     */
    public String getField() {
        return field;
    }

    /**
     *
     * @param field  key specifying the name of the field to sort on.
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     *
     * @return "asc" or "desc" direction key.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     *
     * @param direction "asc" or "desc" direction key.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
