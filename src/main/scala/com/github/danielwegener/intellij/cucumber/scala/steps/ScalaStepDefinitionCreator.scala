package com.github.danielwegener.intellij.cucumber.scala.steps

import com.intellij.openapi.project.Project
import com.intellij.psi.util.CreateClassUtil._
import com.intellij.psi.{ PsiManager, PsiClass, PsiDirectory, PsiFile }
import cucumber.runtime.scala.ScalaSnippetGenerator
import cucumber.runtime.snippets.SnippetGenerator
import gherkin.formatter.model.{ Comment, Step }
import org.jetbrains.annotations.NotNull
import org.jetbrains.plugins.cucumber.AbstractStepDefinitionCreator
import org.jetbrains.plugins.cucumber.psi.GherkinStep
import org.jetbrains.plugins.scala.lang.psi.api.ScalaFile
import org.jetbrains.plugins.scala.lang.psi.api.expr.ScMethodCall
import org.jetbrains.plugins.scala.lang.psi.api.toplevel.typedef.ScClass
import org.jetbrains.plugins.scala.lang.psi.impl.ScalaPsiElementFactory

class ScalaStepDefinitionCreator extends AbstractStepDefinitionCreator {

  final val STEP_DEFINITION_SUFFIX = "MyStepdefs"

  @NotNull
  @Override def createStepDefinitionContainer(@NotNull dir: PsiDirectory, @NotNull name: String): PsiFile = {
    val newClass: PsiClass = createClassNamed(name, DEFAULT_CLASS_TEMPLATE, dir)
    assert(newClass != null)
    newClass.getContainingFile
  }

  @NotNull
  @Override def getDefaultStepFileName(@NotNull step: GherkinStep): String = {
    STEP_DEFINITION_SUFFIX
  }

  override def validateNewStepDefinitionFileName(project: Project, filename: String): Boolean = !filename.isEmpty

  def createMethodCall(gherkinStep: GherkinStep, psiManager: PsiManager): ScMethodCall = {
    val cucumberStep = new Step(new java.util.ArrayList[Comment], gherkinStep.getKeyword.getText, gherkinStep.getStepName, 0, null, null)
    val snippet = new SnippetGenerator(new ScalaSnippetGenerator()).getSnippet(cucumberStep)
    ScalaPsiElementFactory.createExpressionFromText(snippet, psiManager).asInstanceOf[ScMethodCall]
  }

  override def createStepDefinition(gherkinStep: GherkinStep, psiFile: PsiFile): Boolean = {

    (for {
      scalaFile <- Some(psiFile).collect { case s: ScalaFile => s }.toList
      typeDefinitions <- scalaFile.typeDefinitions
      firstClass <- typeDefinitions.typeDefinitions.collectFirst { case c: ScClass => c }
      templateBody <- firstClass.extendsBlock.templateBody
      languageId = gherkinStep.getLanguage.getID
      importStatement = ScalaPsiElementFactory.createImportFromText(s"import cucumber.api.scala.{ScalaDsl, $languageId}", psiFile.getManager)
      methodCall = createMethodCall(gherkinStep, psiFile.getManager)
    } yield {
      scalaFile.addImport(importStatement)
      templateBody.add(createMethodCall(gherkinStep, psiFile.getManager))
      true
    }).headOption.getOrElse(false)

  }

}
