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
package uy.kerri.representations;

import java.util.Arrays;
import java.util.Collection;

/**
 * An array of {@link uy.kerri.representations.Value}s.
 *
 * @since 2.0
 */
public class ArrayOfValues implements Values {
    /**
     * The array.
     */
    private final Value[] array;

    /**
     * Constructs an array with the given values.
     *
     * @param values The array of values.
     */
    public ArrayOfValues(final Value... values) {
        this.array = Arrays.copyOf(values, values.length);
    }

    /**
     * Constructs an empty array of values.
     */
    public ArrayOfValues() {
        this(new Value[0]);
    }

    /**
     * Constructs an array of values from a {@link java.util.Collection}.
     *
     * @param collection A collection of fields.
     */
    public ArrayOfValues(final Collection<? extends Value> collection) {
        this(collection.toArray(new Value[0]));
    }

    @Override
    public final Output printTo(
        final Output output
    ) throws RepresentationsException {
        Output printed = output;
        for (final Value value : this.array) {
            printed = value.printTo(printed);
        }
        return printed;
    }
}
