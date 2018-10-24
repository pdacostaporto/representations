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

import javax.json.JsonObject;

/**
 * A field contained in a JSON object.
 *
 * @since 2.1.0
 */
final class FieldFromJson implements Field {
    /**
     * The name of the field.
     */
    private final String name;

    /**
     * The JSON object.
     */
    private final JsonObject json;

    /**
     * Constructs the field of given name in the JSON object.
     *
     * @param name The name of the field.
     * @param json The JSON object.
     */
    FieldFromJson(final String name, final JsonObject json) {
        this.name = name;
        this.json = json;
    }

    @Override
    public Output printTo(final Output output) throws RepresentationsException {
        final Output printed;
        switch (this.json.get(this.name).getValueType()) {
            case STRING:
                printed = output.print(
                    this.name, this.json.getString(this.name)
                );
                break;
            case NUMBER:
                printed = new LabelledJsonNumber(
                    this.name, this.json.getJsonNumber(this.name)
                ).printTo(output);
                break;
            case TRUE:
                printed = output.print(this.name, true);
                break;
            case FALSE:
                printed = output.print(this.name, false);
                break;
            case OBJECT:
                printed = output.print(
                    this.name,
                    new FieldsFromJson(
                        this.json.getJsonObject(this.name).toString()
                    )
                );
                break;
            case ARRAY:
                printed = output.print(
                    this.name,
                    new ValuesFromJson(
                        this.json.getJsonArray(this.name).toString()
                    )
                );
                break;
            default:
                throw new TypeNotSupportedException(
                    String.format(
                        "The type of the field '%s' is not supported.",
                        this.name
                    )
                );
        }
        return printed;
    }
}
