# Representations

Representations serve as an interface for objects to communicate data indepently of their implementation details.
Their goal is to get rid of getters and setters and other techniques that expose the internal implementation of the objects they want to get the data from or force them to be structured on some specific way.

## Usage

### Representing data

A `Representation` is a media for representing data in key-value pairs.
Keys are strings and values are objects of the `Value` type.
It can be serialized by its `toString` method.

By the moment, only the class `JSONRepresentation` is implemented, which represents the data as a JSON string.

```
Representation json = new JSONRepresentation()
.with("name", new StringValue("Pablo Da Costa Porto"))
.with("age", new IntegerValue(25))
.with(
  "phoneNumbers",
  new ArrayValue(
    {
      new StringValue("598 815 56 59"),
      new StringValue("598 90 599 666")
    }
  )
).toString(); // {"name":"Pablo Da Costa Porto","age":25,"phoneNumbers":["598 815 56 59","598 90 599 666"]}
```

A `Value` encapsulates a value which can be serialized by its `toString` method.
It is used as the value of a field.
By the time, `IntegerValue`, `LongValue`, BooleanValue`, `StringValue` are the implemented options for basic data types and `ArrayValue` for arrays.
`Representation` is a subtype of `Value`.

```
String name = new StringValue("Pablo Da Costa Porto").toString(); // Pablo Da Costa Porto
String age = new IntegerValue(25).toString(); // 25
String phoneNumbers = new ArrayValue(
  {
    new StringValue("598 815 56 59"),
    new StringValue("598 90 599 666")
  }
).toString(); // ["598 815 56 59","598 90 599 666"]
String unemployed = new BooleanValue(true).toString(); // true
String fileSize = new LongValue(12345678910L).toString(); // 12345678910
String phoneNumber = new LongValue(12345678910L).toString(); // 12345678910
```

### Reading data

Sometimes you might want to access some specific field of a representation.
Some `Value` implementations can help you with that.

```
Representation json = new JSONRepresentation()
.with("name", new StringValue("Pablo Da Costa Porto"))
.with("age", new IntegerValue(25))
.with(
  "phoneNumbers",
  new ArrayValue(
    {
      new StringValue("598 815 56 59"),
      new StringValue("598 90 599 666")
    }
  )
);
String age = new IntegerValue(
  JSONFieldValue(json, "age")
).toString(); // "25"
String cellphone = new StringValue(
  new JSONArrayIndexValue(
    new JSONFieldValue(
      json,
      "phoneNumbers"
    ),
    1
  )
).toString(); // 598 90 599 666
```

## Customizing

You're encouraged to write your own implementations of the interfaces.
Don't hesitate to submit a PR if you think your custom implementation could be useful for someone else.

### Writing custom representation formats

You might want to have representations for some specific format that is useful to you.
For example:
```
String savings = new SavingsHTMLTable()
.with("January", new IntegerValue(1000))
.with("February", new IntegerValue(1200))
.with("March", new IntegerValue(950))
.with("April", new IntegerValue(1125))
.toString();
/*
  <table>
    <tr>
      <th>Month</th>
      <th>Savings</th>
    </tr>
    <tr>
      <td>January</td>
      <td>1000</td>
    </tr>
    <tr>
      <td>February</td>
      <td>1200</td>
    </tr>
    <tr>
      <td>March</td>
      <td>950</td>
    </tr>
    <tr>
      <td>April</td>
      <td>1125</td>
    </tr>
  </table>
*/
```

By the moment, `Representation` objects rely on some internal interfaces and classes.
The `MapUnformattedRepresentation` class encapsulates the key-value fields logic without outputting in any specific format.
Formatted representations decorate this class and make the unformatted representation and its values print themselves through the `printTo` method of the `Value` interface on an `Output` object which implements their desired format, so you can decorate an unformatted representation and implement the `Output` interface to make your custom formatted representation easily.

### Writing custom value formats

You might want to have some specific data types of formats to be supported as values.
For example:
```
String budget = new USDCurrencyValue(1500).toString(); // USD1500
String speed = new MPHValue(60).toString(); // 60mph
```

### Writing custom key-value field or printing logic

You can write your own implementation of the `Representation` interface from scratch if you're not happy with the existing field or printing logic.
