package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.Internal
import eyrie.file.context.Sys
import eyrie.file.{FileName, FilePath}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object PotentialSuccessorSpec {
  val relative: FilePath.Relative[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  val absolute: FilePath.Absolute[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)

  val filePath: FilePath[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)
}

@RunWith(classOf[JUnitRunner])
class PotentialSuccessorSpec extends FreeSpec {
  import PotentialSuccessorSpec._
  import eyrie.syntax.potentialSuccessor._

  "RelativeFile" - {
    "parentOption should be Option of FilePath.Relative" in {
      relative.parentOption: Option[FilePath.Relative[Sys]]
    }
    "lastSegmentOption should be Option of FileName" in {
      relative.lastSegmentOption: Option[FileName[Sys]]
    }
  }
  "AbsoluteFile" - {
    "parentOption should be Option of FilePath.Absolute" in {
      absolute.parentOption: Option[FilePath.Absolute[Sys]]
    }
    "lastSegmentOption should be Option of FileName" in {
      absolute.lastSegmentOption: Option[FileName[Sys]]
    }
  }
}
