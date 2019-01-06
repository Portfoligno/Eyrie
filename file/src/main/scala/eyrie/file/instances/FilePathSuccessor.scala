package eyrie.file.instances

import java.nio.file.Path

import eyrie.file.FilePath.Internal
import eyrie.file.{AbsoluteFile, FileName, FilePath, RelativeFile, RootDirectory}
import eyrie.ops.{Descendant, Successor}

private[file]
trait FilePathSuccessorInstances {
  implicit def eyrieFileRelativeSuccessorByPrefixInstance[C]: Successor[
    RelativeFile[C], FilePath.Relative[C], FileName[C]] =
    RelativeFilePathSuccessor.asInstanceOf[Successor[RelativeFile[C], FilePath.Relative[C], FileName[C]]]

  implicit def eyrieFileAbsoluteDescendantByPrefixInstance[C]: Descendant[
    AbsoluteFile[C], FilePath.Absolute[C], FileName[C], RootDirectory[C]] =
    AbsoluteFilePathDescendant.asInstanceOf[Descendant[
      AbsoluteFile[C], FilePath.Absolute[C], FileName[C], RootDirectory[C]]]
}

private
object RelativeFilePathSuccessor extends Successor[RelativeFile[Any], FilePath.Relative[Any], FileName[Any]] {
  import eyrie.file.syntax.asJava._

  private
  def relative[S](path: Path): FilePath.Relative[S] =
    if (path.getNameCount < 2 && Option(path.getFileName).forall(_.toString.isEmpty)) {
      Internal.IdentityFilePath(path)
    } else {
      Internal.RelativeFile(path)
    }

  override
  def parent: RelativeFile[Any] => FilePath.Relative[Any] =
    a => relative(a.asJava.getParent)

  override
  def lastSegment: RelativeFile[Any] => FileName[Any] =
    a => Internal.FileName(a.asJava.getFileName)
}

private
object AbsoluteFilePathDescendant extends Descendant[
  AbsoluteFile[Any], FilePath.Absolute[Any], FileName[Any], RootDirectory[Any]] {
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
