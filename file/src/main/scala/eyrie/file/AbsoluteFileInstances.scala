package eyrie.file

import eyrie.file.ops.{FileAbsoluteSibling, FileEqualityInstances}

private[file]
trait AbsoluteFileInstances extends FileEqualityInstances[AbsoluteFile] {
  implicit def fileRelativeSiblingInstance[C]: FileAbsoluteSibling[C] =
    FileAbsoluteSibling.asInstanceOf[FileAbsoluteSibling[C]]
}
