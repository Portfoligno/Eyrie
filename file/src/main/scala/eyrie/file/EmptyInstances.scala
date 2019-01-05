package eyrie.file

import eyrie.file.FilePath.Empty
import eyrie.file.ops.FileConvertibleInstances

private[file]
class EmptyInstances extends FileConvertibleInstances[Empty, IdentityFilePath, RootDirectory]
