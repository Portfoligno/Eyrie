package eyrie.nio.test

import java.nio.file.Paths

import cats.effect.IO
import eyrie.nio.AbsoluteFile
import eyrie.nio.FilePath.Internal
import eyrie.nio.context.Sys
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object DirectorySpec {
  val absoluteFile: AbsoluteFile[Sys] =
    Internal.AbsoluteFile(Paths.get("").toAbsolutePath)
}

@RunWith(classOf[JUnitRunner])
class DirectorySpec extends FreeSpec {
  import DirectorySpec._
  import eyrie.file.syntax.directory._

  "AbsoluteFile" - {
    "list should work" in {
      absoluteFile
        .listFiles[IO]
        .evalMap(name => IO(println(name)))
        .compile
        .drain
        .unsafeRunSync()
    }
  }
}
