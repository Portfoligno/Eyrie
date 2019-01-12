package eyrie.file.instances

import eyrie.file.FilePath.Relative
import eyrie.file.{Emptiness, FilePath, IdentityFilePath, RelativeFile}

private[file]
trait RelativeInstances extends SubdivisionInstances[Emptiness, FilePath.Relative, IdentityFilePath, RelativeFile]
  with EqualityInstances[Relative]
