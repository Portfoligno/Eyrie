package eyrie.nio.test

import java.nio.file.Paths

import eyrie.nio.FilePath.Internal
import eyrie.nio.context.Sys
import eyrie.nio.{FileName, FilePath}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object DiPartialSuccessorSpec {
  val filePath: FilePath[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)
}

@RunWith(classOf[JUnitRunner])
class DiPartialSuccessorSpec extends FreeSpec {
  import DiPartialSuccessorSpec._
  import eyrie.syntax.diPartialSuccessor._

  "FilePath" - {
    "parentEitherOption should be Option of either FilePath.Absolute or FilePath.Relative" in {
      filePath.parentEitherOption: Option[Either[FilePath.Absolute[Sys], FilePath.Relative[Sys]]]
    }
    "lastSegmentOption should be Option of FileName" in {
      filePath.lastSegmentOption: Option[FileName[Sys]]
    }
  }
}
