package eyrie.nio.test

import java.nio.file.Paths

import eyrie.nio.FilePath.{Absolute, Internal}
import eyrie.nio.context.Sys
import eyrie.nio.{AbsoluteFile, FilePath, RelativeFile}
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

  val absolute: Absolute[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)

  val nonEmpty: FilePath.NonEmpty[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)

  val filePath: FilePath[Sys] =
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
  "Relative" - {
    "startsWith(Relative) should work" in {
      relative.startsWith(relative)
    }
  }
  "Absolute" - {
    "startsWith(Absolute) should work" in {
      absolute.startsWith(absolute)
    }
  }
  "NonEmpty" - {
    "startsWith(NonEmpty) should work" in {
      nonEmpty.startsWith(nonEmpty)
    }
  }
  "FilePath" - {
    "startsWith(FilePath) should work" in {
      filePath.startsWith(filePath)
    }
  }
}
