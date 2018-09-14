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
package uy.kerri.representations.test;

import uy.kerri.representations.Field;
import uy.kerri.representations.Fields;
import uy.kerri.representations.LabelledValue;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;

/**
 * An {@link uy.kerri.representations.Output} that shows if the last field
 *  printed on it matches a given field.
 *
 * @since 2.0
 */
final class FieldMatchingOutput implements Output {
    /**
     * The expected field.
     */
    private final Field expected;

    /**
     * Whether the last printed field matched or not.
     */
    private final Boolean matched;

    /**
     * Constructs an output that shows if the last field printed on it matches
     *  a given field.
     *
     * @param field The field to be matched.
     * @param status Whether the last printed field matched the field or not.
     */
    private FieldMatchingOutput(final Field field, final Boolean status) {
        this.expected = field;
        this.matched = status;
    }

    /**
     * Constructs an output that shows if the last field printed on it matches
     *  a given field.
     *
     * @param field The field to be matched.
     */
    FieldMatchingOutput(final Field field) {
        this(field, false);
    }

    @Override
    public String show() {
        return this.matched.toString();
    }

    @Override
    public Output print(
        final String key, final String value
    ) throws RepresentationsException {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public Output print(
        final String key, final Integer value
    ) throws RepresentationsException {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public Output print(
        final String key, final Boolean value
    ) throws RepresentationsException {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public Output print(
        final String key, final Double value
    ) throws RepresentationsException {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public Output print(
        final String key, final Long value
    ) throws RepresentationsException {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public Output print(
        final String key, final Fields value
    ) throws RepresentationsException {
        return this.match(new LabelledValue(key, value));
    }

    @Override
    public Output print(
        final String key, final Values values
    ) throws RepresentationsException {
        return this.match(new LabelledValue(key, values));
    }

    /**
     * Determines whether the currently printed field matches the expected field
     *  or not.
     *
     * @param actual The currently printed field.
     * @return An output indicating whether the field matched or not.
     * @throws RepresentationsException if something goes wrong.
     */
    private Output match(final Field actual) throws RepresentationsException {
        final Output selected;
        if (new MatchingFieldNameTest(this.expected, actual).passes()) {
            selected = new FieldMatchingOutput(
                this.expected,
                new MatchingFieldTest(this.expected, actual).passes()
            );
        } else {
            selected = this;
        }
        return selected;
    }
}
