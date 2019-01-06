package eyrie.file

import eyrie.file.FilePath.Empty
import eyrie.file.ops.{EqualityInstances, SubdivisionInstances}

private[file]
trait EmptyInstances extends SubdivisionInstances[Relativity, FilePath.Empty, IdentityFilePath, RootDirectory]
  with EqualityInstances[Empty]
