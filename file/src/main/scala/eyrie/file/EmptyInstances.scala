package eyrie.file

import eyrie.file.FilePath.Empty
import eyrie.file.ops.ConvertibleInstances

private[file]
class EmptyInstances extends ConvertibleInstances[Empty, IdentityFilePath, RootDirectory]
