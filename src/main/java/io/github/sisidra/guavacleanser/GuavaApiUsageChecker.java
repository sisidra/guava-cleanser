package io.github.sisidra.guavacleanser;

import static com.google.errorprone.BugPattern.LinkType.CUSTOM;
import static com.google.errorprone.BugPattern.SeverityLevel.ERROR;
import static com.google.errorprone.BugPattern.StandardTags.FRAGILE_CODE;
import static com.google.errorprone.BugPattern.StandardTags.REFACTORING;
import static com.google.errorprone.matchers.Description.NO_MATCH;

import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.IdentifierTreeMatcher;
import com.google.errorprone.bugpatterns.BugChecker.MemberSelectTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.util.ASTHelpers;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.code.Symbol;
import java.net.URI;
import javax.tools.JavaFileObject;

/**
 * Checks for usages of Guava APIs, which should never be used. You decide exceptions tho.
 *
 * @author Mārtiņš Kalvāns
 */
@BugPattern(
    name = "guava",
    summary = "Guava APIs should not be used in the code as it is a dependency nightmare.",
    explanation =
        "In latest Java versions most convenience utilities found in Guava "
            + "are already in stdlib. OR SHADE IT!",
    linkType = CUSTOM,
    link = "http://lmgtfy.com/?q=methodnotfoundexception+guava",
    tags = {FRAGILE_CODE, REFACTORING},
    severity = ERROR)
public final class GuavaApiUsageChecker extends BugChecker
    implements MemberSelectTreeMatcher, IdentifierTreeMatcher {

  private static final long serialVersionUID = -2388529872915988126L;

  @Override
  public final Description matchMemberSelect(MemberSelectTree tree, VisitorState state) {
    return matchTree(tree);
  }

  @Override
  public final Description matchIdentifier(IdentifierTree tree, VisitorState state) {
    return matchTree(tree);
  }

  private Description matchTree(Tree tree) {
    Symbol symbol = ASTHelpers.getSymbol(tree);
    if (symbol instanceof Symbol.ClassSymbol) {
      JavaFileObject classfile = ((Symbol.ClassSymbol) symbol).classfile;
      if (classfile != null) {
        URI classUri = classfile.toUri();
        if ("jar".equals(classUri.getScheme())
            && classUri.getRawSchemeSpecificPart().contains("/guava-")) {
          return describeMatch(tree);
        }
      }
    }
    return NO_MATCH;
  }
}
