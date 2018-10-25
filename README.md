Representations
===============
[![Build Status](https://travis-ci.org/pdacostaporto/representations.svg?branch=master)](https://travis-ci.org/pdacostaporto/representations)
[![Coverage Status](https://coveralls.io/repos/github/pdacostaporto/representations/badge.svg)](https://coveralls.io/github/pdacostaporto/representations)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/uy.kerri.representations/representations/badge.svg)](https://maven-badges.herokuapp.com/maven-central/uy.kerri.representations/representations)

Representations serve as interfaces for objects to communicate data indepently of their implementation details. Their goal is to get rid of getters and setters and other techniques that expose the internal implementation of the objects we want to get data from or force them to be structured on some specific way.

## Concepts

A `Representation` is a media for communicating data. A `Representation` can be:
* a single `Value`.
* a single key-value pair (`Field`).
* a sequence of `Values` in a list or array manner.
* a set of `Fields` in a map manner.

Their principal feature is their ability to print themselves on an `Output`, which serves as a formatter for the `Representation`'s data as a string. For example, an `Output` implementation can format the data as a JSON string, while another implementation formats it as an XML document. This way, data can be manipulated in many ways just by changing the output implementation on which it is printed.

All objects in this library are immutable so they can be reused easily without unexpected side effects.

## Usage

### Representing

At the moment, `ArrayOfValues` and `ArrayOfFields` are the only implementations of `Values` and `Fields` available, respectively. They both take an array of `Value`s or `Field`s as a parameter for their constructors, which will be the data they will encapsulate.
`LabelledValue` is the main implementation of the `Value` and `Field` interfaces. Its constructor can take a primitive value of the types `Integer`, `String`, `Boolean`, `Double` or `Long`, or a `Fields` or `Values` instance to represent composite values. It also requires a string label which will represent the name of the represented `Field` or a label for the represented `Value` in a sequence of values.

```
import uy.kerri.representations.Fields;
import uy.kerri.representations.ArrayOfFields;
import uy.kerri.representations.ArrayOfValues;
import uy.kerri.representations.LabelledValue;

Fields person = new ArrayOfFields(
  LabelledValue("name", "Pablo Da Costa Porto"),
  LabelledValue("age", 25),
  LabelledValue(
    "address",
    new ArrayOfFields(
      new LabelledValue("line1", "18 de julio 999"),
      new LabelledValue("line2", "Apt. 101"),
      new LabelledValue("city", "Montevideo"),
      new LabelledValue("country", "Uruguay"),
      new LabelledValue("zip", 11200)
    ),
  LabelledValue(
    "phoneNumbers",
    new ArrayOfValues(
      new LabelledValue("598 815 56 59"),
      new LabelledValue("598 90 599 666")
    )
  )
);
```

### Printing

Once you constructed your representation, you can format it as a string through an `Output`. At the moment, the only implementations of this interface that do this are `JsonObjectOutput` and `JsonArrayOutput`, which format the data as a JSON object or a JSON array, respectively. This serialization is done through the `show` method.

```
import uy.kerri.representations.format.JsonObjectOutput;

String json = person.printTo(new JsonObjectOutput()).show(); // {"name":"Pablo Da Costa Porto","age":25,"address":{"lines":["18 de julio 999","Apt. 101"],"city":"Montevideo","country":"Uruguay","zip":11200},"phoneNumbers":["598 815 56 59","598 90 599 666"]}
```

### Selecting

You can also access specific values through `Selected<Type>Value` and `SelectedOutput` to select a specific field in a map or index in a sequence of values, respectively.
```
import uy.kerri.representations.select.SelectedIntegerValue;
import uy.kerri.representations.select.SelectedStringValue;
import uy.kerri.representations.select.SelectedOutput;

Integer age = new SelectedIntegerValue(
  person, new SelectedOutput("age")
).value(); // 25
String cellphone = new SelectedStringValue(
  person,
  new SelectedOutput(
    "phoneNumbers",
    SelectedOutput(2)
  )
).show(); // 598 90 599 666
```
To select composite values or multivalued fields is a bit different.
```
import uy.kerri.representations.select.SelectedFields;
import uy.kerri.representations.select.SelectedValues;

Fields address = new SelectedFields("address", person); // {"lines":["18 de julio 999","Apt. 101"],"city":"Montevideo","country":"Uruguay","zip":11200}
Values lines = new SelectedValues("lines", lines); // ["18 de julio 999","Apt. 101"]
```

You can also filter a set of fields.
```
import java.util.Arrays;
import uy.kerri.representations.select.FilteredFields;

Fields filtered = new FielteredFields(Arrays.asList("name", "age"), person); // {"name":"Pablo Da Costa Porto","age":25}
```

### Testing

You can test representations for some conditions through `Test` implementations.
```
import uy.kerri.representations.ArrayOfFields;
import uy.kerri.representations.ArrayOfValues;
import uy.kerri.representations.LabelledValue;
import uy.kerri.representations.test.MatchingFieldsTest;
import uy.kerri.representations.test.MatchingValuesTest;

LabelledValue first = new LabelledValue("first", 1);
LabelledValue second = new LabelledValue("second", "second value");
LabelledValue third = new LabelledValue("third", 3.0);
new MatchingValuesTest(
  new ArrayOfValues(first, second, third),
  new ArrayOfValues(first, second, third)
).passes(); // true
new MatchingFieldsTest(
  new ArrayOfFields(first, third),
  new ArrayOfFields(first, second)
).passes(); // false
```
Check the `uy.kerri.representations.test` package to see available tests.

There are also some [Hamcrest](http://hamcrest.org/JavaHamcrest/) matchers available.
```
import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import uy.kerri.representations.matchers.RepresentationMatchers;

MatcherAssert.assertThat(
  new ArrayOfValues(first, second, third),
  RepresentationMatchers.equalTo(new ArrayOfValues(first, second, third))
);
MatcherAssert.assertThat(
  new ArrayOfFields(first, third),
  CoreMatchers.not(
    RepresentationMatchers.equalTo(new ArrayOfValues(first, second))
  )
);
```
Check the `RepresentationMatchers` class to see available matchers.

## API documentation

You can read the documentation [here](https://javadoc.io/doc/uy.kerri.representations/representations).

## Installation

Add it as a dependency on your `pom.xml`, replacing `{$representations.version}` with the desired version.

```
<dependency>
  <groupId>uy.kerri.representations</groupId>
  <artifactId>representations</artifactId>
  <version>{$representations.version}</version>
</dependency>
```

This library is tested with the Java 8 JDK, but it should work with Java 7.

## How to contribute

Submit an issue if you find a bug or have an idea for a feature. You can also do it if you want to contribute to the code and something is blocking you.
You're also encouraged to make your own implementations of any of the interfaces and make a pull request if you think it can be useful for someone else.

### Configuration

This project requires the [OpenJDK 8](http://openjdk.java.net/projects/jdk8/) and [Maven](https://maven.apache.org/) and it's expected to be developed on a UNIX-like environment.

If you use [Vagrant](https://www.vagrantup.com/) you can automate the configuration process. Just run `vagrant up` at the root of this repository.

### Guidelines

The code of this library tries to respect the [Elegant Objects](https://www.elegantobjects.org/) principles, so check them out when contributing.

For every feature, bugfix or change you make, add a corresponding description of it in the [changelog](https://github.com/pdacostaporto/representations/blob/master/CHANGELOG.md) under a section called `[Unreleased]` at the top of it.

Besides that, just check that `mvn install` runs without issues before submiting the code.

### Releasing

To release a new version set the `RELEASE_VERSION` and `DEVELOPMENT_VERSION` environment variables in Travis and then push the code to a new branch called `release`. Travis will take care of the release automatically.

## Credits

The concepts behind this library were heavily influenced by [this post from Yegor Bugayenko's blog](https://www.yegor256.com/2016/04/05/printers-instead-of-getters.html).

## License

MIT License

Copyright (c) 2018 Pablo Da Costa Porto

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
