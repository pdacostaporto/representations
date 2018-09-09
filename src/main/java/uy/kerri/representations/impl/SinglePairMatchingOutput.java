/*
 * MIT License
 *
 * Copyright (c) 2018 Pablo Da Costa Porto
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package uy.kerri.representations.impl;

import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.Value;
import uy.kerri.representations.Values;
import uy.kerri.representations.exception.FieldNotMatchedException;

/**
 * An {@link uy.kerri.representations.Output} that shows if a field matches
 *  certain value.
 *
 * @since 1.3
 */
public class SinglePairMatchingOutput implements Output {
    private final String label;
    private final Object expected;
    private final Boolean matched;

    private SinglePairMatchingOutput(
        final String key, final Object value, final Boolean status
    ) {
        this.label = key;
        this.expected = value;
        this.matched = status;
    }

    public SinglePairMatchingOutput(final String name, final Object val) {
        this(name, val, false);
    }

    @Override
    public final String show() {
        return this.matched.toString();
    }

    @Override
    public final Output print(
        final String key, final String value
    ) throws Exception {
        return this.match(key, value);
    }

    @Override
    public final Output print(
        final String key, final Integer value
    ) throws Exception {
        return this.match(key, value);
    }

    @Override
    public final Output print(
        final String key, final Boolean value
    ) throws Exception {
        return this.match(key, value);
    }

    @Override
    public final Output print(
        final String key, final Double value
    ) throws Exception {
        return this.match(key, value);
    }

    @Override
    public final Output print(
        final String key, final Long value
    ) throws Exception {
        return this.match(key, value);
    }

    @Override
    public final Output print(
        final String key, final Fields value
    ) throws Exception {
        return this.match(key, value);
    }

    @Override
    public final Output print(
        final String key, final Values values
    ) throws Exception {
        return this.match(key, values);
    }

    private Output match(final String key, final Object value)
    throws Exception {
        return new SinglePairMatchingOutput(
            this.label,
            this.expected,
            key.equals(this.label) && value.equals(this.expected)
        );
    }
}
