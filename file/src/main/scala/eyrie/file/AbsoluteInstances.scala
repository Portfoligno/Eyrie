package eyrie.file

import eyrie.file.FilePath.Absolute
import eyrie.file.ops.{EqualityInstances, SubdivisionInstances}

private[file]
trait AbsoluteInstances extends SubdivisionInstances[Emptiness, FilePath.Absolute, RootDirectory, AbsoluteFile]
  with EqualityInstances[Absolute]
