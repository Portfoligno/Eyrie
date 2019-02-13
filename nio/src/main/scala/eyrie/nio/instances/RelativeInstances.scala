package eyrie.nio.instances

import eyrie.Emptiness
import eyrie.nio.FilePath.Relative
import eyrie.nio.{FilePath, IdentityFilePath, RelativeFile}

private[nio]
trait RelativeInstances extends SubdivisionInstances[Emptiness, FilePath.Relative, RelativeFile, IdentityFilePath]
  with EqualityInstances[Relative]
