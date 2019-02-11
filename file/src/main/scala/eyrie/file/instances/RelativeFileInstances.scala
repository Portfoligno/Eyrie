package eyrie.file.instances

import java.nio.file.Path

import eyrie.file.FilePath.Internal
import eyrie.file.{FileName, FilePath, RelativeFile}
import eyrie.ops.{NonDescendant, Successor}

private[file]
trait RelativeFileInstances extends PrefixInstances[RelativeFile]
  with SuffixInstances[RelativeFile, RelativeFile] {
  implicit def eyrieFileRelativeDescendantInstance[C]:
  NonDescendant[RelativeFile[C]] with Successor[RelativeFile[C], FilePath.Relative[C], FileName[C]] =
    RelativeFilePathDescendant.asInstanceOf[
      NonDescendant[RelativeFile[C]] with Successor[RelativeFile[C], FilePath.Relative[C], FileName[C]]]
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
}
