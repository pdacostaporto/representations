package uy.kerri.representations.test.impl;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.Values;
import uy.kerri.representations.impl.ArrayOfValues;
import uy.kerri.representations.impl.LabelledValue;
import uy.kerri.representations.impl.MatchingValuesInAnyOrderTest;
import static org.hamcrest.CoreMatchers.is;

public final class MatchingValuesInAnyOrderTestTest {
    @Test
    public void matchesTwoEqualSequences() throws Exception {
        final LabelledValue first = new LabelledValue("first", "first value");
        final LabelledValue second = new LabelledValue("second", 2);
        final LabelledValue third = new LabelledValue("third", true);
        final Values some = new ArrayOfValues(first, second, third);
        final Values other = new ArrayOfValues(second, first, third);
        MatcherAssert.assertThat(
            "Two equal sequences of values didn't match.",
            new MatchingValuesInAnyOrderTest(some, other).passes(),
            is(true)
        );
    }

    @Test
    public void doesntMatchAShorterSequence() throws Exception {
        final LabelledValue first = new LabelledValue("1st field", "1");
        final LabelledValue second = new LabelledValue("2nd field", 2);
        final LabelledValue third = new LabelledValue("3rd field", "3");
        final Values some = new ArrayOfValues(first, second, third);
        final Values other = new ArrayOfValues(second, first);
        MatcherAssert.assertThat(
            "A sequence of values matched to a shorter sequence.",
            new MatchingValuesInAnyOrderTest(some, other).passes(),
            is(false)
        );
    }

    @Test
    public void doesntMatchALongerSequence() throws Exception {
        final LabelledValue first = new LabelledValue("1st", "1st value");
        final LabelledValue second = new LabelledValue("2nd", 2);
        final LabelledValue third = new LabelledValue("3rd", true);
        final LabelledValue fourth = new LabelledValue("4th", "4th value");
        final Values some = new ArrayOfValues(first, second, third);
        final Values other = new ArrayOfValues(second, first, third, fourth);
        MatcherAssert.assertThat(
            "A sequence of values matched to a longer sequence.",
            new MatchingValuesInAnyOrderTest(some, other).passes(),
            is(false)
        );
    }

    @Test
    public void doesntMatchIfALabelDiffers() throws Exception {
        final LabelledValue first = new LabelledValue("1stField", "1stValue");
        final LabelledValue second = new LabelledValue("2ndField", 2);
        final LabelledValue alternate = new LabelledValue("alternate", 2);
        final LabelledValue third = new LabelledValue("3rdField", true);
        final Values some = new ArrayOfValues(first, second, third);
        final Values other = new ArrayOfValues(alternate, first, third);
        MatcherAssert.assertThat(
            "A sequences matches another with different labels.",
            new MatchingValuesInAnyOrderTest(some, other).passes(),
            is(false)
        );
    }

    @Test
    public void doesntMatchIfADifferentValueIsRepeated() throws Exception {
        final LabelledValue first = new LabelledValue("1stF", "1stV");
        final LabelledValue second = new LabelledValue("2ndF", 2);
        final LabelledValue third = new LabelledValue("3rdF", true);
        final Values some = new ArrayOfValues(first, second, second, third);
        final Values other = new ArrayOfValues(first, first, third, second);
        MatcherAssert.assertThat(
            "A sequence matches another with different repeated values.",
            new MatchingValuesInAnyOrderTest(some, other).passes(),
            is(false)
        );
    }

    @Test
    public void matchesIfTheSameValueIsRepeated() throws Exception {
        final LabelledValue first = new LabelledValue("1stF", "1stV");
        final LabelledValue second = new LabelledValue("2ndF", 2);
        final LabelledValue third = new LabelledValue("3rdF", true);
        final Values some = new ArrayOfValues(first, second, first, third);
        final Values other = new ArrayOfValues(first, first, third, second);
        MatcherAssert.assertThat(
            "A sequence didn't match another with the same repeated values.",
            new MatchingValuesInAnyOrderTest(some, other).passes(),
            is(true)
        );
    }
}
