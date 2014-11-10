package com.github.danielwegener.intellij.cucumber.scala.steps.search

import com.intellij.openapi.application.QueryExecutorBase
import com.intellij.psi.PsiReference
import com.intellij.psi.search.searches.MethodReferencesSearch
import com.intellij.psi.search.searches.MethodReferencesSearch.SearchParameters
import com.intellij.util.Processor

class CucumberScalaMethodUsageSearcher extends QueryExecutorBase[PsiReference, MethodReferencesSearch.SearchParameters] {

  override def processQuery(queryParameters: SearchParameters, consumer: Processor[PsiReference]): Unit = ???

  /**
   * *
   *
   *
   * public void processQuery(@NotNull final MethodReferencesSearch.SearchParameters p, @NotNull final Processor<PsiReference> consumer) {
   * final PsiMethod method = p.getMethod();
   *
   * final String regexp = ApplicationManager.getApplication().runReadAction(new Computable<String>() {
   * @Override
   * public String compute() {
   * final PsiAnnotation stepAnnotation = CucumberJavaUtil.getCucumberStepAnnotation(method);
   * return stepAnnotation != null ? CucumberJavaUtil.getPatternFromStepDefinition(stepAnnotation) : null;
   * }
   * });
   *
   * if (regexp == null) {
   * return;
   * }
   *
   * final String word = CucumberUtil.getTheBiggestWordToSearchByIndex(regexp);
   * if (StringUtil.isEmpty(word)) {
   * return;
   * }
   *
   * if (p.getScope() instanceof GlobalSearchScope) {
   * GlobalSearchScope restrictedScope = GlobalSearchScope.getScopeRestrictedByFileTypes((GlobalSearchScope)p.getScope(), GherkinFileType.INSTANCE);
   * ReferencesSearch.search(new ReferencesSearch.SearchParameters(method, restrictedScope, false, p.getOptimizer())).forEach(consumer);
   * }
   *
   */

}
