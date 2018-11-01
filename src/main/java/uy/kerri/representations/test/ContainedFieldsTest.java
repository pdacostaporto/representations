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

import uy.kerri.representations.Fields;
import uy.kerri.representations.RepresentationsException;

/**
 * A {@link uy.kerri.representations.test.Test} to verify that a set of fields
 *  is contained in another.
 *
 * @since 2.2
 */
public final class ContainedFieldsTest implements Test {
    /**
     * The container set of fields.
     */
    private final Fields container;

    /**
     * The contained set of fields.
     */
    private final Fields contained;

    /**
     * Constructs a test to verify that a set of fields is contained in another.
     *
     * @param container The container set of fields.
     * @param contained The contained set of fields.
     */
    public ContainedFieldsTest(final Fields container, final Fields contained) {
        this.container = container;
        this.contained = contained;
    }

    @Override
    public Boolean passes() throws RepresentationsException {
        return Boolean.valueOf(
            this.container.printTo(new FieldsMatchingOutput(this.contained))
            .show()
        );
    }
}
