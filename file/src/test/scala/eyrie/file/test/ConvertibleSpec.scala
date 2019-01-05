package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.Internal
import eyrie.file.context.Local
import eyrie.file.{FilePath, IdentityFilePath}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object ConvertibleSpec {
  val relative: FilePath.Relative[Local] =
    Internal.RelativeFile(Paths.get("a/b/c")).asInstanceOf[FilePath.Relative[Local]]
}

@RunWith(classOf[JUnitRunner])
class ConvertibleSpec extends FreeSpec {
  import ConvertibleSpec._
  import eyrie.syntax.convertible._

  "FilePath.Relative" - {
    "widen should work" in {
      relative.widen: FilePath[Local]
    }
    "toOption should work" in {
      relative.toOption[IdentityFilePath[Local]]
    }
  }
}