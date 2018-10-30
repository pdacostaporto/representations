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
package uy.kerri.representations.select;

import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;

/**
 * An {@link uy.kerri.representations.Output} that throws an exception when
 *  tried to be shown.
 *
 * @since 2.1.1
 */
final class InvalidOutput implements Output {
    /**
     * The exception to be thrown.
     */
    private final RepresentationsException exception;

    /**
     * Constructs an output that throws an exception when tried to be shown.
     *
     * @param exception The exception to be thrown.
     */
    InvalidOutput(final RepresentationsException exception) {
        this.exception = exception;
    }

    @Override
    public String show() throws RepresentationsException {
        throw this.exception;
    }

    @Override
    public Output print(final String key, final String value) {
        return this;
    }

    @Override
    public Output print(final String key, final Integer value) {
        return this;
    }

    @Override
    public Output print(final String key, final Boolean value) {
        return this;
    }

    @Override
    public Output print(final String key, final Double value) {
        return this;
    }

    @Override
    public Output print(final String key, final Long value) {
        return this;
    }

    @Override
    public Output print(final String key, final Fields value) {
        return this;
    }

    @Override
    public Output print(final String key, final Values values) {
        return this;
    }
}
