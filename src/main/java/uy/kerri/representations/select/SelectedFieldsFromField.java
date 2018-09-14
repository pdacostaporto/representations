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

/**
 * A set of fields selected from a field of another set of fields.
 *
 * @since 2.0
 */
final class SelectedFieldsFromField implements Fields {
    /**
     * The name of the field to be selected.
     */
    private final String field;

    /**
     * The set of fields where the field will be selected from.
     */
    private final Fields container;

    /**
     * Constructs a set of fields selected from a field of another set of
     *  fields.
     *
     * @param name The name of the field containing the set of fields to be
     *  selected.
     * @param fields The set of fields where the field will be selected from.
     */
    SelectedFieldsFromField(final String name, final Fields fields) {
        this.field = name;
        this.container = fields;
    }

    @Override
    public Output printTo(final Output output) throws RepresentationsException {
        return this.container.printTo(new SelectedOutput(this.field, output));
    }
}
