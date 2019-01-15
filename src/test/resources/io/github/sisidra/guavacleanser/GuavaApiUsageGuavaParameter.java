package io.github.sisidra.guavacleanser;

import com.google.common.collect.*;

public class GuavaApiUsageGuavaParameter {

  // BUG: Diagnostic contains: guava
  public static void something(ImmutableList<String> parameter) {
  }

}
