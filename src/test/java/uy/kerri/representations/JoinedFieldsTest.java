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

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.fake.FakeOutput;

/**
 * Tests for {@link uy.kerri.representations.JoinedFields}.
 *
 * @since 2.0
 */
public class JoinedFieldsTest {
    /**
     * ArrayOfFields prints all joined fields on the output.
     *
     * @throws Exception if something fails.
     */
    @Test
    public final void printsJoinedFields() throws Exception {
        final String name = "name:String:Daniel";
        final String surname = "Umpiérrez";
        final Integer age = 43;
        final Boolean registered = false;
        final Integer albums = 3;
        final FakeOutput output = new FakeOutput(name);
        MatcherAssert.assertThat(
            "Fields weren't printed correctly.",
            new JoinedFields(
                new ArrayOfFields(
                    new LabelledValue("surname", surname)
                ),
                new ArrayOfFields(
                    new LabelledValue("age", age),
                    new LabelledValue("registered", registered)
                ),
                new ArrayOfFields(
                    new LabelledValue("albums", albums)
                )
            ).printTo(output).show(),
            CoreMatchers.equalTo(
                StringUtils.join(
                    new String[] {
                        name,
                        String.format("surname:String:%s", surname),
                        String.format("age:Integer:%d", age),
                        String.format("registered:Boolean:%b", registered),
                        String.format("albums:Integer:%d", albums),
                    },
                    String.format("%n")
                )
            )
        );
    }
}
