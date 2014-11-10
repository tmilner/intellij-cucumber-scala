package com.github.danielwegener.intellij.cucumber.scala.steps.search

import com.intellij.psi.PsiReference
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.search.searches.ReferencesSearch.SearchParameters
import com.intellij.util.{ Processor, QueryExecutor }

class CucumberScalaStepDefinitionSearch extends QueryExecutor[PsiReference, ReferencesSearch.SearchParameters] {

  override def execute(queryParameters: SearchParameters, consumer: Processor[PsiReference]): Boolean = false

  /**
   *  public boolean execute(@NotNull final ReferencesSearch.SearchParameters queryParameters,
   * @NotNull final Processor<PsiReference> consumer) {
   * final PsiElement myElement = queryParameters.getElementToSearch();
   * if (!(myElement instanceof PsiMethod)) {
   * return true;
   * }
   * final PsiMethod method = (PsiMethod)myElement;
   * Boolean isStepDefinition = ApplicationManager.getApplication().runReadAction(new Computable<Boolean>() {
   * @Override
   * public Boolean compute() {
   * return CucumberJavaUtil.isStepDefinition(method);
   * }
   * });
   * if (!isStepDefinition) {
   * return true;
   * }
   *
   * final PsiAnnotation stepAnnotation = ApplicationManager.getApplication().runReadAction(new Computable<PsiAnnotation>() {
   * @Override
   * public PsiAnnotation compute() {
   * return CucumberJavaUtil.getCucumberStepAnnotation(method);
   * }
   * });
   *
   * final String regexp = CucumberJavaUtil.getPatternFromStepDefinition(stepAnnotation);
   * if (regexp == null) {
   * return true;
   * }
   * return CucumberUtil.findGherkinReferencesToElement(myElement, regexp, consumer, queryParameters.getEffectiveSearchScope());
   * }
   */

}
