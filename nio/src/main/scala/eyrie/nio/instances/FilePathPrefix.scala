package eyrie.nio.instances

import eyrie.nio.FilePath
import eyrie.ops.Prefix

private[instances]
trait PrefixInstances[A[_]] extends EqualityInstances[A] {
  implicit def eyrieNioPrefixInstance[C]: Prefix[A[C]] =
    FilePathPrefix.asInstanceOf[Prefix[A[C]]]
}

private
object FilePathPrefix extends Prefix[FilePath[Any]] {
  import eyrie.nio.syntax.asJava._

  override
  def startsWith: (FilePath[Any], FilePath[Any]) => Boolean =
    (x, y) => x.asJava.startsWith(y.asJava)
}
