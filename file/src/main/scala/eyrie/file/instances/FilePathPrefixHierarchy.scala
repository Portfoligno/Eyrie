package eyrie.file.instances

import eyrie.file.FilePath
import eyrie.ops.PrefixHierarchy

private[instances]
trait PrefixHierarchyInstances[A[_]] extends EqualityInstances[A] {
  implicit def eyrieFilePrefixHierarchyInstance[C]: PrefixHierarchy[A[C]] =
    FilePathPrefixHierarchy.asInstanceOf[PrefixHierarchy[A[C]]]
}

private
object FilePathPrefixHierarchy extends PrefixHierarchy[FilePath[Any]] {
  import eyrie.file.syntax.asJava._

  override
  def startsWith(x: FilePath[Any], y: FilePath[Any]): Boolean =
    x.asJava.startsWith(y.asJava)
}
