package com.truward.builder;


import com.truward.builder.support.AbstractLeanBuilder;
import com.truward.builder.support.AbstractValidationAware;
import org.junit.Test;

import javax.annotation.Nonnull;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Tests for lean builder support classes.
 */
public final class LeanBuilderTest {

  @Test
  public void shouldCreateDifferentInstances() {
    // Given:
    final String n1 = "n1";
    final int a1 = 10;
    final String n2 = "n2";
    final int a2 = 20;

    final Person.Builder builder = new Person.Builder();

    // When:
    final Person p1 = builder.setName(n1).setAge(a1).build();
    final Person p2 = builder.setName(n2).setAge(a2).build();

    // Then:
    assertFalse(p1 == p2);

    assertEquals(n1, p1.getName());
    assertEquals(a1, p1.getAge());

    assertEquals(n2, p2.getName());
    assertEquals(a2, p2.getAge());
  }

  @Test(expected = IllegalStateException.class)
  public void shouldValidateProperties() {
    new Person.Builder().build();
  }
}


/** Tested domain class with associated builder. */
final class Person extends AbstractValidationAware {
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

  @Override
  public String toString() {
    return "{'@': 'Person'" +
        ", 'name': '" + getName() + '\'' +
        ", 'age': " + getAge() +
        '}';
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
