package uy.kerri.representations.impl;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.impl.ArrayOfValues;
import uy.kerri.representations.impl.LabelledValue;
import uy.kerri.representations.impl.MatchingCountTest;
import static org.hamcrest.CoreMatchers.is;

public final class MatchingCountTestTest {
    @Test
    public void matchesTwoEqualLengthSequences() throws Exception {
        MatcherAssert.assertThat(
            "Two sequences of the same length didn't match",
            new MatchingCountTest(
                new ArrayOfValues(
                    new LabelledValue("first", "first value"),
                    new LabelledValue("second", "second value"),
                    new LabelledValue("third", "third value")
                ),
                new ArrayOfValues(
                    new LabelledValue("fourth", "fourth value"),
                    new LabelledValue("fifth", "fifth value"),
                    new LabelledValue("sixth", "sixth value")
                )
            ).passes(),
            is(true)
        );
    }

    @Test
    public void doesntMatchWithALongerSequence() throws Exception {
        MatcherAssert.assertThat(
            "A sequence matched with a longer one.",
            new MatchingCountTest(
                new ArrayOfValues(
                    new LabelledValue("1st", "1st value"),
                    new LabelledValue("2nd", "2nd value"),
                    new LabelledValue("3rd", "3rd value")
                ),
                new ArrayOfValues(
                    new LabelledValue("4th", "4th value"),
                    new LabelledValue("5th", "5th value"),
                    new LabelledValue("6th", "6th value"),
                    new LabelledValue("7th", "7th value")
                )
            ).passes(),
            is(false)
        );
    }

    @Test
    public void doesntMatchWithAShorterSequence() throws Exception {
        MatcherAssert.assertThat(
            "A sequence matched with a shorter one.",
            new MatchingCountTest(
                new ArrayOfValues(
                    new LabelledValue("1st value", "1"),
                    new LabelledValue("2nd value", "2"),
                    new LabelledValue("3rd value", "3")
                ),
                new ArrayOfValues(
                    new LabelledValue("4th value", "4"),
                    new LabelledValue("5th value", "5")
                )
            ).passes(),
            is(false)
        );
    }
}
