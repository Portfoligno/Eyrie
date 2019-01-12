package eyrie.file.instances

import eyrie.file.FilePath.Internal
import eyrie.file.{FileName, RelativeFile}
import eyrie.ops.Segment

private[file]
trait FileNameInstances extends EqualityInstances[FileName] {
  implicit def eyrieFileSegmentInstance[C]: Segment.Aux[FileName[C], RelativeFile[C]] =
    FilePathSegment.asInstanceOf[Segment.Aux[FileName[C], RelativeFile[C]]]
}

private
object FilePathSegment extends Segment[FileName[Any]] {
  import eyrie.file.syntax.asJava._

  override
  type Singleton = RelativeFile[Any]

  override
  def singleton(a: FileName[Any]): RelativeFile[Any] =
    Internal.RelativeFile(a.asJava)
}
