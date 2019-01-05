package eyrie.file

import eyrie.file.FilePath.Relative
import eyrie.file.ops.ConvertibleInstances

private[file]
class RelativeInstances extends ConvertibleInstances[Relative, IdentityFilePath, RelativeFile]
