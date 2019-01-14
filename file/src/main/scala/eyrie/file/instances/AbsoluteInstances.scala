package eyrie.file.instances

import eyrie.Emptiness
import eyrie.file.FilePath.Absolute
import eyrie.file.{AbsoluteFile, FilePath, RootDirectory}

private[file]
trait AbsoluteInstances extends SubdivisionInstances[Emptiness, FilePath.Absolute, AbsoluteFile, RootDirectory]
  with EqualityInstances[Absolute]
