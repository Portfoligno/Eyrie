package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.Internal
import eyrie.file.context.Sys
import eyrie.file.{AbsoluteFile, RootDirectory}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object DescendantSpec {
  val absoluteFile: AbsoluteFile[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)
}

@RunWith(classOf[JUnitRunner])
class DescendantSpec extends FreeSpec {
  import DescendantSpec._
  import eyrie.syntax.descendant._

  "AbsoluteFile" - {
    "root should be RootDirectory" in {
      absoluteFile.root: RootDirectory[Sys]
    }
  }
}
