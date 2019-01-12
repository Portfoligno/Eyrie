package eyrie.file.instances

import eyrie.Emptiness
import eyrie.file.FilePath.Relative
import eyrie.file.{FilePath, IdentityFilePath, RelativeFile}

private[file]
trait RelativeInstances extends SubdivisionInstances[Emptiness, FilePath.Relative, IdentityFilePath, RelativeFile]
  with EqualityInstances[Relative]
