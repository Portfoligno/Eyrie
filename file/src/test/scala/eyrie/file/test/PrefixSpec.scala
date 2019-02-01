package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.Internal
import eyrie.file.context.Sys
import eyrie.file.{AbsoluteFile, RelativeFile}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object PrefixSpec {
  val relativeFile: RelativeFile[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  val absoluteFile: AbsoluteFile[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)
}

@RunWith(classOf[JUnitRunner])
class PrefixSpec extends FreeSpec {
  import PrefixSpec._
  import eyrie.syntax.prefix._

  "RelativeFile" - {
    "startsWith(RelativeFile) should work" in {
      relativeFile.startsWith(relativeFile)
    }
  }
  "AbsoluteFile" - {
    "startsWith(AbsoluteFile) should work" in {
      absoluteFile.startsWith(absoluteFile)
    }
  }
}
