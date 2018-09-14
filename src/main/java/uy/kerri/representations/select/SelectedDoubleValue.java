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

import uy.kerri.representations.Output;
import uy.kerri.representations.Representation;
import uy.kerri.representations.RepresentationsException;

/**
 * A double value selected from a
 *  {@link uy.kerri.representations.Representation}.
 *
 * @since 2.0
 */
public final class SelectedDoubleValue implements SelectedValue<Double> {
    /**
     * The representation where the value will be selected from.
     */
    private final Representation origin;

    /**
     * The output that selects the value.
     */
    private final Output selection;

    /**
     * Constructs a value selected from representation by an output.
     *
     * @param representation The representation where the value will be selected
     *  from.
     * @param output The output that selects the value from the representation.
     */
    public SelectedDoubleValue(
        final Representation representation,
        final Output output
    ) {
        this.origin = representation;
        this.selection = output;
    }

    @Override
    public Double value() throws RepresentationsException {
        return Double.valueOf(this.origin.printTo(this.selection).show());
    }
}
