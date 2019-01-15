package io.github.sisidra.guavacleanser;

import com.google.common.collect.*;

public class GuavaApiUsageGuavaReturn {

  // BUG: Diagnostic contains: guava
  public static ImmutableList<String> something() {
    return null;
  }

}
