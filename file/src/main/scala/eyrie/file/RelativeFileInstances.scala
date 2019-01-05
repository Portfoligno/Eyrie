package eyrie.file

import eyrie.file.ops.{EqualityInstances, RelativeFilePathSuccessor}
import eyrie.ops.Successor

private[file]
trait RelativeFileInstances extends EqualityInstances[RelativeFile] {
  implicit def successorInstance[C]: Successor.Aux[RelativeFile[C], FilePath.Relative[C], FileName[C]] =
    RelativeFilePathSuccessor.asInstanceOf[RelativeFilePathSuccessor[C]]
}
