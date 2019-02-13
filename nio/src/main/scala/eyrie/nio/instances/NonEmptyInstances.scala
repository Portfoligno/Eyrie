package eyrie.nio.instances

import eyrie.Relativity
import eyrie.nio.FilePath.NonEmpty
import eyrie.nio.{AbsoluteFile, FilePath, RelativeFile}

private[nio]
trait NonEmptyInstances extends SubdivisionInstances[Relativity, FilePath.NonEmpty, AbsoluteFile, RelativeFile]
  with EqualityInstances[NonEmpty]
