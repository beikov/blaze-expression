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

package com.blazebit.expression.base;

import com.blazebit.domain.Domain;
import com.blazebit.domain.boot.model.DomainBuilder;
import com.blazebit.domain.runtime.model.DomainFunction;
import com.blazebit.domain.runtime.model.DomainFunctionArgument;
import com.blazebit.domain.runtime.model.DomainModel;
import com.blazebit.domain.runtime.model.DomainType;
import com.blazebit.expression.DocumentationMetadataDefinition;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Christian Beikov
 * @since 1.0.0
 */
public class DomainBuilderTest {

    private DomainModel domainModel;
    private DomainType stringType;
    private DomainType integerType;
    private DomainType decimalType;

    @Before
    public void before() {
        if (domainModel == null) {
            DomainBuilder domainBuilder = Domain.getDefaultProvider().createDefaultBuilder();
            domainModel = domainBuilder.build();
            stringType = domainModel.getType(BaseContributor.STRING_TYPE_NAME);
            integerType = domainModel.getType(BaseContributor.INTEGER_TYPE_NAME);
            decimalType = domainModel.getType(BaseContributor.NUMERIC_TYPE_NAME);
        }
    }

    private DomainType resolveFunctionType(String functionName, DomainType... argumentTypes) {
        Map<DomainFunctionArgument, DomainType> argumentDomainTypeMap = new HashMap<>(argumentTypes.length);
        DomainFunction function = domainModel.getFunction(functionName);
        List<? extends DomainFunctionArgument> arguments = function.getArguments();
        for (int i = 0; i < argumentTypes.length; i++) {
            argumentDomainTypeMap.put(arguments.get(i), argumentTypes[i]);
        }
        return domainModel.getFunctionTypeResolver(functionName).resolveType(domainModel, function, argumentDomainTypeMap);
    }

    @Test
    public void testSubstring() {
        DomainFunction substring = domainModel.getFunction("substring");
        Assert.assertEquals("SUBSTRING", substring.getName());
        Assert.assertEquals(2, substring.getMinArgumentCount());
        Assert.assertEquals(3, substring.getArgumentCount());
        Assert.assertEquals(stringType, substring.getResultType());

        Assert.assertEquals(3, substring.getArguments().size());
        Assert.assertEquals("string", substring.getArguments().get(0).getName());
        Assert.assertEquals(stringType, substring.getArguments().get(0).getType());
        Assert.assertEquals("start", substring.getArguments().get(1).getName());
        Assert.assertEquals(integerType, substring.getArguments().get(1).getType());
        Assert.assertEquals("count", substring.getArguments().get(2).getName());
        Assert.assertEquals(integerType, substring.getArguments().get(2).getType());
        Assert.assertEquals(stringType, resolveFunctionType("substring", stringType, integerType, integerType));
    }

    @Test
    public void testAbs() {
        DomainFunction abs = domainModel.getFunction("abs");
        Assert.assertEquals("ABS", abs.getName());
        Assert.assertNotNull(abs.getMetadata(DocumentationMetadataDefinition.class).serialize(domainModel, null, String.class, "json", Collections.emptyMap()));
        Assert.assertEquals(1, abs.getMinArgumentCount());
        Assert.assertEquals(1, abs.getArgumentCount());
        Assert.assertNull(abs.getResultType());

        Assert.assertEquals(1, abs.getArguments().size());
        Assert.assertEquals("number", abs.getArguments().get(0).getName());
        Assert.assertNotNull(abs.getArguments().get(0).getMetadata(DocumentationMetadataDefinition.class).serialize(domainModel, null, String.class, "json", Collections.emptyMap()));
        Assert.assertEquals(integerType, resolveFunctionType("abs", integerType));
        Assert.assertEquals(decimalType, resolveFunctionType("abs", decimalType));
    }
}
