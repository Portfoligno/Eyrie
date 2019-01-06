package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.Internal
import eyrie.file.context.Local
import eyrie.file.{FileName, FilePath}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object PotentialSuccessorSpec {
  val relative: FilePath.Relative[Local] =
    Internal.RelativeFile(Paths.get("a/b/c")).asInstanceOf[FilePath.Relative[Local]]

  val absolute: FilePath.Absolute[Local] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath).asInstanceOf[FilePath.Absolute[Local]]
}

@RunWith(classOf[JUnitRunner])
class PotentialSuccessorSpec extends FreeSpec {
  import PotentialSuccessorSpec._
  import eyrie.syntax.potentialSuccessor._

  "RelativeFile" - {
    "parent should be Option of FilePath.Relative" in {
      relative.parentOption: Option[FilePath.Relative[Local]]
    }
    "lastSegment should be Option of FileName" in {
      relative.lastSegmentOption: Option[FileName[Local]]
    }
  }
  "AbsoluteFile" - {
    "parent should be Option of FilePath.Absolute" in {
      absolute.parentOption: Option[FilePath.Absolute[Local]]
    }
    "lastSegment should be Option of FileName" in {
      absolute.lastSegmentOption: Option[FileName[Local]]
    }
  }
}
