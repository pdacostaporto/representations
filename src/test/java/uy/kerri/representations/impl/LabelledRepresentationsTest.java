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
package uy.kerri.representations.test.impl;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.Representations;
import uy.kerri.representations.fake.FakeOutput;
import uy.kerri.representations.impl.ArrayOfRepresentations;
import uy.kerri.representations.impl.LabelledRepresentations;
import uy.kerri.representations.impl.LabelledString;

/**
 * Tests for {@link uy.kerri.representations.impl.LabelledRepresentations}.
 *
 * @since 1.0
 */
public class LabelledRepresentationsTest {
    /**
     * LabelledRepresentations print themselves in an output.
     *
     * @throws Exception if something fails.
     */
    @Test
    public final void printThemselves() throws Exception {
        final String artist = "artis:String:Boards of Canada";
        final String label = "album";
        final Representations albums = new ArrayOfRepresentations(
            new LabelledString(label, "Music Has the Right to Children"),
            new LabelledString(label, "Geogaddi"),
            new LabelledString(label, "The Campfire Headphase"),
            new LabelledString(label, "Tomorrow's Harvest")
        );
        final FakeOutput output = new FakeOutput(artist);
        MatcherAssert.assertThat(
            "The values didn't print themselves correctly.",
            new LabelledRepresentations("albums", albums).print(output).show(),
            CoreMatchers.equalTo(
                StringUtils.join(
                    new String[] {
                        artist,
                        String.format(
                            "albums:Multivalued:%n*%s",
                            StringUtils.replace(
                                albums.print(new FakeOutput()).show(),
                                String.format("%n"),
                                String.format("%n*")
                            )
                        ),
                    },
                    String.format("%n")
                )
            )
        );
    }
}
