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

public final class IndexMatchingOutput implements Output {
    private final Integer index;
    private final Value expected;
    private final Boolean matched;
    private final Integer current;

    private IndexMatchingOutput(
        final Integer idx,
        final Value value,
        final Boolean status,
        final Integer position
    ) {
        this.index = idx;
        this.expected = value;
        this.matched = status;
        this.current = position;
    }

    public IndexMatchingOutput(final Integer idx, final Value value) {
        this(idx, value, false, 1);
    }

    public IndexMatchingOutput(
        final Integer idx, final String lbl, final String value
    ) {
        this(idx, new LabelledValue(lbl, value));
    }

    public IndexMatchingOutput(
        final Integer idx, final String lbl, final Integer value
    ) {
        this(idx, new LabelledValue(lbl, value));
    }

    public IndexMatchingOutput(
        final Integer idx, final String lbl, final Boolean value
    ) {
        this(idx, new LabelledValue(lbl, value));
    }

    public IndexMatchingOutput(
        final Integer idx, final String lbl, final Long value
    ) {
        this(idx, new LabelledValue(lbl, value));
    }

    public IndexMatchingOutput(
        final Integer idx, final String lbl, final Double value
    ) {
        this(idx, new LabelledValue(lbl, value));
    }

    public IndexMatchingOutput(
        final Integer idx, final String lbl, final Fields value
    ) {
        this(idx, new LabelledValue(lbl, value));
    }

    public IndexMatchingOutput(
        final Integer idx, final String lbl, final Values values
    ) {
        this(idx, new LabelledValue(lbl, values));
    }

    @Override
    public final String show() {
        return this.matched.toString();
    }

    @Override
    public final Output print(
        final String key, final String value
    ) throws Exception {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public final Output print(
        final String key, final Integer value
    ) throws Exception {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public final Output print(
        final String key, final Boolean value
    ) throws Exception {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public final Output print(
        final String key, final Double value
    ) throws Exception {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public final Output print(
        final String key, final Long value
    ) throws Exception {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public final Output print(
        final String key, final Fields value
    ) throws Exception {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public final Output print(
        final String key, final Values values
    ) throws Exception {
        return this.match(new LabelledValue(key, values));
    }

    private Output match(final Value value) throws Exception {
        return new IndexMatchingOutput(
            this.index,
            this.expected,
            this.current.equals(this.index) ? (
                new MatchingValueTest(this.expected, value).passes()
            ) : this.matched,
            this.current + 1
        );
    }
}
