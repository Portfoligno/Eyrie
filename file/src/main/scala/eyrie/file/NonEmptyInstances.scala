package eyrie.file

import eyrie.file.FilePath.NonEmpty
import eyrie.file.ops.FileConvertibleInstances

private[file]
class NonEmptyInstances extends FileConvertibleInstances[NonEmpty, RelativeFile, AbsoluteFile]
