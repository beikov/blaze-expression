/*
 * Copyright 2019 - 2025 Blazebit.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blazebit.expression.base.converter;

import com.blazebit.domain.runtime.model.DomainType;
import com.blazebit.expression.ExpressionInterpreter;
import com.blazebit.expression.spi.TypeConverter;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * @author Christian Beikov
 * @since 1.0.0
 */
public class InstantToZonedDateTimeTypeConverter implements TypeConverter<Instant, ZonedDateTime>, Serializable {
    @Override
    public ZonedDateTime convert(ExpressionInterpreter.Context context, Instant value, DomainType domainType) {
        return ZonedDateTime.ofInstant(value, ZoneOffset.UTC);
    }
}
