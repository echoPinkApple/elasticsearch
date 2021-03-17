/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */

package org.elasticsearch.index.mapper;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.mapper.NumberFieldTypeTests.OutOfRangeSpec;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FloatFieldMapperTests extends NumberFieldMapperTests {

    @Override
    protected Number missingValue() {
        return 123f;
    }

    @Override
    protected List<OutOfRangeSpec> outOfRangeSpecs() {
        return Arrays.asList(
            OutOfRangeSpec.of(NumberFieldMapper.NumberType.FLOAT, "3.4028235E39", "[float] supports only finite values"),
            OutOfRangeSpec.of(NumberFieldMapper.NumberType.FLOAT, "-3.4028235E39", "[float] supports only finite values"),
            OutOfRangeSpec.of(NumberFieldMapper.NumberType.FLOAT, Float.NaN, "[float] supports only finite values"),
            OutOfRangeSpec.of(NumberFieldMapper.NumberType.FLOAT, Float.POSITIVE_INFINITY, "[float] supports only finite values"),
            OutOfRangeSpec.of(NumberFieldMapper.NumberType.FLOAT, Float.NEGATIVE_INFINITY, "[float] supports only finite values")
        );
    }

    @Override
    protected void minimalMapping(XContentBuilder b) throws IOException {
        b.field("type", "float");
    }
}
