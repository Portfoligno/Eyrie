package eyrie.nio.test

import eyrie.nio.context.Sys
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FilePathSchemeSpec extends FreeSpec {
  import eyrie.nio.syntax.filePathScheme._

  "String" - {
    "toFilePath should work" in {
      "a/b/c".toFilePath[Sys]
    }
  }
}
