package com.truward.builder;

import javax.annotation.Nonnull;

/**
 * Represents an interface contract for builders.
 *
 * @param <T> Type which is constructed by builder
 *
 * @author Alexander Shabanov
 */
public interface LeanBuilder<T> {

  /**
   * @return Constructed instance
   */
  @Nonnull
  T build();
}
