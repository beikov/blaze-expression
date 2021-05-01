/*
 * Copyright 2019 - 2021 Blazebit.
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

package com.blazebit.expression.persistence;

import com.blazebit.domain.runtime.model.DomainFunction;
import com.blazebit.domain.runtime.model.DomainType;

import java.io.Serializable;

/**
 * @author Christian Beikov
 * @since 1.0.0
 */
class PersistenceStringlyTypeConstructorFunctionRenderer implements PersistenceFunctionRenderer, Serializable {

    private final PersistenceStringlyTypeHandler stringlyTypeHandler;

    /**
     * Create a new constructor function renderer with the given stringly type handler.
     *
     * @param stringlyTypeHandler The stringly type handler
     */
    public PersistenceStringlyTypeConstructorFunctionRenderer(PersistenceStringlyTypeHandler stringlyTypeHandler) {
        this.stringlyTypeHandler = stringlyTypeHandler;
    }

    @Override
    public void render(DomainFunction function, DomainType returnType, PersistenceDomainFunctionArgumentRenderers argumentRenderers, StringBuilder sb, PersistenceExpressionSerializer serializer) {
        stringlyTypeHandler.appendPersistenceConstructTo(sb, subBuilder -> argumentRenderers.renderArgument(subBuilder, 0));
    }
}
