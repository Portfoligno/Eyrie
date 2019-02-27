package eyrie.nio.instances

import java.nio.file.Path

import eyrie.file.ops.Directory
import eyrie.nio.FilePath.Internal
import eyrie.nio.{AbsoluteFile, FileName, FilePath, RelativeFile, RootDirectory}
import eyrie.ops.{Descendant, Successor}

private[nio]
trait AbsoluteFileInstances extends PrefixInstances[AbsoluteFile]
  with SuffixInstances[AbsoluteFile, RelativeFile] {
  implicit def eyrieNioAbsoluteDescendantInstance[C]:
  Descendant[AbsoluteFile[C], RootDirectory[C]] with Successor[AbsoluteFile[C], FilePath.Absolute[C], FileName[C]] =
    AbsoluteFilePathDescendant.asInstanceOf[
      Descendant[AbsoluteFile[C], RootDirectory[C]] with Successor[AbsoluteFile[C], FilePath.Absolute[C], FileName[C]]]

  implicit def eyrieNioDirectoryInstance[C]: Directory[AbsoluteFile[C], FileName[C]] =
    FilePathDirectory.asInstanceOf[Directory[AbsoluteFile[C], FileName[C]]]
}

private
object AbsoluteFilePathDescendant extends Descendant[AbsoluteFile[Any], RootDirectory[Any]]
  with Successor[AbsoluteFile[Any], FilePath.Absolute[Any], FileName[Any]] {
  import eyrie.nio.syntax.asJava._

  override
  def root: AbsoluteFile[Any] => RootDirectory[Any] =
    a => Internal.RootDirectory(a.asJava.getRoot)

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
}
