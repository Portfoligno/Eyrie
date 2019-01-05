package eyrie.file

import eyrie.file.ops.{AbsoluteFilePathSuccessor, EqualityInstances}
import eyrie.ops.Successor

private[file]
trait AbsoluteFileInstances extends EqualityInstances[AbsoluteFile] {
  implicit def absoluteFilePathSuccessorInstance[C]: Successor.Aux[AbsoluteFile[C], FilePath.Absolute[C], FileName[C]] =
    AbsoluteFilePathSuccessor.asInstanceOf[AbsoluteFilePathSuccessor[C]]
}
