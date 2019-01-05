package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.File.Internal
import eyrie.file.context.Local
import eyrie.file.{AbsoluteFile, File, FileName, RelativeFile}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object SiblingSpec {
  val relativeFile: RelativeFile[Local] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  val absoluteFile: AbsoluteFile[Local] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)
}

@RunWith(classOf[JUnitRunner])
class SiblingSpec extends FreeSpec {
  import SiblingSpec._
  import eyrie.syntax.sibling._

  "RelativeFile" - {
    "parent should be File.Relative" in {
      relativeFile.parent: File.Relative[Local]
    }
    "lastSegment should be FileName" in {
      relativeFile.lastSegment: FileName[Local]
    }
  }
  "AbsoluteFile" - {
    "parent should be File.Absolute" in {
      absoluteFile.parent: File.Absolute[Local]
    }
    "lastSegment should be FileName" in {
      absoluteFile.lastSegment: FileName[Local]
    }
  }
}
