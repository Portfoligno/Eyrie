package eyrie.file.ops

import eyrie.file.File.Internal
import eyrie.file.{AbsoluteFile, File, FileName}
import eyrie.ops.Sibling

trait FileAbsoluteSibling[C] extends Sibling[AbsoluteFile[C]]  {
  override
  type Prefix = File.Absolute[C]

  override
  type Segment = FileName[C]
}

private[file]
object FileAbsoluteSibling extends FileAbsoluteSibling[Any] {
  import eyrie.file.syntax.fileAsJava._

  override
  def parent(a: AbsoluteFile[Any]): File.Absolute[Any] =
    Internal.File.Absolute(a.asJava.getParent)

  override
  def lastSegment(a: AbsoluteFile[Any]): FileName[Any] =
    Internal.FileName(a.asJava.getFileName)
}

