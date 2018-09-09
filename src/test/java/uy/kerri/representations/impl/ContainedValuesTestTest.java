package uy.kerri.representations.test.impl;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.Values;
import uy.kerri.representations.impl.ArrayOfFields;
import uy.kerri.representations.impl.ArrayOfValues;
import uy.kerri.representations.impl.LabelledValue;
import uy.kerri.representations.impl.ContainedValuesTest;
import static org.hamcrest.CoreMatchers.is;

public final class ContainedValuesTestTest {
    @Test
    public void passesIfASequenceIsContainedOnAnother() throws Exception {
        final LabelledValue first = new LabelledValue("first", "first value");
        final LabelledValue second = new LabelledValue("second", 2);
        final LabelledValue third = new LabelledValue(
            "third", new ArrayOfFields()
        );
        final LabelledValue fourth = new LabelledValue("fourth", false);
        final Values container = new ArrayOfValues(
            first, second, third, fourth
        );
        final Values contained = new ArrayOfValues(second, third);
        MatcherAssert.assertThat(
            "A contained sequence didn't pass.",
            new ContainedValuesTest(container, contained).passes(),
            is(true)
        );
    }

    @Test
    public void doesntPassIfALabelDiffers() throws Exception {
        final LabelledValue first = new LabelledValue("1stField", "1stValue");
        final LabelledValue second = new LabelledValue("2ndField", 2);
        final LabelledValue third = new LabelledValue("3rdField", true);
        final LabelledValue alternate = new LabelledValue("alternate", 2);
        final Values container = new ArrayOfValues(first, second, third);
        final Values contained = new ArrayOfValues(second, alternate);
        MatcherAssert.assertThat(
            "A subsequence passed despite having a different label.",
            new ContainedValuesTest(container, contained).passes(),
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
        final Values container = new ArrayOfValues(first, second, third);
        final Values contained = new ArrayOfValues(second, alternate);
        MatcherAssert.assertThat(
            "A subsequence passed despite having a different value.",
            new ContainedValuesTest(container, contained).passes(),
            is(false)
        );
    }

    @Test
    public void passesIfDuplicatesMatch() throws Exception {
        final LabelledValue first = new LabelledValue("first", "first value");
        final LabelledValue second = new LabelledValue("second", 2);
        final LabelledValue third = new LabelledValue("third", 1L);
        final LabelledValue fourth = new LabelledValue("fourth", false);
        final Values container = new ArrayOfValues(
            first, second, third, third, fourth
        );
        final Values contained = new ArrayOfValues(third, second, third);
        MatcherAssert.assertThat(
            "A sequence with the same duplicates didn't match as contained.",
            new ContainedValuesTest(container, contained).passes(),
            is(true)
        );
    }

    @Test
    public void doesntPassIfDuplicatesDontMatch() throws Exception {
        final LabelledValue first = new LabelledValue("first", "first value");
        final LabelledValue second = new LabelledValue(
                "second", new ArrayOfValues()
        );
        final LabelledValue third = new LabelledValue("third", true);
        final LabelledValue fourth = new LabelledValue("fourth", 1.0);
        final Values container = new ArrayOfValues(
            first, second, third, third, fourth
        );
        final Values contained = new ArrayOfValues(fourth, second, fourth);
        MatcherAssert.assertThat(
            "A sequence was contained in another with different duplicates.",
            new ContainedValuesTest(container, contained).passes(),
            is(false)
        );
    }
}
