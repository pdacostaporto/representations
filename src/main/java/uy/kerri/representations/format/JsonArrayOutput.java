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
package uy.kerri.representations.format;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;

/**
 * An {@link uy.kerri.representations.Output} in JSON array format.
 *
 * Labels are ignored.
 *
 * @since 2.0
 */
public class JsonArrayOutput implements Output {
    /**
     * The JSON array.
     */
    private JsonArray array;

    /**
     * Constructs a JSON array.
     *
     * @param array The JSON array.
     */
    private JsonArrayOutput(final JsonArray array) {
        this.array = array;
    }

    /**
     * Constructs a JSON array from a string.
     *
     * @param json A JSON array formatted string.
     */
    public JsonArrayOutput(final String json) {
        this(Json.createReader(new StringReader(json)).readArray());
    }

    /**
     * Constructs an empty JSON array.
     */
    public JsonArrayOutput() {
        this("[]");
    }

    @Override
    public final String show() throws RepresentationsException {
        return this.array.toString();
    }

    @Override
    public final JsonArrayOutput print(
        final String label,
        final String value
    ) throws RepresentationsException {
        return new JsonArrayOutput(
            this.builder().add(value).build().toString()
        );
    }

    @Override
    public final JsonArrayOutput print(
        final String label,
        final Integer value
    ) throws RepresentationsException {
        return new JsonArrayOutput(
            this.builder().add(value).build().toString()
        );
    }

    @Override
    public final JsonArrayOutput print(
        final String label,
        final Boolean value
    ) throws RepresentationsException {
        return new JsonArrayOutput(
            this.builder().add(value).build().toString()
        );
    }

    @Override
    public final JsonArrayOutput print(
        final String label,
        final Double value
    ) throws RepresentationsException {
        return new JsonArrayOutput(
            this.builder().add(value).build().toString()
        );
    }

    @Override
    public final JsonArrayOutput print(
        final String label,
        final Long value
    ) throws RepresentationsException {
        return new JsonArrayOutput(
            this.builder().add(value).build().toString()
        );
    }

    @Override
    public final JsonArrayOutput print(
        final String label,
        final Fields value
    ) throws RepresentationsException {
        return new JsonArrayOutput(
            this.builder().add(
                Json.createReader(
                    new StringReader(
                        value.printTo(new JsonObjectOutput()).show()
                    )
                ).readObject()
            ).build().toString()
        );
    }

    @Override
    public final JsonArrayOutput print(
        final String label,
        final Values values
    ) throws RepresentationsException {
        return new JsonArrayOutput(
            this.builder().add(
                Json.createReader(
                    new StringReader(
                        values.printTo(new JsonArrayOutput()).show()
                    )
                ).readArray()
            ).build().toString()
        );
    }

    /**
     * Creates a JSON array builder initialized to the current state.
     *
     * @return The builder.
     * @throws RepresentationsException if anything goes wrong.
     */
    private JsonArrayBuilder builder() throws RepresentationsException {
        return Json.createArrayBuilder(this.array);
    }
}
