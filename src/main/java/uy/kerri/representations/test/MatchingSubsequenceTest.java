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
package uy.kerri.representations.test;

import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;

/**
 * A {@link uy.kerri.representations.test.Test} to verify that a sequence
 *  of values is a subsequence of another.
 *
 * @since 2.0
 */
public final class MatchingSubsequenceTest implements Test {
    /**
     * The place in the sequence where the subsequence should start.
     */
    private final Integer start;

    /**
     * The containing sequence.
     */
    private final Values sequence;

    /**
     * The subsequence.
     */
    private final Values subsequence;

    /**
     * Constructs a test to verify than a sequence of values is a subsequence of
     *  another.
     *
     * @param start The place in the sequence where the subsequence should
     *  start.
     * @param seq The sequence.
     * @param sub The subsequence.
     */
    public MatchingSubsequenceTest(
        final Integer start, final Values seq, final Values sub
    ) {
        this.start = start;
        this.sequence = seq;
        this.subsequence = sub;
    }

    @Override
    public Boolean passes() throws RepresentationsException {
        return Boolean.valueOf(
            this.subsequence.printTo(
                new SubsequenceMatchingOutput(this.start, this.sequence)
            ).show()
        );
    }
}
