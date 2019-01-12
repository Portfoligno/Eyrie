package eyrie.file.instances

import eyrie.file.FilePath.Absolute
import eyrie.file.{AbsoluteFile, Emptiness, FilePath, RootDirectory}

private[file]
trait AbsoluteInstances extends SubdivisionInstances[Emptiness, FilePath.Absolute, RootDirectory, AbsoluteFile]
  with EqualityInstances[Absolute]
