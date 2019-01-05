package eyrie.file.ops

import java.nio.file.Path

import eyrie.file.FilePath.Internal
import eyrie.file.{AbsoluteFile, FilePath, FileName}
import eyrie.ops.Successor

private[file]
trait AbsoluteFilePathSuccessor[C] extends Successor[AbsoluteFile[C]]  {
  override
  type Prefix = FilePath.Absolute[C]

  override
  type Segment = FileName[C]
}

private[file]
object AbsoluteFilePathSuccessor extends AbsoluteFilePathSuccessor[Any] {
  import eyrie.file.syntax.fileAsJava._

  private
  def absolute[S](path: Path): FilePath.Absolute[S] =
    if (path.getNameCount < 1) {
      Internal.RootDirectory(path)
    } else {
      Internal.AbsoluteFile(path)
    }

  override
  def parent(a: AbsoluteFile[Any]): FilePath.Absolute[Any] =
    absolute(a.asJava.getParent)

  override
  def lastSegment(a: AbsoluteFile[Any]): FileName[Any] =
    Internal.FileName(a.asJava.getFileName)
}

