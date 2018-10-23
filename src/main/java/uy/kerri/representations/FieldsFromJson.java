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

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;

/**
 * A group of fields from a JSON object.
 *
 * @since 2.1.0
 */
public final class FieldsFromJson implements Fields {
    /**
     * The JSON object string.
     */
    private final String json;

    /**
     * Constructs a group of fields from a JSON string.
     *
     * @param json A JSON object string.
     */
    public FieldsFromJson(final String json) {
        this.json = json;
    }

    @Override
    public Output printTo(final Output output) throws RepresentationsException {
        Output printed = output;
        final JsonObject object = Json.createReader(new StringReader(this.json))
            .readObject();
        for (final String key : object.keySet()) {
            printed = new FieldFromJson(key, object).printTo(printed);
        }
        return printed;
    }
}
