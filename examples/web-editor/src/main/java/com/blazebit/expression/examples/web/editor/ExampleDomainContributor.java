/*
 * Copyright 2019 - 2020 Blazebit.
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

package com.blazebit.expression.examples.web.editor;

import com.blazebit.domain.boot.model.DomainBuilder;
import com.blazebit.domain.boot.model.EnumDomainTypeBuilder;
import com.blazebit.domain.spi.DomainContributor;
import com.blazebit.expression.examples.web.editor.entity.Gender;
import com.blazebit.expression.persistence.TypeUtils;

/**
 * @author Christian Beikov
 * @since 1.0.0
 */
public class ExampleDomainContributor implements DomainContributor {

    @Override
    public void contribute(DomainBuilder domainBuilder) {
        EnumDomainTypeBuilder genderEnumBuilder = domainBuilder.createEnumType("Gender", Gender.class);
        TypeUtils.registerStringlyEnumType(domainBuilder, genderEnumBuilder);
        for (Gender gender : Gender.values()) {
            genderEnumBuilder.withValue(gender.name());
        }
        genderEnumBuilder.build();
    }
}