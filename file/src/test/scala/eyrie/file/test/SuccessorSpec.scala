package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.Internal
import eyrie.file.context.Sys
import eyrie.file.{AbsoluteFile, FileName, FilePath, RelativeFile}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object SuccessorSpec {
  val relativeFile: RelativeFile[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  val absoluteFile: AbsoluteFile[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)

  val nonEmpty: FilePath.NonEmpty[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)
}

@RunWith(classOf[JUnitRunner])
class SuccessorSpec extends FreeSpec {
  import SuccessorSpec._
  import eyrie.syntax.successor._

  "RelativeFile" - {
    "parent should be FilePath.Relative" in {
      relativeFile.parent: FilePath.Relative[Sys]
    }
    "lastSegment should be FileName" in {
      relativeFile.lastSegment: FileName[Sys]
    }
  }
  "AbsoluteFile" - {
    "parent should be FilePath.Absolute" in {
      absoluteFile.parent: FilePath.Absolute[Sys]
    }
    "lastSegment should be FileName" in {
      absoluteFile.lastSegment: FileName[Sys]
    }
  }
}
