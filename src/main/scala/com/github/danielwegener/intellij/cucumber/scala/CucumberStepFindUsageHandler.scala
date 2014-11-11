package com.github.danielwegener.intellij.cucumber.scala

import com.intellij.find.findUsages.FindUsagesHandler
import com.intellij.psi.PsiElement
import org.jetbrains.plugins.scala.lang.psi.api.base.ScLiteral

class CucumberStepFindUsageHandler(psiElement: PsiElement) extends FindUsagesHandler(psiElement) {
  require(psiElement.isInstanceOf[ScLiteral], "can only find usages on ScLiterals")

  //TODO: implement

}
