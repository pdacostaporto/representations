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

/**
 * Represents the output in which represented data is printed.
 *
 * @since 1.0
 */
public interface Output {
    /**
     * Shows the output.
     *
     * @return A string representation of the output
     * @throws RepresentationsException if anything goes wrong
     */
    String show() throws RepresentationsException;

    /**
     * Prints a string field on the output.
     *
     * @param key The key of the printed field.
     * @param value The value of the printed field.
     * @return The output with the field printed on it.
     * @throws RepresentationsException if anything goes wrong.
     */
    Output print(String key, String value) throws RepresentationsException;

    /**
     * Prints an integer field on the output.
     *
     * @param key The key of the printed field.
     * @param value The value of the printed field.
     * @return The output with the field printed on it.
     * @throws RepresentationsException if anything goes wrong.
     */
    Output print(String key, Integer value) throws RepresentationsException;

    /**
     * Prints a boolean field on the output.
     *
     * @param key The key of the printed field.
     * @param value The value of the printed field.
     * @return The output with the field printed on it.
     * @throws RepresentationsException if anything goes wrong.
     */
    Output print(String key, Boolean value) throws RepresentationsException;

    /**
     * Prints a double field on the output.
     *
     * @param key The key of the printed field.
     * @param value The value of the printed field.
     * @return The output with the field printed on it.
     * @throws RepresentationsException if anything goes wrong.
     */
    Output print(String key, Double value) throws RepresentationsException;

    /**
     * Prints a long field on the output.
     *
     * @param key The key of the printed field.
     * @param value The value of the printed field.
     * @return The output with the field printed on it.
     * @throws RepresentationsException if anything goes wrong.
     */
    Output print(String key, Long value) throws RepresentationsException;

    /**
     * Prints nested fields on the output.
     *
     * @param key The key of the printed field.
     * @param value The nested fields of the printed field.
     * @return The output with the nested fields printed on it.
     * @throws RepresentationsException if anything goes wrong.
     */
    Output print(String key, Fields value) throws RepresentationsException;

    /**
     * Prints a multivalued field on the output.
     *
     * @param key The key of the printed field.
     * @param values The values of the printed field.
     * @return The output with the field printed on it.
     * @throws RepresentationsException if anything goes wrong.
     */
    Output print(String key, Values values) throws RepresentationsException;
}
