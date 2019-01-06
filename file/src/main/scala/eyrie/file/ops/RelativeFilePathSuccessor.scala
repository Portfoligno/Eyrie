package eyrie.file.ops

import java.nio.file.Path

import eyrie.file.FilePath.Internal
import eyrie.file.{FilePath, FileName, RelativeFile}
import eyrie.ops.Successor

private[file]
trait RelativeFilePathSuccessor[C] extends Successor[RelativeFile[C]]  {
  override
  type Prefix = FilePath.Relative[C]

  override
  type Segment = FileName[C]
}

private[file]
object RelativeFilePathSuccessor extends RelativeFilePathSuccessor[Any] {
  import eyrie.file.syntax.filePathAsJava._

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
