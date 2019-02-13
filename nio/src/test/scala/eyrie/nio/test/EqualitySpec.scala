package eyrie.nio.test

import java.nio.file.Paths

import eyrie.nio.FilePath
import eyrie.nio.FilePath.Internal
import eyrie.nio.context.Sys
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object EqualitySpec {
  val relative: FilePath.Relative[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  def testCall: Boolean = {
    import eyrie.syntax.equality._
    relative === relative
  }
}

@RunWith(classOf[JUnitRunner])
class EqualitySpec extends FreeSpec {
  import EqualitySpec._

  "FilePath.Relative" - {
    "=== should work" in {
      assert(testCall)
    }
  }
}
