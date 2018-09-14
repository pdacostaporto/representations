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

import uy.kerri.representations.RepresentationsException;

/**
 * Exception to throw when an output is to be shown but its value has not been
 *  selected yet.
 *
 * @since 2.0
 */
public class ValueNotSelectedException extends RepresentationsException {
    /**
     * Creates an exception.
     */
    public ValueNotSelectedException() {
        super();
    }

    /**
    * Creates an exception with given message and cause.
    *
    * @param message The message of the exception.
    */
    public ValueNotSelectedException(final String message) {
        super(message);
    }

    /**
    * Creates an exception with given message and cause.
    *
    * @param message The message of the exception.
    * @param cause The cause of the exception.
    */
    public ValueNotSelectedException(
        final String message, final Exception cause
    ) {
        super(message, cause);
    }
}
