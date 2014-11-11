package com.github.danielwegener.intellij.cucumber.scala

import com.intellij.find.findUsages.{ FindUsagesHandler, FindUsagesHandlerFactory }
import com.intellij.psi.PsiElement
import org.jetbrains.plugins.scala.lang.psi.api.base.ScLiteral
import org.jetbrains.plugins.scala.lang.psi.api.expr.{ ScMethodCall, ScArgumentExprList }

class CucumberStepFindUsageHandlerFactory extends FindUsagesHandlerFactory {

  override def canFindUsages(element: PsiElement): Boolean = {
    val canFind = for {
     elemn <- Some(element)
     literal: ScLiteral <- elemn
     if literal.isString
     argumentList: ScArgumentExprList <- literal.parent
     methodCall: ScMethodCall <- literal.parent

    } yield true

    canFind.getOrElse(false)
  }

  override def createFindUsagesHandler(element: PsiElement, forHighlightUsages: Boolean): FindUsagesHandler = null
}
