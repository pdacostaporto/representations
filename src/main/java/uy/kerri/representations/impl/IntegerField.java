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

import uy.kerri.representations.Field;
import uy.kerri.representations.Output;

/**
 * {@link uy.kerri.representations.Field} consisting of an integer value.
 *
 * @since 1.0
 */
public class IntegerField implements Field {
    /**
     * The name of this field.
     */
    private final String name;

    /**
     * The value of this field.
     */
    private final Integer value;

    /**
     * Constructs the field with given key and value.
     *
     * @param key The name for this field.
     * @param val The value for this field.
     */
    public IntegerField(final String key, final Integer val) {
        this.name = key;
        this.value = val;
    }

    @Override
    public final Output print(final Output output) throws Exception {
        return output.print(this.name, this.value);
    }
}
