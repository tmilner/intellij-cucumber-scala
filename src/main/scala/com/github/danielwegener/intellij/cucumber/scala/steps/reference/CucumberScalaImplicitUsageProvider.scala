package com.github.danielwegener.intellij.cucumber.scala.steps.reference

import com.intellij.codeInsight.daemon.ImplicitUsageProvider
import com.intellij.psi.PsiElement
import org.jetbrains.plugins.scala.lang.psi.api.toplevel.typedef.ScClass

class CucumberScalaImplicitUsageProvider extends ImplicitUsageProvider {

  override def isImplicitUsage(element: PsiElement): Boolean = {
    element match {
      case scClass: ScClass => ???
      case _ => false
    }
  }

  override def isImplicitRead(element: PsiElement): Boolean = false

  override def isImplicitWrite(element: PsiElement): Boolean = false
}
