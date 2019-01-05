package eyrie.file

import eyrie.file.FilePath.Absolute
import eyrie.file.ops.FileConvertibleInstances

private[file]
class AbsoluteInstances extends FileConvertibleInstances[Absolute, RootDirectory, AbsoluteFile]
