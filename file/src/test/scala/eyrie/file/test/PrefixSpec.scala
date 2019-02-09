package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.Internal
import eyrie.file.context.Sys
import eyrie.file.{AbsoluteFile, FilePath, RelativeFile}
import eyrie.ops.Prefix
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object PrefixSpec {
  val relativeFile: RelativeFile[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  val absoluteFile: AbsoluteFile[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)

  val relative: FilePath.Relative[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))
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
  "Relative" - {
    "startsWith(Relative) should work" in {
      relative.startsWith(relative)
    }
  }
}