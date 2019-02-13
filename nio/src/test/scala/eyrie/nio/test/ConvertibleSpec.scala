package eyrie.nio.test

import java.nio.file.Paths

import eyrie.{Emptiness, False, Relativity, True}
import eyrie.nio.FilePath.Internal
import eyrie.nio.context.Sys
import eyrie.nio.{FilePath, IdentityFilePath, RelativeFile, RootDirectory}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object ConvertibleSpec {
  val relative: FilePath.Relative[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  val relativeFile: RelativeFile[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  val empty: FilePath.Empty[Sys] =
    Internal.RootDirectory(Paths.get("a/b/c").toAbsolutePath.getRoot)
}

@RunWith(classOf[JUnitRunner])
class ConvertibleSpec extends FreeSpec {
  import ConvertibleSpec._
  import eyrie.syntax.convertible._

  "FilePath.Relative" - {
    "widen should be FilePath" in {
      relative.widen: FilePath[Sys]
    }
    "typed narrow should work" in {
      relative.narrow[IdentityFilePath[Sys]]
    }
    "narrowBy[Emptiness[True]] should be Option of IdentityFilePath" in {
      relative.narrowBy[Emptiness[True]]: Option[IdentityFilePath[Sys]]
    }
    "narrowBy[Emptiness[False]] should be Option of RelativeFile" in {
      relative.narrowBy[Emptiness[False]]: Option[RelativeFile[Sys]]
    }
  }
  "RelativeFile" - {
    "typed widen should work" in {
      relativeFile.widen[FilePath[Sys]]
    }
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
  "FilePath.Empty" - {
    "typed narrow should work" in {
      empty.narrow[RootDirectory[Sys]]
    }
    "typed narrowBy should work" in {
      empty.narrowBy[Relativity[False]]
    }
    "typed as should work" in {
      empty.as[RootDirectory[Sys]]
    }
  }
}
