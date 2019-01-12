package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.Internal
import eyrie.file.context.Sys
import eyrie.file.{Emptiness, FilePath, IdentityFilePath, RelativeFile, Relativity}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object ConvertibleSpec {
  val relative: FilePath.Relative[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  val relativeFile: RelativeFile[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))
}

@RunWith(classOf[JUnitRunner])
class ConvertibleSpec extends FreeSpec {
  import ConvertibleSpec._
  import eyrie.syntax.convertible._

  "FilePath.Relative" - {
    "widen should be FilePath" in {
      relative.widen: FilePath[Sys]
    }
    "narrow should work" in {
      relative.narrow[IdentityFilePath[Sys]]
    }
  }
  "RelativeFile" - {
    "widen should be FilePath.Relative" in {
      relativeFile.widen: FilePath.Relative[Sys]
    }
    "widen.widen should should be FilePath" in {
      relativeFile.widen.widen: FilePath[Sys]
    }
    "widenBy[Emptiness] should be FilePath.Relative" in {
      relativeFile.widenBy[Emptiness]: FilePath.Relative[Sys]
    }
    "widenBy[Relativity] should be FilePath.NonEmpty" in {
      relativeFile.widenBy[Relativity]: FilePath.NonEmpty[Sys]
    }
  }
}
