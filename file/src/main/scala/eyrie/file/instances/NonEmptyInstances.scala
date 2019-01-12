package eyrie.file.instances

import eyrie.Relativity
import eyrie.file.FilePath.NonEmpty
import eyrie.file.{AbsoluteFile, FilePath, RelativeFile}

private[file]
trait NonEmptyInstances extends SubdivisionInstances[Relativity, FilePath.NonEmpty, RelativeFile, AbsoluteFile]
  with EqualityInstances[NonEmpty]
