package eyrie.file.ops

import eyrie.file.FilePath.Internal
import eyrie.file.{FileName, RelativeFile}
import eyrie.ops.Segment

trait FileSegment[C] extends Segment[FileName[C]] {
  override
  type Singleton = RelativeFile[C]
}

private[file]
object FileSegment extends FileSegment[Any] {
  import eyrie.file.syntax.fileAsJava._

  override
  def singleton(a: FileName[Any]): RelativeFile[Any] =
    Internal.RelativeFile(a.asJava)
}
