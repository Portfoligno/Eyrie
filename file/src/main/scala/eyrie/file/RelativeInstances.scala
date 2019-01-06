package eyrie.file

import eyrie.file.FilePath.Relative
import eyrie.file.ops.{EqualityInstances, SubdivisionInstances}

private[file]
trait RelativeInstances extends SubdivisionInstances[Emptiness, FilePath.Relative, IdentityFilePath, RelativeFile]
  with EqualityInstances[Relative]
