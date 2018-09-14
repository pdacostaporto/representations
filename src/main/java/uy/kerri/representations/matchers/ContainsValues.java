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

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;
import uy.kerri.representations.fake.FakeOutput;
import uy.kerri.representations.test.ContainedValuesTest;

/**
 * Hamcrest matcher to match a group of values contained on another.
 *
 * @since 2.0
 */
final class ContainsValues extends TypeSafeMatcher<Values> {
    /**
     * The containing group of values.
     */
    private final Values contained;

    /**
     * Constructs a matcher to test if a group of values contains another group.
     *
     * @param contained The contained group.
     */
    ContainsValues(final Values contained) {
        super();
        this.contained = contained;
    }

    @Override
    public void describeTo(final Description description) {
        try {
            description.appendText("a group of values containing ").appendValue(
                this.contained.printTo(new FakeOutput()).show()
            );
        } catch (final RepresentationsException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    @Override
    protected void describeMismatchSafely(
        final Values containing, final Description description
    ) {
        try {
            description.appendText("the containing group was ").appendValue(
                containing.printTo(new FakeOutput()).show()
            );
        } catch (final RepresentationsException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    @Override
    protected boolean matchesSafely(final Values containing) {
        try {
            return new ContainedValuesTest(containing, this.contained).passes();
        } catch (final RepresentationsException exception) {
            throw new IllegalArgumentException(exception);
        }
    }
}
