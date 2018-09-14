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
package uy.kerri.representations.select;

import java.util.List;
import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;

/**
 * An {@link uy.kerri.representations.Output} that only allows certain fields
 *  to be printed on a nested output.
 *
 * @since 2.0
 */
final class FilteringOutput implements Output {
    /**
     * The fields to be allowed.
     */
    private final List<String> fields;

    /**
     * The nested output.
     */
    private final Output nested;

    /**
     * Constructs an output that only allows given fields to be printed on a
     *  nested output.
     *
     * @param names The names of the fields allowed.
     * @param output The output where the fields will be printed.
     */
    FilteringOutput(final List<String> names, final Output output) {
        this.fields = names;
        this.nested = output;
    }

    @Override
    public String show() throws RepresentationsException {
        return this.nested.show();
    }

    @Override
    public Output print(
        final String key, final String value
    ) throws RepresentationsException {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key, final Integer value
    ) throws RepresentationsException {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key, final Boolean value
    ) throws RepresentationsException {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key, final Double value
    ) throws RepresentationsException {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key, final Long value
    ) throws RepresentationsException {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key,
        final Fields value
    ) throws RepresentationsException {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public Output print(
        final String key, final Values values
    ) throws RepresentationsException {
        return this.select(key, this.nested.print(key, values));
    }

    /**
     * Chooses between the output with a printed field or not considering if the
     *  field is allowed to be printed.
     *
     * @param key The name of the printed field.
     * @param printed This output with the field already printed.
     * @return This output with the field already printed or not considering if
     *  the field is allowed.
     * @throws RepresentationsException if something goes wrong.
     */
    private Output select(
        final String key, final Output printed
    ) throws RepresentationsException {
        final Output selected;
        if (this.fields.contains(key)) {
            selected = new FilteringOutput(this.fields, printed);
        } else {
            selected = this;
        }
        return selected;
    }
}
