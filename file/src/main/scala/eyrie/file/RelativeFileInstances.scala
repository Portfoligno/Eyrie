package eyrie.file

import eyrie.file.ops.FileRelativeSibling

private[file]
trait RelativeFileInstances {
  implicit def fileRelativeSiblingInstance[C]: FileRelativeSibling[C] =
    FileRelativeSibling.asInstanceOf[FileRelativeSibling[C]]
}
