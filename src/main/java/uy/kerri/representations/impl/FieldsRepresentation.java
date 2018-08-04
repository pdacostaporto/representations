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
import uy.kerri.representations.Field;
import uy.kerri.representations.Output;
import uy.kerri.representations.Representation;

/**
 * {@link uy.kerri.representations.Representation} consisting of multiple
 *  fields.
 *
 * @since 1.0
 */
public class FieldsRepresentation implements Representation {
    /**
     * Fields of the representation.
     */
    private final Field[] fields;

    /**
     * Constructs a representation with the given fields.
     *
     * @param flds The fields for the constructed representation.
     */
    public FieldsRepresentation(final Field... flds) {
        this.fields = Arrays.copyOf(flds, flds.length);
    }

    /**
     * Constructs an empty representation.
     */
    public FieldsRepresentation() {
        this(new Field[0]);
    }

    @Override
    public final Output print(final Output output) throws Exception {
        Output printed = output;
        for (final Field field : this.fields) {
            printed = field.print(printed);
        }
        return printed;
    }
}
