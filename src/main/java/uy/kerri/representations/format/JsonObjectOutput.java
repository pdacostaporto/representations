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
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import uy.kerri.representations.Fields;
import uy.kerri.representations.Output;
import uy.kerri.representations.RepresentationsException;
import uy.kerri.representations.Values;

/**
 * An {@link uy.kerri.representations.Output} in JSON object format.
 *
 * @since 2.0
 */
public class JsonObjectOutput implements Output {
    /**
     * Reader for the object's JSON input stream.
     */
    private JsonReader reader;

    /**
     * Constructs a JSON object read from an input stream.
     *
     * @param rdr The reader of the input stream containing a JSON object.
     */
    private JsonObjectOutput(final JsonReader rdr) {
        this.reader = rdr;
    }

    /**
     * Constructs a JSON object from a string.
     *
     * @param json A JSON object formatted as a string.
     */
    public JsonObjectOutput(final String json) {
        this(Json.createReader(new StringReader(json)));
    }

    /**
     * Constructs an empty JSON object.
     */
    public JsonObjectOutput() {
        this("{}");
    }

    @Override
    public final String show() throws RepresentationsException {
        return this.reader.readObject().toString();
    }

    @Override
    public final JsonObjectOutput print(
        final String key,
        final String value
    ) throws RepresentationsException {
        return new JsonObjectOutput(
            this.builder().add(key, value).build().toString()
        );
    }

    @Override
    public final JsonObjectOutput print(
        final String key,
        final Integer value
    ) throws RepresentationsException {
        return new JsonObjectOutput(
            this.builder().add(key, value).build().toString()
        );
    }

    @Override
    public final JsonObjectOutput print(
        final String key,
        final Boolean value
    ) throws RepresentationsException {
        return new JsonObjectOutput(
            this.builder().add(key, value).build().toString()
        );
    }

    @Override
    public final JsonObjectOutput print(
        final String key,
        final Double value
    ) throws RepresentationsException {
        return new JsonObjectOutput(
            this.builder().add(key, value).build().toString()
        );
    }

    @Override
    public final JsonObjectOutput print(
        final String key,
        final Long value
    ) throws RepresentationsException {
        return new JsonObjectOutput(
            this.builder().add(key, value).build().toString()
        );
    }

    @Override
    public final JsonObjectOutput print(
        final String key,
        final Fields value
    ) throws RepresentationsException {
        return new JsonObjectOutput(
            this.builder().add(
                key,
                Json.createReader(
                    new StringReader(
                        value.printTo(new JsonObjectOutput()).show()
                    )
                ).readObject()
            ).build().toString()
        );
    }

    @Override
    public final JsonObjectOutput print(
        final String key,
        final Values values
    ) throws RepresentationsException {
        return new JsonObjectOutput(
            this.builder().add(
                key,
                Json.createReader(
                    new StringReader(
                        values.printTo(new JsonArrayOutput()).show()
                    )
                ).readArray()
            ).build().toString()
        );
    }

    /**
     * Creates a JSON object builder initialized to the current state.
     *
     * @return The builder.
     * @throws RepresentationsException if anything goes wrong.
     */
    private JsonObjectBuilder builder() throws RepresentationsException {
        return Json.createObjectBuilder(this.reader.readObject());
    }
}
