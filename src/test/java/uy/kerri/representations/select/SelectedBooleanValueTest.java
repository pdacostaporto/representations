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

import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.ArrayOfValues;
import uy.kerri.representations.LabelledValue;

/**
 * Tests for {@link uy.kerri.representations.select.SelectedBooleanValue}.
 *
 * @since 2.0
 */
public final class SelectedBooleanValueTest {
    /**
     * SelectedBooleanValue allows to select a boolean.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void selectsABoolean() throws Exception {
        final Integer index = 1;
        final Boolean value = true;
        MatcherAssert.assertThat(
            "A boolean value wasn't selected.",
            new SelectedBooleanValue(
                new ArrayOfValues(
                    new LabelledValue("label", value)
                ),
                new SelectedOutput(index)
            ).value(),
            CoreMatchers.is(value)
        );
    }
}
