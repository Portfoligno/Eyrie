package eyrie.file

import eyrie.file.FilePath.Absolute
import eyrie.file.ops.ConvertibleInstances

private[file]
class AbsoluteInstances extends ConvertibleInstances[Absolute, RootDirectory, AbsoluteFile]
