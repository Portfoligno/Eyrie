package eyrie.file.instances

import java.nio.file.Path

import eyrie.file.FilePath.Internal
import eyrie.file.{AbsoluteFile, FileName, FilePath, IdentityFilePath, RelativeFile, RootDirectory}
import eyrie.ops.{Descendant, NonDescendant, Successor, TrivialDescendant}

private[file]
trait FilePathSuccessorInstances {
  implicit def eyrieFileRelativeDescendantInstance[C]:
  NonDescendant[RelativeFile[C]] with Successor[RelativeFile[C], FilePath.Relative[C], FileName[C]] =
    RelativeFilePathDescendant.asInstanceOf[
      NonDescendant[RelativeFile[C]] with Successor[RelativeFile[C], FilePath.Relative[C], FileName[C]]]

  implicit def eyrieFileAbsoluteDescendantInstance[C]:
  Descendant[AbsoluteFile[C], RootDirectory[C]] with Successor[AbsoluteFile[C], FilePath.Absolute[C], FileName[C]] =
    AbsoluteFilePathDescendant.asInstanceOf[
      Descendant[AbsoluteFile[C], RootDirectory[C]] with Successor[AbsoluteFile[C], FilePath.Absolute[C], FileName[C]]]

  implicit def eyrieFileRelativeNonDescendantInstance[C]: NonDescendant[IdentityFilePath[C]] =
    FilePathNonDescendant.asInstanceOf[NonDescendant[IdentityFilePath[C]]]

  implicit def eyrieFileAbsoluteTrivialDescendantInstance[C]: TrivialDescendant[RootDirectory[C]] =
    FilePathTrivialDescendant.asInstanceOf[TrivialDescendant[RootDirectory[C]]]
}

private
object RelativeFilePathDescendant extends NonDescendant[RelativeFile[Any]]
  with Successor[RelativeFile[Any], FilePath.Relative[Any], FileName[Any]] {
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

  override
  def root: RelativeFile[Any] => Nothing =
    _ => throw new UnsupportedOperationException("Unexpected invocation")
}

private
object AbsoluteFilePathDescendant extends Descendant[AbsoluteFile[Any], RootDirectory[Any]]
  with Successor[AbsoluteFile[Any], FilePath.Absolute[Any], FileName[Any]] {
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

private
object FilePathNonDescendant extends NonDescendant[IdentityFilePath[Any]] {
  override
  def root: IdentityFilePath[Any] => Nothing =
    _ => throw new UnsupportedOperationException("Unexpected invocation")
}

private
object FilePathTrivialDescendant extends TrivialDescendant[RootDirectory[Any]] {
  override
  def root: RootDirectory[Any] => RootDirectory[Any] =
    identity
}
