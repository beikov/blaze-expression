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

package com.blazebit.expression.declarative.impl;

import java.io.Serializable;
import java.util.UUID;

import com.blazebit.domain.runtime.model.DomainType;
import com.blazebit.expression.ExpressionInterpreter;
import com.blazebit.expression.spi.TypeAdapter;

/**
 * @author Christian Beikov
 * @since 1.0.0
 */
public class UUIDTypeAdapter implements TypeAdapter<UUID, String>, Serializable {

    public static final UUIDTypeAdapter INSTANCE = new UUIDTypeAdapter();

    private UUIDTypeAdapter() {
    }

    @Override
    public String toInternalType(ExpressionInterpreter.Context context, UUID value, DomainType domainType) {
        return value.toString();
    }

    @Override
    public UUID toModelType(ExpressionInterpreter.Context context, String value, DomainType domainType) {
        return UUID.fromString(value);
    }
}
