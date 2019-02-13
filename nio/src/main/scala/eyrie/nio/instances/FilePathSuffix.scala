package eyrie.nio.instances

import eyrie.nio.FilePath
import eyrie.ops.Suffix

private[instances]
trait SuffixInstances[A[_], B[_]] {
  implicit def eyrieNioSuffixInstance[C]: Suffix[A[C], B[C]] =
    FilePathSuffix.asInstanceOf[Suffix[A[C], B[C]]]
}

private
object FilePathSuffix extends Suffix[FilePath[Any], FilePath[Any]] {
  import eyrie.nio.syntax.asJava._

  override
  def endsWith: (FilePath[Any], FilePath[Any]) => Boolean =
    (x, y) => x.asJava.endsWith(y.asJava)
}
