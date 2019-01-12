package eyrie.file.instances

import java.nio.file.Path

import eyrie.file.FilePath.Internal
import eyrie.file.{AbsoluteFile, FileName, FilePath, RootDirectory}
import eyrie.ops.Descendant

private[file]
trait AbsoluteFileInstances extends EqualityInstances[AbsoluteFile] {
  implicit def eyrieFileDescendantInstance[C]: Descendant.Aux[
    AbsoluteFile[C], FilePath.Absolute[C], FileName[C], RootDirectory[C]] =
    AbsoluteFilePathDescendant.asInstanceOf[Descendant.Aux[
      AbsoluteFile[C], FilePath.Absolute[C], FileName[C], RootDirectory[C]]]
}

private
object AbsoluteFilePathDescendant extends Descendant[AbsoluteFile[Any]] {
  import eyrie.file.syntax.asJava._

  override
  type Prefix = FilePath.Absolute[Any]

  override
  type Segment = FileName[Any]

  override
  type Root = RootDirectory[Any]

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
