package eyrie.file

import eyrie.file.ops.{EqualityInstances, FilePathSegment}

private[file]
trait FileNameInstances extends EqualityInstances[FileName] {
  implicit def eyrieFileFileSegmentInstance[C]: FilePathSegment[C] =
    FilePathSegment.asInstanceOf[FilePathSegment[C]]
}
