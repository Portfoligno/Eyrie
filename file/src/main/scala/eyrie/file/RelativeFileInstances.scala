package eyrie.file

import eyrie.file.ops.{FileEqualityInstances, FileRelativeSibling}

private[file]
trait RelativeFileInstances extends FileEqualityInstances[RelativeFile] {
  implicit def fileRelativeSiblingInstance[C]: FileRelativeSibling[C] =
    FileRelativeSibling.asInstanceOf[FileRelativeSibling[C]]
}
