Representations
===============
[![Build Status](https://travis-ci.org/pdacostaporto/representations.svg?branch=master)](https://travis-ci.org/pdacostaporto/representations)
[![Coverage Status](https://coveralls.io/repos/github/pdacostaporto/representations/badge.svg)](https://coveralls.io/github/pdacostaporto/representations)
[![Maintainability](https://api.codeclimate.com/v1/badges/0bae9ee741ef9170b432/maintainability)](https://codeclimate.com/github/pdacostaporto/representations/maintainability)

Representations serve as interfaces for objects to communicate data indepently of their implementation details. Their goal is to get rid of getters and setters and other techniques that expose the internal implementation of the objects we want to get data from or force them to be structured on some specific way.

## Concepts

A `Representation` is a media for communicating data. A `Representation` can be:
* a single `Value`.
* a single key-value pair (`Field`).
* a sequence of `Values` in a list or array manner.
* a set of `Fields` in a map manner.

Their principal feature is their ability to print themselves on an `Output`, which serves as a formatter for the `Representation`'s data as a string. For example, an `Output` implementation can format the data as a JSON string, while another implementation formats it as an XML document. This way, data can be manipulated in many ways just by changing the output implementation on which it is printed.

## Usage

At the moment, `ArrayOfValues` and `ArrayOfFields` are the only implementations of `Values` and `Fields` available, respectively. They both take an array of `Value`s or `Field`s as a parameter for their constructors, which will be the data they will encapsulate.
`LabelledValue` is the main implementation of the `Value` and `Field` interfaces. Its constructor can take a primitive value of the types `Integer`, `String`, `Boolean`, `Double` or `Long`, or a `Fields` or `Values` instance to represent composite values. It also requires a string label which will represent the name of the represented `Field` or a label for the represented `Value` in a sequence of values.

```
import uy.kerri.representations.Fields;
import uy.kerri.representations.impl.ArrayOfFields;
import uy.kerri.representations.impl.ArrayOfValues;
import uy.kerri.representations.impl.LabelledValue;

Fields person = new ArrayOfFields(
  LabelledValue("name", "Pablo Da Costa Porto"),
  LabelledValue("age", 25),
  LabelledValue(
    "phoneNumbers",
    new ArrayOfValues(
      new LabelledValue("598 815 56 59"),
      new LabelledValue("598 90 599 666")
    )
  )
);
```

Once you constructed your representation, you can format it as a string through an `Output`. At the moment, the only implementations of this interface that do this are `JsonObjectOutput` and `JsonArrayOutput`, which format the data as a JSON object or a JSON array, respectively. This serialization is done through the `show` method.

```
import uy.kerri.representations.impl.JsonObjectOutput;

String json = person.printTo(new JsonObjectOutput()).show(); // {"name":"Pablo Da Costa Porto","age":25,"phoneNumbers":["598 815 56 59","598 90 599 666"]}
```

You can also access specific values through `SelectedFieldOutput` and `SelectedIndexOutput` to select a specific field in a map or index in a sequence of values, respectively.

```
String age = person.printTo(new SelectedFieldOutput("age")).show(); // 25
String cellphone = person.printTo(
  new SelectedFieldOutput(
    "phoneNumbers",
    SelectedIndexOutput(2)
  )
).show(); // 598 90 599 666
```

All objects in this library are immutable so they can be reused easily without unexpected side effects.

## Installation

Add it as a dependency on your `pom.xml`.

```
<dependency>
  <groupId>uy.kerri.representations</groupId>
  <artifactId>representations</artifactId>
  <version>1.0</version>
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

Besides that, just check that `mvn install` runs without issues before submiting the code.

### Releasing

To release a new version just run the `release.sh` script and fill the prompted information.

Remember to configure your [OSSRH Nexus](https://oss.sonatype.org/) credentials and your GPG passphrase on your `settings.xml` file. If you use Vagrant edit the file at `/home/vagrant/.m2/settings.xml`.

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
