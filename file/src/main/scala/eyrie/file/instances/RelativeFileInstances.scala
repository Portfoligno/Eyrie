package eyrie.file.instances

import java.nio.file.Path

import eyrie.file.FilePath.Internal
import eyrie.file.{FileName, FilePath, RelativeFile}
import eyrie.ops.Successor

private[file]
trait RelativeFileInstances extends EqualityInstances[RelativeFile] {
  implicit def eyrieFileSuccessorInstance[C]: Successor.Aux[RelativeFile[C], FilePath.Relative[C], FileName[C]] =
    RelativeFilePathSuccessor.asInstanceOf[Successor.Aux[RelativeFile[C], FilePath.Relative[C], FileName[C]]]
}

private
object RelativeFilePathSuccessor extends Successor[RelativeFile[Any]] {
  import eyrie.file.syntax.asJava._

  override
  type Prefix = FilePath.Relative[Any]

  override
  type Segment = FileName[Any]

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
