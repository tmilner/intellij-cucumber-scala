package com.github.danielwegener.intellij.cucumber.scala

import com.intellij.find.findUsages.{ FindUsagesHandler, FindUsagesHandlerFactory }
import com.intellij.psi.PsiElement
import org.jetbrains.plugins.cucumber.psi.CucumberFileTypeFactory
import org.jetbrains.plugins.scala.lang.psi.api.base.ScLiteral

import scala.util.Try

class CucumberStepFindUsageHandlerFactory extends FindUsagesHandlerFactory {

  override def canFindUsages(element: PsiElement): Boolean = {
    false
  }

  override def createFindUsagesHandler(element: PsiElement, forHighlightUsages: Boolean): FindUsagesHandler = ???
}
