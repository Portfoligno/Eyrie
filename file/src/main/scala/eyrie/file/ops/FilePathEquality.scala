package eyrie.file.ops

import eyrie.ops.Equality

private
object FilePathEquality extends Equality[Any] {
  override
  def equal(x: Any, y: Any): Boolean =
    x == y
}

private[file]
trait EqualityInstances[A[_]] {
  implicit def eyrieFileEqualityInstance[C]: Equality[A[C]] =
    FilePathEquality.asInstanceOf[Equality[A[C]]]
}
