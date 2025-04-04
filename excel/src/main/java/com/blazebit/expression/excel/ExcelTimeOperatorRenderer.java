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

package com.blazebit.expression.excel;

import com.blazebit.domain.runtime.model.DomainOperator;
import com.blazebit.domain.runtime.model.TemporalInterval;
import com.blazebit.expression.ChainingArithmeticExpression;
import com.blazebit.expression.DomainModelException;
import com.blazebit.expression.Expression;
import com.blazebit.expression.Literal;

import java.io.Serializable;

/**
 * @author Christian Beikov
 * @since 1.0.0
 */
public class ExcelTimeOperatorRenderer implements ExcelDomainOperatorRenderer, Serializable {

    public static final ExcelTimeOperatorRenderer INSTANCE = new ExcelTimeOperatorRenderer();

    private ExcelTimeOperatorRenderer() {
    }

    @Override
    public boolean render(ChainingArithmeticExpression e, ExcelExpressionSerializer serializer) {
        DomainOperator domainOperator = e.getOperator().getDomainOperator();
        if (domainOperator == DomainOperator.PLUS || domainOperator == DomainOperator.MINUS) {
            Expression expression = null;
            TemporalInterval interval = null;
            StringBuilder sb = serializer.getStringBuilder();
            if (e.getLeft() instanceof Literal) {
                if (domainOperator == DomainOperator.PLUS) {
                    expression = e.getRight();
                    interval = (TemporalInterval) ((Literal) e.getLeft()).getValue();
                }
            } else if (e.getRight() instanceof Literal) {
                expression = e.getLeft();
                interval = (TemporalInterval) ((Literal) e.getRight()).getValue();
            }

            if (interval != null) {
                boolean isConstant = expression.accept(serializer);
                String argumentSeparator = serializer.getArgumentSeparator();
                if (domainOperator == DomainOperator.PLUS) {
                    sb.append(" + ");
                } else {
                    sb.append(" - ");
                }
                sb.append("TIME(");
                sb.append(interval.getHours());
                sb.append(argumentSeparator).append(' ');
                sb.append(interval.getMinutes());
                sb.append(argumentSeparator).append(' ');
                sb.append(interval.getSeconds());
                sb.append(")");
                return isConstant;
            }
        }
        throw new DomainModelException("Can't handle the operator " + domainOperator + " for the arguments [" + e.getLeft() + ", " + e.getRight() + "]!");
    }
}
