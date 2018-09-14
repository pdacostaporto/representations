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
package uy.kerri.representations.test;

import uy.kerri.representations.Field;
import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;

/**
 * A {@link uy.kerri.representations.test.Test} to verify that a field is
 *  contained in a group of fields and its value matches.
 *
 * @since 2.0
 */
public final class ContainedFieldTest implements Test {
    /**
     * The output that shows if the field is contained.
     */
    private final Output output;

    /**
     * The group of fields where the field should be contained.
     */
    private final Fields fields;

    /**
     * Constructs a test to verify if a field is contained in a group of fields.
     *
     * @param out The output that shows if the field is contained.
     * @param actual The group of fields where the field should be contained.
     */
    private ContainedFieldTest(final Output out, final Fields actual) {
        this.output = out;
        this.fields = actual;
    }

    /**
     * Constructs a test to verify if a field is contained in a group of fields.
     *
     * @param field The field to be tested.
     * @param actual The group of fields where the field should be contained.
     */
    ContainedFieldTest(final Field field, final Fields actual) {
        this(new FieldMatchingOutput(field), actual);
    }

    @Override
    public Boolean passes() throws RepresentationsException {
        return Boolean.valueOf(this.fields.printTo(this.output).show());
    }
}
