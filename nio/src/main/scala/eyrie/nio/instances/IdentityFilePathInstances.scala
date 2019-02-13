package eyrie.nio.instances

import eyrie.nio.IdentityFilePath
import eyrie.ops.{NonDescendant, NonSuccessor}

private[nio]
trait IdentityFilePathInstances extends EqualityInstances[IdentityFilePath] {
  implicit def eyrieNioRelativeNonDescendantInstance[C]:
  NonDescendant[IdentityFilePath[C]] with NonSuccessor[IdentityFilePath[C]] =
    FilePathNonDescendant.asInstanceOf[NonDescendant[IdentityFilePath[C]] with NonSuccessor[IdentityFilePath[C]]]
}

private
object FilePathNonDescendant extends NonDescendant[IdentityFilePath[Any]]
  with NonSuccessor[IdentityFilePath[Any]]
