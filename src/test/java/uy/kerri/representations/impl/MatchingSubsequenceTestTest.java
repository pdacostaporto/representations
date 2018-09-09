package uy.kerri.representations.test.impl;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.Values;
import uy.kerri.representations.impl.ArrayOfValues;
import uy.kerri.representations.impl.LabelledValue;
import uy.kerri.representations.impl.MatchingSubsequenceTest;
import static org.hamcrest.CoreMatchers.is;

public final class MatchingSubsequenceTestTest {
    @Test
    public void passesIfASequenceIsContainedOnAnother() throws Exception {
        final LabelledValue first = new LabelledValue("first", "first value");
        final LabelledValue second = new LabelledValue("second", 2);
        final LabelledValue third = new LabelledValue("third", 1L);
        final LabelledValue fourth = new LabelledValue("fourth", false);
        final Values some = new ArrayOfValues(first, second, third, fourth);
        final Values other = new ArrayOfValues(second, third);
        MatcherAssert.assertThat(
            "A contained sequence didn't pass.",
            new MatchingSubsequenceTest(2, some, other).passes(),
            is(true)
        );
    }

    @Test
    public void doesntPassIfALabelDiffers() throws Exception {
        final LabelledValue first = new LabelledValue("1stField", "1stValue");
        final LabelledValue second = new LabelledValue("2ndField", 1.0);
        final LabelledValue third = new LabelledValue("3rdField", true);
        final LabelledValue alternate = new LabelledValue("alternate", 2);
        final LabelledValue fourth = new LabelledValue("4thField", false);
        final Values some = new ArrayOfValues(first, second, third);
        final Values other = new ArrayOfValues(second, alternate);
        MatcherAssert.assertThat(
            "A subsequence passed despite having a different label.",
            new MatchingSubsequenceTest(2, some, other).passes(),
            is(false)
        );
    }

    @Test
    public void doesntPassIfAValueDiffers() throws Exception {
        final String label = "3rd";
        final LabelledValue first = new LabelledValue("1st", "1");
        final LabelledValue second = new LabelledValue("2nd", 2);
        final LabelledValue third = new LabelledValue(label, true);
        final LabelledValue alternate = new LabelledValue(label, false);
        final LabelledValue fourth = new LabelledValue("4thField", false);
        final Values some = new ArrayOfValues(first, second, third);
        final Values other = new ArrayOfValues(second, alternate);
        MatcherAssert.assertThat(
            "A subsequence passed despite having a different value.",
            new MatchingSubsequenceTest(2, some, other).passes(),
            is(false)
        );
    }
}
