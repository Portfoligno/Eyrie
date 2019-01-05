package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath
import eyrie.file.FilePath.Internal
import eyrie.file.context.Local
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object EqualitySpec {
  val relative: FilePath.Relative[Local] =
    Internal.RelativeFile(Paths.get("a/b/c")).asInstanceOf[FilePath.Relative[Local]]

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
