package eyrie.file.instances

import eyrie.ops.Equality

private
object FilePathEquality extends Equality[Any] {
  override
  def equal(x: Any, y: Any): Boolean =
    x == y
}

private[instances]
trait EqualityInstances[A[_]] extends AsJavaInstances[A] {
  implicit def eyrieFileEqualityInstance[C]: Equality[A[C]] =
    FilePathEquality.asInstanceOf[Equality[A[C]]]
}
