# Guava Cleanser

An [Error Prone] plugin that checks for usages of Guava APIs.

This is my personal vendetta against Guava as I have fought a bunch too
many times a dependency hell Guava has induced (especially in Hadoop
ecosystem).

This plugin was created after inspiration from [Guava Beta Checker].

Example error:

```
src/main/java/foo/MyClass.java:14: error: [guava] Guava APIs should not be used in the code as it is a dependency nightmare.
    (see http://lmgtfy.com/?q=methodnotfoundexception+guava)
```

## Usage

Using the Guava Cleanser requires configuring your project to build with
the Error Prone Java compiler. By default, this enables a lot of useful
checks for a variety of common bugs. However, if you just want to use
the Guava Cleanser, the other checks can be disabled.

## Related projects

You might want to checkout [Modernizer Maven Plugin] as well.

[Error Prone]: https://github.com/google/error-prone
[Guava Beta Checker]: https://github.com/google/guava-beta-checker
[Modernizer Maven Plugin]: https://github.com/gaul/modernizer-maven-plugin/
