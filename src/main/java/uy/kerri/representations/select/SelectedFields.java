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

import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;

/**
 * A set of fields selected from another
 *  {@link uy.kerri.representations.Representation}.
 *
 * @since 2.0
 */
public final class SelectedFields implements Fields {
    /**
     * The selected set of fields.
     */
    private final Fields selected;

    /**
     * Constructs a set of fields selected from another representation.
     *
     * @param values The selected fields.
     */
    private SelectedFields(final Fields values) {
        this.selected = values;
    }

    /**
     * Constructs a set of fields selected from a field of another set of
     *  fields.
     *
     * @param field The name of the field containing the set of fields to be
     *  selected.
     * @param fields The set of fields where the field will be selected from.
     */
    public SelectedFields(final String field, final Fields fields) {
        this(new SelectedFieldsFromField(field, fields));
    }

    /**
     * Constructs a set of fields selected from a sequence of values.
     *
     * @param index The index of the value to be selected.
     * @param values The sequence of values where the set of fields will be
     *  selected from.
     */
    public SelectedFields(final Integer index, final Values values) {
        this(new SelectedFieldsFromIndex(index, values));
    }

    @Override
    public Output printTo(final Output output) throws RepresentationsException {
        return this.selected.printTo(output);
    }
}
