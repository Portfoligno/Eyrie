package eyrie.nio.instances

import eyrie.nio.FilePath.Internal
import eyrie.nio.{FileName, RelativeFile}
import eyrie.ops.Segment

private[nio]
trait FileNameInstances extends EqualityInstances[FileName] {
  implicit def eyrieNioSegmentInstance[C]: Segment.Aux[FileName[C], RelativeFile[C]] =
    FilePathSegment.asInstanceOf[Segment.Aux[FileName[C], RelativeFile[C]]]
}

private
object FilePathSegment extends Segment[FileName[Any]] {
  import eyrie.nio.syntax.asJava._

  override
  type Singleton = RelativeFile[Any]

  override
  def singleton(a: FileName[Any]): RelativeFile[Any] =
    Internal.RelativeFile(a.asJava)
}
