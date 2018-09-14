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
package uy.kerri.representations.matchers;

import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.ArrayOfFields;
import uy.kerri.representations.ArrayOfValues;
import uy.kerri.representations.LabelledValue;

/**
 * Tests for {@link uy.kerri.representations.matchers.RepresentationMatchers}.
 *
 * @since 2.0
 */
public final class MatchersTest {
    /**
     * EqualToValues matches two equal sequences of values.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void equalToMatchesTwoEqualSequences() throws Exception {
        final LabelledValue first = new LabelledValue("first", "first val");
        final LabelledValue second = new LabelledValue("second", "second val");
        final LabelledValue third = new LabelledValue("third", "third val");
        MatcherAssert.assertThat(
            new ArrayOfValues(first, second, third),
            RepresentationMatchers.equalTo(
                new ArrayOfValues(first, second, third)
            )
        );
    }

    /**
     * EqualToValues does not match two different sequences of values.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void equalToDoesNotMatchTwoDifferentSequences() throws Exception {
        final LabelledValue first = new LabelledValue("1st", "1st val");
        final LabelledValue second = new LabelledValue("2nd", "2nd val");
        final LabelledValue third = new LabelledValue("3rd", "3rd val");
        MatcherAssert.assertThat(
            new ArrayOfValues(first, second, third),
            CoreMatchers.not(
                RepresentationMatchers.equalTo(
                    new ArrayOfValues(first, third, second)
                )
            )
        );
    }

    /**
     * EqualToValuesInAnyOrder matches two equal sequences of values
     *  disregarding the order of their values.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void equalToInAnyOrderMatchesTwoEqualSequences() throws Exception {
        final LabelledValue first = new LabelledValue("1stL", "1stV");
        final LabelledValue second = new LabelledValue("2ndL", "2ndV");
        final LabelledValue third = new LabelledValue("3rdL", "3rdV");
        MatcherAssert.assertThat(
            new ArrayOfValues(third, first, second, third),
            RepresentationMatchers.equalToInAnyOrder(
                new ArrayOfValues(first, third, third, second)
            )
        );
    }

    /**
     * EqualToValuesInAnyOrder does not match two different sequences of values.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void equalToInAnyOrderDoesNotMatchDifferentSeqs() throws Exception {
        final LabelledValue first = new LabelledValue("1stLabel", "1stVal");
        final LabelledValue second = new LabelledValue("2ndLabel", "2ndVal");
        final LabelledValue third = new LabelledValue("3rdLabel", "3rdVal");
        MatcherAssert.assertThat(
            new ArrayOfValues(first, second, second, third),
            CoreMatchers.not(
                RepresentationMatchers.equalToInAnyOrder(
                    new ArrayOfValues(first, third, third, second)
                )
            )
        );
    }

    /**
     * ContainsValues matches a group of values contained on another.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void containsMatchesAGroupOfValuesContainedOnAnother()
        throws Exception {
        final LabelledValue first = new LabelledValue("1st Label", "1stCV");
        final LabelledValue second = new LabelledValue("2nd Label", "2ndCV");
        final LabelledValue third = new LabelledValue("3rd Label", "3rdCV");
        MatcherAssert.assertThat(
            new ArrayOfValues(first, second, third),
            RepresentationMatchers.contains(new ArrayOfValues(third, first))
        );
    }

    /**
     * ContainsValues does not match a group of values not contained on another.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void containsDoesNotMatchAGroupNotContained() throws Exception {
        final LabelledValue first = new LabelledValue(
            "1stIndex", "1stIndexVal"
        );
        final String alternate = "3rdIndex";
        MatcherAssert.assertThat(
            new ArrayOfValues(
                first,
                new LabelledValue("2ndIndex", "2ndIndexVal"),
                new LabelledValue(alternate, "expectedVal")
            ),
            CoreMatchers.not(
                RepresentationMatchers.contains(
                    new ArrayOfValues(
                        first, new LabelledValue(alternate, "actualVal")
                    )
                )
            )
        );
    }

    /**
     * EqualToFields matches two equal sets of fields.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void equalToMatchesTwoEqualSetsOfFields() throws Exception {
        final LabelledValue first = new LabelledValue("1stF", "1stFV");
        final LabelledValue second = new LabelledValue("2ndF", "2ndFV");
        final LabelledValue third = new LabelledValue("3rdF", "3rdFV");
        MatcherAssert.assertThat(
            new ArrayOfFields(first, second, third),
            RepresentationMatchers.equalTo(
                new ArrayOfFields(first, third, second)
            )
        );
    }

    /**
     * EqualToFields does not match two different sets of fields.
     *
     * @throws Exception if something goes wrong.
     */
    @Test
    public void equalToDoesNotMatchDifferentSets() throws Exception {
        final LabelledValue first = new LabelledValue(
            "1stField", "1stFieldVal"
        );
        final LabelledValue second = new LabelledValue(
            "2ndField", "2ndFieldVal"
        );
        final String alternate = "3rdField";
        MatcherAssert.assertThat(
            new ArrayOfFields(
                first,
                second,
                new LabelledValue(alternate, "expected")
            ),
            CoreMatchers.not(
                RepresentationMatchers.equalTo(
                    new ArrayOfFields(
                        first,
                        new LabelledValue(alternate, "actual"),
                        second
                    )
                )
            )
        );
    }
}
