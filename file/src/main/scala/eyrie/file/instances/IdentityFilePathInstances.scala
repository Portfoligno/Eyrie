package eyrie.file.instances

import eyrie.file.IdentityFilePath
import eyrie.ops.{NonDescendant, NonSuccessor}

private[file]
trait IdentityFilePathInstances extends EqualityInstances[IdentityFilePath] {
  implicit def eyrieFileRelativeNonDescendantInstance[C]:
  NonDescendant[IdentityFilePath[C]] with NonSuccessor[IdentityFilePath[C]] =
    FilePathNonDescendant.asInstanceOf[NonDescendant[IdentityFilePath[C]] with NonSuccessor[IdentityFilePath[C]]]
}

private
object FilePathNonDescendant extends NonDescendant[IdentityFilePath[Any]]
  with NonSuccessor[IdentityFilePath[Any]]
