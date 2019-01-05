package eyrie.file

import eyrie.file.ops.FileAbsoluteSibling

private[file]
trait AbsoluteFileInstances {
  implicit def fileRelativeSiblingInstance[C]: FileAbsoluteSibling[C] =
    FileAbsoluteSibling.asInstanceOf[FileAbsoluteSibling[C]]
}
