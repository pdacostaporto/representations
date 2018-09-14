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

/**
 * {@link uy.kerri.representations.Output} that shows a fixed value and ignores
 *  anything else printed on it.
 *
 * @since 2.0
 */
public class FixedOutput implements Output {
    /**
     * The value to show.
     */
    private Object fixed;

    /**
     * Constructs an output that shows a fixed value.
     *
     * @param value The object that will be shown.
     */
    public FixedOutput(final Object value) {
        this.fixed = value;
    }

    /**
     * Constructs an empty output.
     */
    public FixedOutput() {
        this("");
    }

    @Override
    public final String show() {
        return this.fixed.toString();
    }

    @Override
    public final FixedOutput print(final String key, final String value) {
        return this;
    }

    @Override
    public final FixedOutput print(final String key, final Integer value) {
        return this;
    }

    @Override
    public final FixedOutput print(final String key, final Boolean value) {
        return this;
    }

    @Override
    public final FixedOutput print(final String key, final Double value) {
        return this;
    }

    @Override
    public final FixedOutput print(final String key, final Long value) {
        return this;
    }

    @Override
    public final FixedOutput print(final String key, final Fields value) {
        return this;
    }

    @Override
    public final FixedOutput print(final String key, final Values values) {
        return this;
    }
}
