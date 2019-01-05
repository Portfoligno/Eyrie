package eyrie.file

import eyrie.file.ops.{FileEqualityInstances, FileSegment}

private[file]
trait FileNameInstances extends FileEqualityInstances[FileName] {
  implicit def fileSegmentInstance[C]: FileSegment[C] =
    FileSegment.asInstanceOf[FileSegment[C]]
}
