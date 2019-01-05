package eyrie.file

import eyrie.file.ops.{AbsoluteFilePathSuccessor, EqualityInstances}

private[file]
trait AbsoluteFileInstances extends EqualityInstances[AbsoluteFile] {
  implicit def absoluteFilePathSuccessorInstance[C]: AbsoluteFilePathSuccessor[C] =
    AbsoluteFilePathSuccessor.asInstanceOf[AbsoluteFilePathSuccessor[C]]
}
