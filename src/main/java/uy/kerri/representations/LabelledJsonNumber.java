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

import javax.json.JsonNumber;

/**
 * A label-value pair with a {@link javax.json.JsonNumber} value.
 *
 * @since 2.1.0
 */
final class LabelledJsonNumber implements Field, Value {
    /**
     * The label.
     */
    private final String label;

    /**
     * The value.
     */
    private final JsonNumber value;

    /**
     * Constructs a pair with given label and value.
     *
     * @param label The label for this pair.
     * @param value The value for this pair.
     */
    LabelledJsonNumber(final String label, final JsonNumber value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public Output printTo(final Output output) throws RepresentationsException {
        final Output printed;
        if (this.value.isIntegral()) {
            printed = output.print(this.label, this.value.longValue());
        } else {
            printed = output.print(this.label, this.value.doubleValue());
        }
        return printed;
    }
}
