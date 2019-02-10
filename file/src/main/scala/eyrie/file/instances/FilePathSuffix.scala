package eyrie.file.instances

import eyrie.file.FilePath
import eyrie.ops.Suffix

private[instances]
trait SuffixInstances[A[_], B[_]] {
  implicit def eyrieFileSuffixInstance[C]: Suffix[A[C], B[C]] =
    FilePathSuffix.asInstanceOf[Suffix[A[C], B[C]]]
}

private
object FilePathSuffix extends Suffix[FilePath[Any], FilePath[Any]] {
  import eyrie.file.syntax.asJava._

  override
  def endsWith: (FilePath[Any], FilePath[Any]) => Boolean =
    (x, y) => x.asJava.endsWith(y.asJava)
}
