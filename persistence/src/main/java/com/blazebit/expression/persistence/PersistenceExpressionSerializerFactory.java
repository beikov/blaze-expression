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

package com.blazebit.expression.persistence;

import com.blazebit.apt.service.ServiceProvider;
import com.blazebit.expression.ExpressionSerializer;
import com.blazebit.expression.ExpressionService;
import com.blazebit.expression.spi.ExpressionSerializerFactory;
import com.blazebit.persistence.WhereBuilder;

/**
 * @author Christian Beikov
 * @since 1.0.0
 */
@ServiceProvider(ExpressionSerializerFactory.class)
public class PersistenceExpressionSerializerFactory implements ExpressionSerializerFactory<WhereBuilder<?>> {

    @Override
    public Class<WhereBuilder<?>> getSerializationTargetType() {
        return (Class<WhereBuilder<?>>) (Class) WhereBuilder.class;
    }

    @Override
    public ExpressionSerializer<WhereBuilder<?>> createSerializer(ExpressionService expressionService) {
        return new PersistenceExpressionSerializer(expressionService);
    }
}
