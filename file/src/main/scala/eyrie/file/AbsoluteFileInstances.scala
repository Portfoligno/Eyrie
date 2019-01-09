package eyrie.file

import eyrie.file.ops.{AbsoluteFilePathDescendant, EqualityInstances}
import eyrie.ops.Descendant

private[file]
trait AbsoluteFileInstances extends EqualityInstances[AbsoluteFile] {
  implicit def eyrieFileDescendantInstance[C]: Descendant.Aux[
    AbsoluteFile[C], FilePath.Absolute[C], FileName[C], RootDirectory[C]] =
    AbsoluteFilePathDescendant.asInstanceOf[AbsoluteFilePathDescendant[C]]
}
