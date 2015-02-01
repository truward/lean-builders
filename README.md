Lean Builders
=============

## Why?

This small library provides support for so-called 'lean builder' template. The idea is simple: in order to make
writing builders simpler, it is assumed that the target classes will expose properties to the associated builders,
so those builders will be able to modify them (e.g. by having builder class defined in the hosting class scope so that builder class will have an access to the private fields of the target class).

Using this class gives the following benefits:
* simplifies builder definitions
 * implemented builder won't have to duplicate properties of the target class
 * target class doesn't need to implement a lengthy constructor that takes all the properties to initialize its fields
* allows to minimize builders memory footprint

## Sample usage

```java
import com.truward.builder.support.AbstractLeanBuilder;

public final class Person implements ValidationAware {
  private String name;
  private int age;

  /*package*/ Person() {
  }

  @Nonnull public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  @Override
  public void validate() {
    if (name == null) {
      throw new IllegalStateException("name can't be null");
    }

    if (age < 0) {
      throw new IllegalStateException("age can't be negative");
    }
  }

  //
  // Builder
  //

  public static final class Builder extends AbstractLeanBuilder<Person> {

    @Nonnull
    @Override
    protected Person newInstance() {
      return new Person();
    }

    @Nonnull public Builder setName(String value) {
      target().name = value;
      return this;
    }

    @Nonnull public Builder setAge(int value) {
      target().age = value;
      return this;
    }
  }
}
```

## Maven


In order to include this library to your project add the following to your ``pom.xml``:

```xml
<dependency>
  <groupId>com.truward.builder</groupId>
  <artifactId>lean-builders</artifactId>
  <version>1.0.1</version>
</dependency>
```



