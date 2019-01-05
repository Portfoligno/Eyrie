package eyrie.file

import eyrie.file.ops.{EqualityInstances, RelativeFilePathSuccessor}

private[file]
trait RelativeFileInstances extends EqualityInstances[RelativeFile] {
  implicit def relativeFilePathSuccessorInstance[C]: RelativeFilePathSuccessor[C] =
    RelativeFilePathSuccessor.asInstanceOf[RelativeFilePathSuccessor[C]]
}
