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
package uy.kerri.representations.impl;

import java.util.List;
import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.Values;

public final class FilteringOutput implements Output {
    private final List<String> fields;

    private final Output nested;

    public FilteringOutput(final List<String> names, final Output output) {
        this.fields = names;
        this.nested = output;
    }

    @Override
    public final String show() throws Exception {
        return this.nested.show();
    }

    @Override
    public final Output print(
        final String key, final String value
    ) throws Exception {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public final Output print(
        final String key, final Integer value
    ) throws Exception {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public final Output print(
        final String key, final Boolean value
    ) throws Exception {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public final Output print(
        final String key, final Double value
    ) throws Exception {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public final Output print(
        final String key, final Long value
    ) throws Exception {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public final Output print(
        final String key,
        final Fields value
    ) throws Exception {
        return this.select(key, this.nested.print(key, value));
    }

    @Override
    public final Output print(
        final String key, final Values values
    ) throws Exception {
        return this.select(key, this.nested.print(key, values));
    }

    private Output select(
        final String key, final Output printed
    ) throws Exception {
        final Output selected;
        if (this.fields.contains(key)) {
            selected = new FilteringOutput(this.fields, printed);
        } else {
            selected = this;
        }
        return selected;
    }
}
