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
package uy.kerri.representations.fake;

import uy.kerri.representations.Output;

/**
 * Output implementation for testing purposes.
 *
 * @since 1.0
 */
public class FakeOutput implements Output {
    /**
     * Printed string of the output.
     */
    private String output;

    /**
     * Constructs an output already printed to the preformat.
     *
     * @param preformat A preformatted string to initialize the output.
     */
    public FakeOutput(final String preformat) {
        this.output = preformat;
    }

    /**
     * Constructs an empty output.
     */
    public FakeOutput() {
        this("");
    }

    @Override
    public final String show() {
        return this.output;
    }

    @Override
    public final FakeOutput print(final String key, final String value)
    throws Exception {
        return new FakeOutput(
            String.format("%s%n%s:String:%s", this.output, key, value)
        );
    }

    @Override
    public final FakeOutput print(final String key, final Integer value)
    throws Exception {
        return new FakeOutput(
            String.format("%s%n%s:Integer:%d", this.output, key, value)
        );
    }

    @Override
    public final FakeOutput print(final String key, final Boolean value)
    throws Exception {
        return new FakeOutput(
            String.format("%s%n%s:Boolean:%b", this.output, key, value)
        );
    }
}
