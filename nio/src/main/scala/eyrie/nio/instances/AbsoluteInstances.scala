package eyrie.nio.instances

import eyrie.Emptiness
import eyrie.nio.FilePath.Absolute
import eyrie.nio.{AbsoluteFile, FilePath, RootDirectory}

private[nio]
trait AbsoluteInstances extends SubdivisionInstances[Emptiness, FilePath.Absolute, AbsoluteFile, RootDirectory]
  with EqualityInstances[Absolute]
