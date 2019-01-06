package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.{Emptiness, FilePath, IdentityFilePath, RelativeFile, Relativity}
import eyrie.file.FilePath.Internal
import eyrie.file.context.Local
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object SubdivisionSpec {
  val filePath: FilePath[Local] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  val relativeFile: FilePath.Relative[Local] =
    Internal.RelativeFile(Paths.get("a/b/c"))
}

@RunWith(classOf[JUnitRunner])
class SubdivisionSpec extends FreeSpec {
  import SubdivisionSpec._
  import eyrie.syntax.subdivision._

  "FilePath" - {
    "subdivide should be either FilePath.Relative or FilePath.Absolute" in {
      filePath.subdivide: Either[FilePath.Relative[Local], FilePath.Absolute[Local]]
    }
    "subdivideBy[Emptiness] should be either FilePath.Empty or FilePath.NonEmpty" in {
      filePath.subdivideBy[Emptiness]: Either[FilePath.Empty[Local], FilePath.NonEmpty[Local]]
    }
    "subdivideBy[Relativity] should be either FilePath.Relative or FilePath.Absolute" in {
      filePath.subdivideBy[Relativity]: Either[FilePath.Relative[Local], FilePath.Absolute[Local]]
    }
  }
  "FilePath.Relative" - {
    "subdivide should be either IdentityFilePath or RelativeFile" in {
      relativeFile.subdivide: Either[IdentityFilePath[Local], RelativeFile[Local]]
    }
    "subdivideBy[Emptiness] should be either IdentityFilePath or RelativeFile" in {
      relativeFile.subdivideBy[Emptiness]: Either[IdentityFilePath[Local], RelativeFile[Local]]
    }
  }
}
