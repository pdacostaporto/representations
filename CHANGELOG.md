# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Fixed
- `JsonObjectOutput` and `JsonArrayOutput` are now reusable.

## [2.2.0]
### Added
- A `Test` to verify that a set of fields is contained in another.

## [2.1.1]
### Fixed
- `SelectedOutput` selects the last value printed on the field.

## [2.1.0]
### Added
- Representations from JSON strings.

## [2.0.0] - 2018-09-14
### Added
- Tests for matching certain conditions.
- Values and Fields selection.
- New interface for selecting single values.
- Hamcrest matchers.

### Changed
- Package organization.

## [1.2.0] - 2018-09-02
### Added
- `Values` and `Fields` are also `Value` subtypes.
- `ArrayOfFields` and `ArrayOfValues` now can take a `Collection` on their constructors.

## [1.1.1] - 2018-09-02
### Changed
- Field joining.
- Values concatenation.

## [1.0.0] - 2018-09-02
### Added
- Value selection.

### Changed
- New general design.

## [0.3] - 2018-01-02
### Added
- JSONRepresentation takes a JSON string as parameter.

## [0.2] - 2017-12-08
### Added
- Nested representations

## [0.1] - 2017-10-01
### Added
- Interfaces for representations and representable objects.
- Implementation of JSON representation.
- Fake implementation of the representation interface.

[Unreleased]: https://github.com/pdacostaporto/representations/compare/2.2.0...HEAD
[2.2.0]: https://github.com/pdacostaporto/representations/compare/2.1.1...2.2.0
[2.1.1]: https://github.com/pdacostaporto/representations/compare/2.1.0...2.1.1
[2.1.0]: https://github.com/pdacostaporto/representations/compare/2.0.0...2.1.0
[2.0.0]: https://github.com/pdacostaporto/representations/compare/1.2.0...2.0.0
[1.2.0]: https://github.com/pdacostaporto/representations/compare/1.1.1...1.2.0
[1.1.1]: https://github.com/pdacostaporto/representations/compare/1.0.0...1.1.1
[1.0.0]: https://github.com/pdacostaporto/representations/compare/0.3...1.0.0
[0.3]: https://github.com/pdacostaporto/representations/compare/0.2...0.3
[0.2]: https://github.com/pdacostaporto/representations/compare/0.1...0.2
