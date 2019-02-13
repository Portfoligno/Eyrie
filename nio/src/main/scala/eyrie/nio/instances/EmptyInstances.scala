package eyrie.nio.instances

import eyrie.Relativity
import eyrie.nio.FilePath.Empty
import eyrie.nio.{FilePath, IdentityFilePath, RootDirectory}

private[nio]
trait EmptyInstances extends SubdivisionInstances[Relativity, FilePath.Empty, RootDirectory, IdentityFilePath]
  with EqualityInstances[Empty]
