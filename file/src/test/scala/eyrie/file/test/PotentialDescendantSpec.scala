package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.FilePath.Internal
import eyrie.file.context.Sys
import eyrie.file.{FilePath, RootDirectory}
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner
import shapeless.test.illTyped

object PotentialDescendantSpec {
  val nonEmpty: FilePath.NonEmpty[Sys] =
    Internal.AbsoluteFile(Paths.get("a/b/c").toAbsolutePath)

  val filePath: FilePath[Sys] =
    Internal.RootDirectory(Paths.get("a/b/c").getRoot)

  val empty: FilePath.Empty[Sys] =
    Internal.RootDirectory(Paths.get("a/b/c").getRoot)
}

@RunWith(classOf[JUnitRunner])
class PotentialDescendantSpec extends FreeSpec {
  import PotentialDescendantSpec._
  import eyrie.syntax.potentialDescendant._

  "FilePath.Absolute" - {
    "rootOption should be Option of RootDirectory" in {
      nonEmpty.rootOption: Option[RootDirectory[Sys]]
    }
  }
  "FilePath" - {
    "rootOption should be Option of RootDirectory" in {
      filePath.rootOption: Option[RootDirectory[Sys]]
    }
  }
  "FilePath.Empty" - {
    "rootOption should not be available" in {
      illTyped(
        """
          empty.rootOption
        """
      )
    }
    "asRoot should be Option of RootDirectory" in {
      empty.asRoot: Option[RootDirectory[Sys]]
    }
  }
}
