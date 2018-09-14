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

import java.util.HashMap;
import java.util.Map;
import uy.kerri.representations.Fields;
import uy.kerri.representations.LabelledValue;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;

/**
 * An {@link uy.kerri.representations.Output} that shows if the fields printed
 *  on it match to correspondent fields on a given group of fields.
 *
 * @since 2.0
 */
final class FieldsMatchingOutput implements Output {
    /**
     * The group of fields to match the printed fields to.
     */
    private final Fields actual;

    /**
     * Already printed fields and whether they matched or not.
     */
    private final Map<String, Boolean> matched;

    /**
     * Constructs and output that shows if fields printed on it match on a given
     *  group of fields, considering that some fields have already been printed.
     *
     * @param fields The group of fields to match the printed fields to.
     * @param matches A map of already printed fields to whether they matched or
     *  not.
     */
    FieldsMatchingOutput(
        final Fields fields, final Map<String, Boolean> matches
    ) {
        this.actual = fields;
        this.matched = matches;
    }

    /**
     * Constructs and output that shows if fields printed on it match on a given
     *  group of fields.
     *
     * @param fields The group of fields to match the printed fields to.
     *  not.
     */
    FieldsMatchingOutput(final Fields fields) {
        this(fields, new HashMap<String, Boolean>());
    }

    @Override
    public String show() {
        return String.format("%b", !this.matched.containsValue(false));
    }

    @Override
    public Output print(
        final String key, final String value
    ) throws RepresentationsException {
        return this.match(
            key,
            new ContainedFieldTest(new LabelledValue(key, value), this.actual)
            .passes()
        );
    }

    @Override
    public Output print(
        final String key, final Integer value
    ) throws RepresentationsException {
        return this.match(
            key,
            new ContainedFieldTest(new LabelledValue(key, value), this.actual)
            .passes()
        );
    }

    @Override
    public Output print(
        final String key, final Boolean value
    ) throws RepresentationsException {
        return this.match(
            key,
            new ContainedFieldTest(new LabelledValue(key, value), this.actual)
            .passes()
        );
    }

    @Override
    public Output print(
        final String key, final Double value
    ) throws RepresentationsException {
        return this.match(
            key,
            new ContainedFieldTest(new LabelledValue(key, value), this.actual)
            .passes()
        );
    }

    @Override
    public Output print(
        final String key, final Long value
    ) throws RepresentationsException {
        return this.match(
            key,
            new ContainedFieldTest(new LabelledValue(key, value), this.actual)
            .passes()
        );
    }

    @Override
    public Output print(
        final String key, final Fields value
    ) throws RepresentationsException {
        return this.match(
            key,
            new ContainedFieldTest(new LabelledValue(key, value), this.actual)
            .passes()
        );
    }

    @Override
    public Output print(
        final String key, final Values values
    ) throws RepresentationsException {
        return this.match(
            key,
            new ContainedFieldTest(new LabelledValue(key, values), this.actual)
            .passes()
        );
    }

    /**
     * Matches a printed field.
     *
     * @param key The name of the printed field.
     * @param current Whether the current field matched or not.
     * @return An output updated to indicate if the field matched or not.
     * @throws RepresentationsException if something goes wrong.
     */
    private Output match(
        final String key, final Boolean current
    ) throws RepresentationsException {
        final Map<String, Boolean> matches = new HashMap<>(this.matched);
        matches.put(key, current);
        return new FieldsMatchingOutput(this.actual, matches);
    }
}
