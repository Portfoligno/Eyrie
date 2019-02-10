package eyrie.file.instances

import eyrie.file.{AbsoluteFile, RelativeFile}

private[file]
trait AbsoluteFileInstances extends PrefixInstances[AbsoluteFile]
  with SuffixInstances[AbsoluteFile, RelativeFile]
