package eyrie.file

import eyrie.file.ops.{FilePathConvertible, EqualityInstances}
import eyrie.ops.Convertible

private[file]
trait FileInstances extends EqualityInstances[FilePath]
