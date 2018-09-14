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

import org.apache.commons.lang3.StringUtils;
import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;

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
    public final FakeOutput print(final String key, final String value) {
        return this.print(key, "String", value);
    }

    @Override
    public final FakeOutput print(final String key, final Integer value) {
        return this.print(key, "Integer", String.format("%d", value));
    }

    @Override
    public final FakeOutput print(final String key, final Boolean value) {
        return this.print(key, "Boolean", String.format("%b", value));
    }

    @Override
    public final FakeOutput print(final String key, final Double value) {
        return this.print(key, "Double", String.format("%f", value));
    }

    @Override
    public final FakeOutput print(final String key, final Long value) {
        return this.print(key, "Long", String.format("%d", value));
    }

    @Override
    public final FakeOutput print(
        final String key,
        final Fields value
    ) throws RepresentationsException {
        return this.print(
            key,
            "Nested",
            this.reformat(
                value.printTo(new FakeOutput()).show(),
                String.format("%n\t")
            )
        );
    }

    @Override
    public final FakeOutput print(
        final String key,
        final Values values
    ) throws RepresentationsException {
        return this.print(
            key,
            "Multivalued",
            this.reformat(
                values.printTo(new FakeOutput()).show(),
                String.format("%n*")
            )
        );
    }

    /**
     * Reformats the output of another representation.
     *
     * @param input The original output of the representation.
     * @param separator The separator used to join the lines of the output.
     * @return The reformatted output.
     */
    private static String reformat(
        final String input,
        final String separator
    ) {
        return String.format(
            "%s%s",
            separator,
            StringUtils.join(input.split(String.format("%n")), separator)
        );
    }

    /**
     * Prints the output with the value already formatted.
     *
     * @param key The key of the printed field.
     * @param type The original type of the printed value.
     * @param value The value of the printed field already formatted as string.
     * @return The output with the field printed on it.
     */
    private FakeOutput print(
        final String key,
        final String type,
        final String value
    ) {
        return new FakeOutput(
            StringUtils.stripStart(
                String.format("%s%n%s:%s:%s", this.output, key, type, value),
                String.format("%n")
            )
        );
    }
}
