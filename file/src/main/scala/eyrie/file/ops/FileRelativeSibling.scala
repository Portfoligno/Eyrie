package eyrie.file.ops

import eyrie.file.{File, FileName, RelativeFile}
import eyrie.ops.Sibling
import eyrie.file.File.Internal

trait FileRelativeSibling[C] extends Sibling[RelativeFile[C]]  {
  override
  type Prefix = File.Relative[C]

  override
  type Segment = FileName[C]
}

private[file]
object FileRelativeSibling extends FileRelativeSibling[Any] {
  import eyrie.file.syntax.fileAsJava._

  override
  def parent(a: RelativeFile[Any]): File.Relative[Any] =
    Internal.File.Relative(a.asJava.getParent)

  override
  def lastSegment(a: RelativeFile[Any]): FileName[Any] =
    Internal.FileName(a.asJava.getFileName)
}
