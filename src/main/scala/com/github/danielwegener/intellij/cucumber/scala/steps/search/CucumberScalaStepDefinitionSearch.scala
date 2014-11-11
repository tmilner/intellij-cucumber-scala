package com.github.danielwegener.intellij.cucumber.scala.steps.search

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.util.Computable
import com.intellij.psi.{PsiMethod, PsiReference}
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.search.searches.ReferencesSearch.SearchParameters
import com.intellij.util.{ Processor, QueryExecutor }
import org.jetbrains.plugins.cucumber.java.CucumberJavaUtil

object CucumberScalaStepDefinitionSearch {
  val LOG = Logger.getInstance(classOf[CucumberScalaStepDefinitionSearch])
}

class CucumberScalaStepDefinitionSearch extends QueryExecutor[PsiReference, ReferencesSearch.SearchParameters] {

  override def execute(queryParameters: SearchParameters, consumer: Processor[PsiReference]): Boolean = {
    val myElement = queryParameters.getElementToSearch

    if (!myElement.isInstanceOf[PsiMethod]) {
      return true
    }

    val method: PsiMethod = myElement.asInstanceOf[PsiMethod]


    val isStepDefinition: Boolean = ApplicationManager.getApplication.runReadAction(new Computable[Boolean] {
      def compute: Boolean = {
        CucumberJavaUtil.isStepDefinition(method)
      }
    })


    if (!isStepDefinition) {
      return true
    }

    CucumberScalaStepDefinitionSearch.LOG.warn(s"execute($queryParameters, $consumer), elementToSearch=$myElement")
    true
  }

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
