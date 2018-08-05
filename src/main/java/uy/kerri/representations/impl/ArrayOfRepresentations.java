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

import java.util.Arrays;
import uy.kerri.representations.Output;
import uy.kerri.representations.Representation;
import uy.kerri.representations.Representations;

/**
 * An array of {@link uy.kerri.Representation}s.
 *
 * @since 1.0
 */
public class ArrayOfRepresentations implements Representations {
    /**
     * The array.
     */
    private final Representation[] array;

    /**
     * Constructs an array with the given representations.
     *
     * @param values The array of representations.
     */
    public ArrayOfRepresentations(final Representation... values) {
        this.array = Arrays.copyOf(values, values.length);
    }

    /**
     * Constructs an empty array of representations.
     */
    public ArrayOfRepresentations() {
        this(new Representation[0]);
    }

    @Override
    public final Output printTo(final Output output) throws Exception {
        Output printed = output;
        for (final Representation value : this.array) {
            printed = value.printTo(printed);
        }
        return printed;
    }
}
