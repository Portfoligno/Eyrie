package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.Internal
import eyrie.file.context.Local
import eyrie.file.{Emptiness, FilePath, IdentityFilePath, RelativeFile, Relativity}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object ConvertibleSpec {
  val relative: FilePath.Relative[Local] =
    Internal.RelativeFile(Paths.get("a/b/c")).asInstanceOf[FilePath.Relative[Local]]
  val relativeFile: RelativeFile[Local] =
    Internal.RelativeFile(Paths.get("a/b/c"))
}

@RunWith(classOf[JUnitRunner])
class ConvertibleSpec extends FreeSpec {
  import ConvertibleSpec._
  import eyrie.syntax.convertible._

  "FilePath.Relative" - {
    "widen should be FilePath" in {
      relative.widen: FilePath[Local]
    }
    "toOption should work" in {
      relative.toOption[IdentityFilePath[Local]]
    }
  }
  "RelativeFile" - {
    "widen should be FilePath.Relative" in {
      relativeFile.widen: FilePath.Relative[Local]
    }
    "widen.widen should should be FilePath" in {
      relativeFile.widen.widen: FilePath[Local]
    }
    "widening[Emptiness] should be FilePath.Relative" in {
      relativeFile.widening[Emptiness]: FilePath.Relative[Local]
    }
    "widening[Relativity] should be FilePath.NonEmpty" in {
      relativeFile.widening[Relativity]: FilePath.NonEmpty[Local]
    }
  }
}
