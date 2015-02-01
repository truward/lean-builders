package com.truward.builder;

/**
 * Interface contract for domain objects that can be validated.
 *
 * @author Alexander Shabanov
 */
public interface ValidationAware {

  /**
   * Triggers internal validation.
   *
   * @throws java.lang.IllegalStateException if internal state is not valid
   */
  void validate() throws IllegalStateException;
}
