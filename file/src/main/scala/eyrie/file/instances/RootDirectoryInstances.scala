package eyrie.file.instances

import eyrie.file.RootDirectory
import eyrie.ops.{NonSuccessor, TrivialDescendant}

private[file]
trait RootDirectoryInstances extends EqualityInstances[RootDirectory] {
  implicit def eyrieFileAbsoluteTrivialDescendantInstance[C]:
  TrivialDescendant[RootDirectory[C]] with NonSuccessor[RootDirectory[C]] =
    FilePathTrivialDescendant.asInstanceOf[TrivialDescendant[RootDirectory[C]] with NonSuccessor[RootDirectory[C]]]
}

private
object FilePathTrivialDescendant extends TrivialDescendant[RootDirectory[Any]]
  with NonSuccessor[RootDirectory[Any]]
