package eyrie.nio.test

import java.nio.file.Paths

import eyrie.nio.FilePath.Internal
import eyrie.nio.context.Sys
import eyrie.nio.{AbsoluteFile, FilePath, RelativeFile}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object SuffixSpec {
  val relativeFile: RelativeFile[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))

  val absoluteFile: AbsoluteFile[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)

  val nonEmpty: FilePath.NonEmpty[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)

  val filePath: FilePath[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)

  val relative: FilePath.Relative[Sys] =
    Internal.RelativeFile(Paths.get("a/b/c"))
}

@RunWith(classOf[JUnitRunner])
class SuffixSpec extends FreeSpec {
  import SuffixSpec._
  import eyrie.syntax.suffix._

  "RelativeFile" - {
    "endsWith(RelativeFile) should work" in {
      relativeFile.endsWith(relativeFile)
    }
  }
  "AbsoluteFile" - {
    "endsWith(RelativeFile) should work" in {
      absoluteFile.endsWith(relativeFile)
    }
  }
  "NonEmpty" - {
    "endsWith(RelativeFile) should work" in {
      nonEmpty.endsWith(relativeFile)
    }
  }
  "FilePath" - {
    "endsWith(RelativeFile) should work" in {
      filePath.endsWith(relativeFile)
    }
  }

  "RelativeFile" - {
    "endsWith(Relative) should work" in {
      relativeFile.endsWith(relative)
    }
  }
  "AbsoluteFile" - {
    "endsWith(Relative) should work" in {
      absoluteFile.endsWith(relative)
    }
  }
  "NonEmpty" - {
    "endsWith(Relative) should work" in {
      nonEmpty.endsWith(relative)
    }
  }
  "FilePath" - {
    "endsWith(Relative) should work" in {
      filePath.endsWith(relative)
    }
  }
}
