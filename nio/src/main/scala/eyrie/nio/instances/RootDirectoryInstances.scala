package eyrie.nio.instances

import eyrie.nio.RootDirectory
import eyrie.ops.{NonSuccessor, TrivialDescendant}

private[nio]
trait RootDirectoryInstances extends EqualityInstances[RootDirectory] {
  implicit def eyrieNioAbsoluteTrivialDescendantInstance[C]:
  TrivialDescendant[RootDirectory[C]] with NonSuccessor[RootDirectory[C]] =
    FilePathTrivialDescendant.asInstanceOf[TrivialDescendant[RootDirectory[C]] with NonSuccessor[RootDirectory[C]]]
}

private
object FilePathTrivialDescendant extends TrivialDescendant[RootDirectory[Any]]
  with NonSuccessor[RootDirectory[Any]]
