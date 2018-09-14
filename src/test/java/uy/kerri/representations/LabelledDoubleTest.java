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
 * Tests for {@link uy.kerri.representations.LabelledDouble}.
 *
 * @since 2.0
 */
public class LabelledDoubleTest {
    /**
     * LabelledDouble prints itself in an output.
     *
     * @throws Exception if something fails.
     */
    @Test
    public final void printsItself() throws Exception {
        final String humidity = "humidity:String:100%";
        final Double pressure = 29.83;
        final FakeOutput output = new FakeOutput(humidity);
        MatcherAssert.assertThat(
            "The value didn't print itself correctly.",
            new LabelledDouble("pressure", pressure).printTo(output).show(),
            CoreMatchers.equalTo(
                StringUtils.join(
                    new String[] {
                        humidity,
                        String.format("pressure:Double:%f", pressure),
                    },
                    String.format("%n")
                )
            )
        );
    }
}
