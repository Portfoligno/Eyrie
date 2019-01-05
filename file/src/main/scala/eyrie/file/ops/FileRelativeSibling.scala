package eyrie.file.ops

import java.nio.file.Path

import eyrie.file.FilePath.Internal
import eyrie.file.{FilePath, FileName, RelativeFile}
import eyrie.ops.Sibling

trait FileRelativeSibling[C] extends Sibling[RelativeFile[C]]  {
  override
  type Prefix = FilePath.Relative[C]

  override
  type Segment = FileName[C]
}

private[file]
object FileRelativeSibling extends FileRelativeSibling[Any] {
  import eyrie.file.syntax.fileAsJava._

  private
  def relative[S](path: Path): FilePath.Relative[S] =
    if (path.getNameCount < 2 && Option(path.getFileName).forall(_.toString.isEmpty)) {
      Internal.IdentityFilePath(path)
    } else {
      Internal.RelativeFile(path)
    }

  override
  def parent(a: RelativeFile[Any]): FilePath.Relative[Any] =
    relative(a.asJava.getParent)

  override
  def lastSegment(a: RelativeFile[Any]): FileName[Any] =
    Internal.FileName(a.asJava.getFileName)
}
