package eyrie.file

import eyrie.file.FilePath.Relative
import eyrie.file.ops.FileConvertibleInstances

private[file]
class RelativeInstances extends FileConvertibleInstances[Relative, IdentityFilePath, RelativeFile]
