package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.Internal
import eyrie.file.context.Local
import eyrie.file.{AbsoluteFile, FileName, FilePath, RelativeFile}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object SuccessorSpec {
  val relativeFile: RelativeFile[Local] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  val absoluteFile: AbsoluteFile[Local] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)
}

@RunWith(classOf[JUnitRunner])
class SuccessorSpec extends FreeSpec {
  import SuccessorSpec._
  import eyrie.syntax.successor._

  "RelativeFile" - {
    "parent should be File.Relative" in {
      relativeFile.parent: FilePath.Relative[Local]
    }
    "lastSegment should be FileName" in {
      relativeFile.lastSegment: FileName[Local]
    }
  }
  "AbsoluteFile" - {
    "parent should be File.Absolute" in {
      absoluteFile.parent: FilePath.Absolute[Local]
    }
    "lastSegment should be FileName" in {
      absoluteFile.lastSegment: FileName[Local]
    }
  }
}
