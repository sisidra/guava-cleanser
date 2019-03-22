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
