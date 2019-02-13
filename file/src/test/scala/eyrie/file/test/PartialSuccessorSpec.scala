package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.Internal
import eyrie.file.context.Sys
import eyrie.file.{FileName, FilePath}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object PartialSuccessorSpec {
  val relative: FilePath.Relative[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  val absolute: FilePath.Absolute[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)

  val filePath: FilePath[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)
}

@RunWith(classOf[JUnitRunner])
class PartialSuccessorSpec extends FreeSpec {
  import PartialSuccessorSpec._
  import eyrie.syntax.partialSuccessor._

  "FilePath.Relative" - {
    "parentOption should be Option of FilePath.Relative" in {
      relative.parentOption: Option[FilePath.Relative[Sys]]
    }
    "lastSegmentOption should be Option of FileName" in {
      relative.lastSegmentOption: Option[FileName[Sys]]
    }
  }
  "FilePath.Absolute" - {
    "parentOption should be Option of FilePath.Absolute" in {
      absolute.parentOption: Option[FilePath.Absolute[Sys]]
    }
    "lastSegmentOption should be Option of FileName" in {
      absolute.lastSegmentOption: Option[FileName[Sys]]
    }
  }
  "FilePath" - {
    "parentOption should be Option of FilePath" in {
      filePath.parentOption: Option[FilePath[Sys]]
    }
    "lastSegmentOption should be Option of FileName" in {
      filePath.lastSegmentOption: Option[FileName[Sys]]
    }
  }
}
