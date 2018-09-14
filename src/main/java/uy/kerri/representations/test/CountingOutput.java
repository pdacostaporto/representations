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
import uy.kerri.representations.Output;
import uy.kerri.representations.Values;

/**
 * An {@link uy.kerri.representations.Output} that counts the number of pairs
 *  that were printed on it.
 *
 * @since 2.0
 */
final class CountingOutput implements Output {
    /**
     * The count of pairs printed so far.
     */
    private final Integer count;

    /**
     * Constructs an output that counts the number of pairs that were printed on
     *  it, initialized to some value.
     *
     * @param printed The count of prints so far.
     */
    private CountingOutput(final Integer printed) {
        this.count = printed;
    }

    /**
     * Constructs an output that counts the number of pairs that were printed on
     *  it.
     */
    CountingOutput() {
        this(0);
    }

    @Override
    public String show() {
        return this.count.toString();
    }

    @Override
    public Output print(final String key, final String value) {
        return this.incremented();
    }

    @Override
    public Output print(final String key, final Integer value) {
        return this.incremented();
    }

    @Override
    public Output print(final String key, final Boolean value) {
        return this.incremented();
    }

    @Override
    public Output print(final String key, final Double value) {
        return this.incremented();
    }

    @Override
    public Output print(final String key, final Long value) {
        return this.incremented();
    }

    @Override
    public Output print(final String key, final Fields value) {
        return this.incremented();
    }

    @Override
    public Output print(final String key, final Values values) {
        return this.incremented();
    }

    /**
     * Increments the count of prints.
     *
     * @return An output with a count of prints incremented by one.
     */
    private Output incremented() {
        return new CountingOutput(this.count + 1);
    }
}
