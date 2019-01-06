package eyrie.file.instances

import eyrie.Relativity
import eyrie.file.FilePath.Empty
import eyrie.file.{FilePath, IdentityFilePath, RootDirectory}

private[file]
trait EmptyInstances extends SubdivisionInstances[Relativity, FilePath.Empty, IdentityFilePath, RootDirectory]
  with EqualityInstances[Empty]