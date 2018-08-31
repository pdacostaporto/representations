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
import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.Value;
import uy.kerri.representations.Values;

/**
 * A label-value pair.
 *
 * @since 1.0
 */
public class LabelledValue implements Field, Value {
    /**
     * The value.
     */
    private final Value value;

    /**
     * Constructs a new value that encapsulates another value.
     * @param val The value to encapsulate.
     */
    private LabelledValue(final Value val) {
        this.value = val;
    }

    /**
     * Constructs a pair with given label and integer value.
     *
     * @param name The label for this pair.
     * @param val The integer value for this pair.
     */
    public LabelledValue(final String name, final Integer val) {
        this(new LabelledInteger(name, val));
    }

    /**
     * Constructs a pair with given label and string value.
     *
     * @param name The label for this pair.
     * @param val The string value for this pair.
     */
    public LabelledValue(final String name, final String val) {
        this(new LabelledString(name, val));
    }

    /**
     * Constructs a pair with given label and boolean value.
     *
     * @param name The label for this pair.
     * @param val The boolean value for this pair.
     */
    public LabelledValue(final String name, final Boolean val) {
        this(new LabelledBoolean(name, val));
    }

    /**
     * Constructs a pair with given label and double value.
     *
     * @param name The label for this pair.
     * @param val The double value for this pair.
     */
    public LabelledValue(final String name, final Double val) {
        this(new LabelledDouble(name, val));
    }

    /**
     * Constructs a pair with given label and long value.
     *
     * @param name The label for this pair.
     * @param val The long value for this pair.
     */
    public LabelledValue(final String name, final Long val) {
        this(new LabelledLong(name, val));
    }

    /**
     * Constructs a pair with given label and composite value.
     *
     * @param name The label for this pair.
     * @param val The composite value for this pair.
     */
    public LabelledValue(final String name, final Fields val) {
        this(new LabelledFields(name, val));
    }

    /**
     * Constructs a pair with given label and values.
     *
     * @param name The label for this pair.
     * @param values The values for this pair.
     */
    public LabelledValue(final String name, final Values values) {
        this(new LabelledValues(name, values));
    }

    @Override
    public final Output printTo(final Output output) throws Exception {
        return this.value.printTo(output);
    }
}
