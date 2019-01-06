package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.Internal
import eyrie.file.context.Local
import eyrie.file.{FilePath, IdentityFilePath, RelativeFile}
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
    "widen to FilePath should work" in {
      relative.widen: FilePath[Local]
    }
    "toOption should work" in {
      relative.toOption[IdentityFilePath[Local]]
    }
  }
  "RelativeFile" - {
    "widen to NonEmpty should work" in {
      relativeFile.widen[FilePath.NonEmpty[Local]]
    }
    "widen to Relative should work" in {
      relativeFile.widen: FilePath.Relative[Local]
    }
    "widen to FilePath should work" in {
      relativeFile.widen[FilePath[Local]]
    }
  }
}
