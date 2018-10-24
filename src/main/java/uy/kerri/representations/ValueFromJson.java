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

import javax.json.JsonArray;

/**
 * A value contained in a JSON array.
 *
 * @since 2.1.0
 */
final class ValueFromJson implements Value {
    /**
     * The index of the value.
     */
    private final Integer index;

    /**
     * The JSON array.
     */
    private final JsonArray json;

    /**
     * Constructs the value on a given index in the JSON array.
     *
     * @param index The index of the value.
     * @param json The JSON array.
     */
    ValueFromJson(final Integer index, final JsonArray json) {
        this.index = index;
        this.json = json;
    }

    @Override
    public Output printTo(final Output output) throws RepresentationsException {
        final Output printed;
        switch (this.json.get(this.index).getValueType()) {
            case STRING:
                printed = output.print("", this.json.getString(this.index));
                break;
            case NUMBER:
                printed = new LabelledJsonNumber(
                    "", this.json.getJsonNumber(this.index)
                ).printTo(output);
                break;
            case TRUE:
                printed = output.print("", true);
                break;
            case FALSE:
                printed = output.print("", false);
                break;
            case OBJECT:
                printed = output.print(
                    "",
                    new FieldsFromJson(
                        this.json.getJsonObject(this.index).toString()
                    )
                );
                break;
            case ARRAY:
                printed = output.print(
                    "",
                    new ValuesFromJson(
                        this.json.getJsonArray(this.index).toString()
                    )
                );
                break;
            default:
                throw new TypeNotSupportedException(
                    String.format(
                        "The type of the index '%d' is not supported.",
                        this.index
                    )
                );
        }
        return printed;
    }
}
