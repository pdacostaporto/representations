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

import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.fake.FakeOutput;

/**
 * Tests for {@link uy.kerri.representations.ArrayOfValues}.
 *
 * @since 2.0
 */
public class ArrayOfValuesTest {
    /**
     * ArrayOfValues prints nothing if it has no values.
     *
     * @throws Exception if something fails.
     */
    @Test
    public final void printsNothingIfEmpty() throws Exception {
        final String name = "name:String:William";
        final FakeOutput output = new FakeOutput(name);
        MatcherAssert.assertThat(
            "Empty output isn't empty",
            new ArrayOfValues().printTo(output).show(),
            CoreMatchers.equalTo(name)
        );
    }

    /**
     * ArrayOfValues prints its representations on the output.
     *
     * @throws Exception if something fails.
     */
    @Test
    public final void printsFields() throws Exception {
        final String name = "name:String:Daniel";
        final String surname = "Umpiérrez";
        final Integer age = 43;
        final Boolean registered = false;
        final FakeOutput output = new FakeOutput(name);
        MatcherAssert.assertThat(
            "Representations weren't printed correctly.",
            new ArrayOfValues(
                new LabelledValue("surname", surname),
                new LabelledValue("age", age),
                new LabelledValue("registered", registered)
            ).printTo(output).show(),
            CoreMatchers.equalTo(
                StringUtils.join(
                    new String[] {
                        name,
                        String.format("surname:String:%s", surname),
                        String.format("age:Integer:%d", age),
                        String.format("registered:Boolean:%b", registered),
                    },
                    String.format("%n")
                )
            )
        );
    }

    /**
     * ArrayOfValues accepts a {@link java.util.Collection}.
     *
     * @throws Exception if something fails.
     */
    @Test
    public final void acceptsACollection() throws Exception {
        final String label = "todo";
        final String clean = "clean my room";
        final String homework = "do my homework";
        final String clothes = "wash the clothes";
        final String format = "%s:String:%s";
        final FakeOutput output = new FakeOutput();
        MatcherAssert.assertThat(
            "Values from collection weren't printed correctly.",
            new ArrayOfValues(
                (Collection<LabelledValue>) Arrays.asList(
                    new LabelledValue(label, clean),
                    new LabelledValue(label, homework),
                    new LabelledValue(label, clothes)
                )
            ).printTo(output).show(),
            CoreMatchers.equalTo(
                StringUtils.join(
                    new String[] {
                        String.format(format, label, clean),
                        String.format(format, label, homework),
                        String.format(format, label, clothes),
                    },
                    String.format("%n")
                )
            )
        );
    }
}
