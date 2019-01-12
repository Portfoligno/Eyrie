package eyrie.file.test

import java.nio.file.Paths

import eyrie.file.{FileName, RelativeFile}
import eyrie.file.FilePath.Internal
import eyrie.file.context.Sys
import org.junit.runner.RunWith
import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner

object SegmentSpec {
  val fileName: FileName[Sys] =
    Internal.FileName(Paths.get("a/b/c").getFileName)
}

@RunWith(classOf[JUnitRunner])
class SegmentSpec extends FreeSpec {
  import SegmentSpec._
  import eyrie.syntax.segment._

  "FileName" - {
    "singletonPath should be RelativeFile" in {
      fileName.singletonPath: RelativeFile[Sys]
    }
  }
}
