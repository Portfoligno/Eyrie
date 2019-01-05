package eyrie.file

import eyrie.file.FilePath.NonEmpty
import eyrie.file.ops.ConvertibleInstances

private[file]
class NonEmptyInstances extends ConvertibleInstances[NonEmpty, RelativeFile, AbsoluteFile]
