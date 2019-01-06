package eyrie.file

import eyrie.file.FilePath.NonEmpty
import eyrie.file.ops.EqualityInstances

private[file]
class NonEmptyInstances extends EqualityInstances[NonEmpty]
