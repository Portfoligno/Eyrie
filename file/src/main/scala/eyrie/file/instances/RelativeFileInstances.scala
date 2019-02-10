package eyrie.file.instances

import eyrie.file.RelativeFile

private[file]
trait RelativeFileInstances extends PrefixInstances[RelativeFile]
  with SuffixInstances[RelativeFile, RelativeFile]
