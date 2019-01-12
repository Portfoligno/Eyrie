package eyrie.file.instances

import eyrie.file.FilePath.Empty
import eyrie.file.{FilePath, IdentityFilePath, Relativity, RootDirectory}

private[file]
trait EmptyInstances extends SubdivisionInstances[Relativity, FilePath.Empty, IdentityFilePath, RootDirectory]
  with EqualityInstances[Empty]
