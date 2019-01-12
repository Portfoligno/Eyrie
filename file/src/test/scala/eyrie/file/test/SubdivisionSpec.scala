package eyrie.file.test

import java.nio.file.Paths

import eyrie.{Emptiness, Relativity}
import eyrie.file.{FilePath, IdentityFilePath, RelativeFile}
import eyrie.file.FilePath.Internal
import eyrie.file.context.Sys
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object SubdivisionSpec {
  val filePath: FilePath[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  val relativeFile: FilePath.Relative[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))
}

@RunWith(classOf[JUnitRunner])
class SubdivisionSpec extends FreeSpec {
  import SubdivisionSpec._
  import eyrie.syntax.subdivision._

  "FilePath" - {
    "subdivide should be either FilePath.Relative or FilePath.Absolute" in {
      filePath.subdivide: Either[FilePath.Relative[Sys], FilePath.Absolute[Sys]]
    }
    "subdivideBy[Emptiness] should be either FilePath.Empty or FilePath.NonEmpty" in {
      filePath.subdivideBy[Emptiness]: Either[FilePath.Empty[Sys], FilePath.NonEmpty[Sys]]
    }
    "subdivideBy[Relativity] should be either FilePath.Relative or FilePath.Absolute" in {
      filePath.subdivideBy[Relativity]: Either[FilePath.Relative[Sys], FilePath.Absolute[Sys]]
    }
  }
  "FilePath.Relative" - {
    "subdivide should be either IdentityFilePath or RelativeFile" in {
      relativeFile.subdivide: Either[IdentityFilePath[Sys], RelativeFile[Sys]]
    }
    "subdivideBy[Emptiness] should be either IdentityFilePath or RelativeFile" in {
      relativeFile.subdivideBy[Emptiness]: Either[IdentityFilePath[Sys], RelativeFile[Sys]]
    }
  }
}
