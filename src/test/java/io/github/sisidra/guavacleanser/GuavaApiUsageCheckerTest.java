/*-
 * -\-\-
 * Guava Cleanser
 * --
 * Copyright (C) 2016 - 2019 Spotify AB
 * --
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * -/-/-
 */

package io.github.sisidra.guavacleanser;

import com.google.errorprone.CompilationTestHelper;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for {@link GuavaApiUsageChecker}.
 *
 * @author Mārtiņš Kalvāns
 */
public class GuavaApiUsageCheckerTest {

  private CompilationTestHelper compilationHelper =
      CompilationTestHelper.newInstance(GuavaApiUsageChecker.class, getClass());

  @Test
  public void testEmptyClass() {
    compilationHelper.addSourceFile("GuavaApiUsageEmpty.java").doTest();
  }

  @Test
  public void testValidClass() {
    compilationHelper.addSourceFile("GuavaApiUsageValid.java").doTest();
  }

  @Test
  public void testGuavaFieldClass() {
    compilationHelper.addSourceFile("GuavaApiUsageGuavaField.java").doTest();
  }

  @Test
  public void testGuavaValueClass() {
    compilationHelper.addSourceFile("GuavaApiUsageGuavaValue.java").doTest();
  }

  @Ignore("TODO: Help!")
  @Test
  public void testGuavaGenericClass() {
    compilationHelper.addSourceFile("GuavaApiUsageGuavaGeneric.java").doTest();
  }

  @Test
  public void testGuavaGenericFieldClass() {
    compilationHelper.addSourceFile("GuavaApiUsageGuavaGenericField.java").doTest();
  }

  @Test
  public void testGuavaGenericValueClass() {
    compilationHelper.addSourceFile("GuavaApiUsageGuavaGenericValue.java").doTest();
  }

  @Test
  public void testGuavaParameterClass() {
    compilationHelper.addSourceFile("GuavaApiUsageGuavaParameter.java").doTest();
  }

  @Test
  public void testGuavaReturnClass() {
    compilationHelper.addSourceFile("GuavaApiUsageGuavaReturn.java").doTest();
  }
}
