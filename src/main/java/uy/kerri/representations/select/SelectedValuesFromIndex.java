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

import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;

/**
 * A sequence of values selected from another sequence of values.
 *
 * @since 2.0
 */
final class SelectedValuesFromIndex implements Values {
    /**
     * The index of the value to be selected.
     */
    private final Integer index;

    /**
     * The sequence of values where the value will be selected from.
     */
    private final Values container;

    /**
     * Constructs a sequence of values selected from another sequence of values.
     *
     * @param idx The index of the value to be selected.
     * @param values The sequence of values where the index will be selected
     *  from.
     */
    SelectedValuesFromIndex(final Integer idx, final Values values) {
        this.index = idx;
        this.container = values;
    }

    @Override
    public Output printTo(final Output output) throws RepresentationsException {
        return this.container.printTo(new SelectedOutput(this.index, output));
    }
}
