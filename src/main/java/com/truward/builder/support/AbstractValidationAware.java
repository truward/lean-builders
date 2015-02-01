package com.truward.builder.support;

import com.truward.builder.ValidationAware;

/**
 * Abstract base for validation aware classes.
 * Implemented {@link #validate()} method does nothing.
 *
 * @author Alexander Shabanov
 */
public abstract class AbstractValidationAware implements ValidationAware {

  @Override
  public void validate() throws IllegalStateException {
    // do nothing
  }
}
