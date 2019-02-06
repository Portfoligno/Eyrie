package eyrie.file.instances

import eyrie.file.FilePath
import eyrie.ops.Prefix

private[instances]
trait PrefixInstances[A[_]] extends EqualityInstances[A] {
  implicit def eyrieFilePrefixHierarchyInstance[C]: Prefix[A[C]] =
    FilePathPrefix.asInstanceOf[Prefix[A[C]]]
}

private
object FilePathPrefix extends Prefix[FilePath[Any]] {
  import eyrie.file.syntax.asJava._

  override
  def startsWith: (FilePath[Any], FilePath[Any]) => Boolean =
    (x, y) => x.asJava.startsWith(y.asJava)
}
