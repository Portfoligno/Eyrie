package eyrie.file.ops

import java.nio.file.Path

import eyrie.file.FilePath.Internal
import eyrie.file.{AbsoluteFile, FileName, FilePath, RootDirectory}
import eyrie.ops.Descendant

private[file]
trait AbsoluteFilePathDescendant[C] extends Descendant[AbsoluteFile[C]]  {
  override
  type Prefix = FilePath.Absolute[C]

  override
  type Segment = FileName[C]

  override
  type Root = RootDirectory[C]
}

private[file]
object AbsoluteFilePathDescendant extends AbsoluteFilePathDescendant[Any] {
  import eyrie.file.syntax.asJava._

  private
  def absolute[S](path: Path): FilePath.Absolute[S] =
    if (path.getNameCount < 1) {
      Internal.RootDirectory(path)
    } else {
      Internal.AbsoluteFile(path)
    }

  override
  def parent: AbsoluteFile[Any] => FilePath.Absolute[Any] =
    a => absolute(a.asJava.getParent)

  override
  def lastSegment: AbsoluteFile[Any] => FileName[Any] =
    a => Internal.FileName(a.asJava.getFileName)

  override
  def root: AbsoluteFile[Any] => RootDirectory[Any] =
    a => Internal.RootDirectory(a.asJava.getRoot)
}
