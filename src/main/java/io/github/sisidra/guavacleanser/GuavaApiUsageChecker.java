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

import static com.google.errorprone.BugPattern.Category.GUAVA;
import static com.google.errorprone.BugPattern.LinkType.CUSTOM;
import static com.google.errorprone.BugPattern.SeverityLevel.ERROR;
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
    category = GUAVA,
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
