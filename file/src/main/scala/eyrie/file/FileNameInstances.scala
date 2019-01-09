package eyrie.file

import eyrie.file.ops.{EqualityInstances, FilePathSegment}
import eyrie.ops.Segment

private[file]
trait FileNameInstances extends EqualityInstances[FileName] {
  implicit def eyrieFileSegmentInstance[C]: Segment.Aux[FileName[C], RelativeFile[C]] =
    FilePathSegment.asInstanceOf[FilePathSegment[C]]
}
