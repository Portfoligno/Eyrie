package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.Internal
import eyrie.file.context.Sys
import eyrie.file.{FileName, FilePath}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object DiSuccessorSpec {
  val nonEmpty: FilePath.NonEmpty[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)
}

@RunWith(classOf[JUnitRunner])
class DiSuccessorSpec extends FreeSpec {
  import DiSuccessorSpec._
  import eyrie.syntax.diSuccessor._

  "FilePath.NonEmpty" - {
    "parentEither should be either FilePath.Absolute or FilePath.Relative" in {
      nonEmpty.parentEither: Either[FilePath.Absolute[Sys], FilePath.Relative[Sys]]
    }
    "lastSegment should be FileName" in {
      nonEmpty.lastSegment: FileName[Sys]
    }
  }
}
