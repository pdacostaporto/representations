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
package uy.kerri.representations;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import uy.kerri.representations.matchers.RepresentationMatchers;

/**
 * Tests for {@link uy.kerri.representations.FieldsFromJson}.
 *
 * @since 2.1.0
 */
public final class FieldsFromJsonTest {
    /**
     * FieldsFromJson contains all the fields of a JSON object string.
     *
     * @throws Exception if anything goes wrong.
     */
    @Test
    public void containsAllFields() throws Exception {
        MatcherAssert.assertThat(
            new FieldsFromJson(
                StringUtils.join(
                    "{",
                    "\"name\":\"Pablo\",",
                    "\"active\":true,",
                    "\"logged\":false,",
                    "\"postCount\":2,",
                    "\"ratio\":1.0,",
                    "\"id\":{\"code\":\"SAA\",\"number\":0},",
                    "\"posts\":[0,1,2]",
                    "}"
                )
            ),
            RepresentationMatchers.equalTo(
                new ArrayOfFields(
                    new LabelledValue("name", "Pablo"),
                    new LabelledValue("active", true),
                    new LabelledValue("logged", false),
                    new LabelledValue("postCount", 2L),
                    new LabelledValue("ratio", 1.0),
                    new LabelledValue(
                        "id",
                        new ArrayOfFields(
                            new LabelledValue("code", "SAA"),
                            new LabelledValue("number", 0L)
                        )
                    ),
                    new LabelledValue(
                        "posts",
                        new ArrayOfValues(
                            new LabelledValue("", 0L),
                            new LabelledValue("", 1L),
                            new LabelledValue("", 2L)
                        )
                    )
                )
            )
        );
    }
}
