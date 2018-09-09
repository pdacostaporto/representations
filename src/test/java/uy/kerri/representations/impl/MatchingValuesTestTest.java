package uy.kerri.representations.test.impl;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.Values;
import uy.kerri.representations.impl.ArrayOfValues;
import uy.kerri.representations.impl.LabelledValue;
import uy.kerri.representations.impl.MatchingValuesTest;
import static org.hamcrest.CoreMatchers.is;

public final class MatchingValuesTestTest {
    @Test
    public void matchesTwoEqualSequences() throws Exception {
        final LabelledValue first = new LabelledValue("first", "first value");
        final LabelledValue second = new LabelledValue("second", 2);
        final LabelledValue third = new LabelledValue("third", true);
        final Values some = new ArrayOfValues(first, second, third);
        final Values other = new ArrayOfValues(first, second, third);
        MatcherAssert.assertThat(
            "Two equal sequences of values didn't match.",
            new MatchingValuesTest(some, other).passes(),
            is(true)
        );
    }

    @Test
    public void doesntMatchAShorterSequence() throws Exception {
        final LabelledValue first = new LabelledValue("1st field", "1");
        final LabelledValue second = new LabelledValue("2nd field", 2);
        final LabelledValue third = new LabelledValue("3rd field", "3");
        final Values some = new ArrayOfValues(first, second, third);
        final Values other = new ArrayOfValues(first, second);
        MatcherAssert.assertThat(
            "A sequence of values matched to a shorter sequence.",
            new MatchingValuesTest(some, other).passes(),
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
        final Values other = new ArrayOfValues(first, second, third, fourth);
        MatcherAssert.assertThat(
            "A sequence of values matched to a longer sequence.",
            new MatchingValuesTest(some, other).passes(),
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
        final Values other = new ArrayOfValues(first, alternate, third);
        MatcherAssert.assertThat(
            "A sequences matches another with different labels.",
            new MatchingValuesTest(some, other).passes(),
            is(false)
        );
    }
}
