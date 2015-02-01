package com.truward.builder.support;

import com.truward.builder.LeanBuilder;
import com.truward.builder.ValidationAware;

import javax.annotation.Nonnull;

/**
 * Abstract base for lean builders. Each builder holds an instance of the constructed class, it is assumed
 * that builder that inherits this class will have an access to the mutable members of the created instance.
 *
 * This base class is not thread safe.
 *
 * @author Alexander Shabanov
 */
public abstract class AbstractLeanBuilder<T extends ValidationAware> implements LeanBuilder<T> {
  private T instance;

  @Nonnull
  @Override
  public final T build() {
    checkInstanceExists();

    final T instance = this.instance;
    this.instance = null;

    if (instance == null) {
      throw new IllegalStateException(); // unlikely
    }

    instance.validate();
    return instance;
  }

  //
  // Protected
  //

  /**
   * @return Current instance, which is constructed by this builder.
   */
  @Nonnull
  protected final T target() {
    checkInstanceExists();
    return instance;
  }

  /**
   * @return Newly created, uninitialized instance of the target class.
   */
  @Nonnull
  protected abstract T newInstance();

  //
  // Private
  //

  private void checkInstanceExists() {
    if (instance == null) {
      instance = newInstance();
    }
  }
}
