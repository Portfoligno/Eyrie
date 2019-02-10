package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.{Absolute, Internal}
import eyrie.file.context.Sys
import eyrie.file.{AbsoluteFile, FilePath, RelativeFile}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object SuffixSpec {
  val relativeFile: RelativeFile[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  val absoluteFile: AbsoluteFile[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)

  val nonEmpty: FilePath.NonEmpty[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)
}

@RunWith(classOf[JUnitRunner])
class SuffixSpec extends FreeSpec {
  import SuffixSpec._
  import eyrie.syntax.suffix._

  "RelativeFile" - {
    "endsWith(RelativeFile) should work" in {
      relativeFile.endsWith(relativeFile)
    }
  }
  "AbsoluteFile" - {
    "endsWith(RelativeFile) should work" in {
      absoluteFile.endsWith(relativeFile)
    }
  }
  "NonEmpty" - {
    "endsWith(RelativeFile) should work" in {
      nonEmpty.endsWith(relativeFile)
    }
  }
}
