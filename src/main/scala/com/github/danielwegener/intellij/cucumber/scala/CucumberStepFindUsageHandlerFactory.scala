package com.github.danielwegener.intellij.cucumber.scala

import com.intellij.find.findUsages.{ FindUsagesHandler, FindUsagesHandlerFactory }
import com.intellij.psi.PsiElement
import org.jetbrains.plugins.scala.lang.psi.api.base.ScLiteral
import org.jetbrains.plugins.scala.lang.psi.api.expr.{ ScMethodCall, ScArgumentExprList }

class CucumberStepFindUsageHandlerFactory extends FindUsagesHandlerFactory {

  override def canFindUsages(element: PsiElement): Boolean = {
    val canFind = for {
      literal @ (_y: ScLiteral) <- Some(element)
      if literal.isString
      argumentList @ (_y: ScArgumentExprList) <- literal.parent
      methodCall @ (_y: ScMethodCall) <- argumentList.parent
    } yield true

    canFind.getOrElse(false)
  }

  override def createFindUsagesHandler(element: PsiElement, forHighlightUsages: Boolean): FindUsagesHandler = null
}
