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
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;

/**
 * An {@link uy.kerri.representations.Output} that shows if the last label
 *  printed on it matches the first label that was printed.
 *
 * @since 2.0
 */
final class LabelMatchingOutput implements Output {
    @Override
    public String show() throws LabelNotMatchedException {
        throw new LabelNotMatchedException(
            "Tried to show the result of matching without matching anything."
        );
    }

    @Override
    public Output print(
        final String key, final String value
    ) throws RepresentationsException {
        return new SelectedLabelMatchingOutput(key);
    }

    @Override
    public Output print(
        final String key, final Integer value
    ) throws RepresentationsException {
        return new SelectedLabelMatchingOutput(key);
    }

    @Override
    public Output print(
        final String key, final Boolean value
    ) throws RepresentationsException {
        return new SelectedLabelMatchingOutput(key);
    }

    @Override
    public Output print(
        final String key, final Double value
    ) throws RepresentationsException {
        return new SelectedLabelMatchingOutput(key);
    }

    @Override
    public Output print(
        final String key, final Long value
    ) throws RepresentationsException {
        return new SelectedLabelMatchingOutput(key);
    }

    @Override
    public Output print(
        final String key, final Fields value
    ) throws RepresentationsException {
        return new SelectedLabelMatchingOutput(key);
    }

    @Override
    public Output print(
        final String key, final Values values
    ) throws RepresentationsException {
        return new SelectedLabelMatchingOutput(key);
    }
}
