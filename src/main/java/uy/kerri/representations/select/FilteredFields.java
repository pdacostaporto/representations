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

/**
 * A set of fields selected from another set of fields.
 *
 * @since 2.0
 */
public final class FilteredFields implements Fields {
    /**
     * The name of the fields to be selected.
     */
    private final List<String> filtered;

    /**
     * The set where the fields will be selected from.
     */
    private final Fields origin;

    /**
     * Constructs a set of fields selected from another set of fields.
     *
     * @param names The names of the fields to be selected.
     * @param fields The set where the field will be selected from.
     */
    public FilteredFields(final List<String> names, final Fields fields) {
        this.filtered = names;
        this.origin = fields;
    }

    @Override
    public Output printTo(final Output output) throws RepresentationsException {
        return this.origin.printTo(new FilteringOutput(this.filtered, output));
    }
}
