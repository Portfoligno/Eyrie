package eyrie.file

import eyrie.file.FilePath.NonEmpty
import eyrie.file.ops.{EqualityInstances, SubdivisionInstances}

private[file]
trait NonEmptyInstances extends SubdivisionInstances[Relativity, FilePath.NonEmpty, RelativeFile, AbsoluteFile]
  with EqualityInstances[NonEmpty]
