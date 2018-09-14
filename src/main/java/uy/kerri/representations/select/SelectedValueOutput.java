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
import uy.kerri.representations.FixedOutput;
import uy.kerri.representations.Output;
import uy.kerri.representations.Values;
import uy.kerri.representations.exception.ValueNotSelectedException;

/**
 * An {@link uy.kerri.representations.Output} that selects the first value that
 *  is printed on it.
 *
 * @since 1.0
 */
public class SelectedValueOutput implements Output {
    @Override
    public final String show() throws ValueNotSelectedException {
        throw new ValueNotSelectedException("The value wasn't selected yet.");
    }

    @Override
    public final Output print(final String key, final String value) {
        return this.select(value);
    }

    @Override
    public final Output print(final String key, final Integer value) {
        return this.select(value);
    }

    @Override
    public final Output print(final String key, final Boolean value) {
        return this.select(value);
    }

    @Override
    public final Output print(final String key, final Double value) {
        return this.select(value);
    }

    @Override
    public final Output print(final String key, final Long value) {
        return this.select(value);
    }

    @Override
    public final Output print(final String key, final Fields value) {
        return this.select(value);
    }

    @Override
    public final Output print(final String key, final Values values) {
        return this.select(values);
    }

    /**
     * Selects the value.
     *
     * @param value The value to be selected.
     * @return A FixedOutput that always shows the selected value.
     */
    private static Output select(final Object value) {
        return new FixedOutput(value);
    }
}
