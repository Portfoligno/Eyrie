package eyrie.file.ops

import eyrie.ops.Equality

private
object FileEquality extends Equality[Any] {
  override
  def equal(x: Any, y: Any): Boolean =
    x == y
}

private[file]
trait FileEqualityInstances[A[_]] {
  implicit def equalityInstance[C]: Equality[A[C]] =
    FileEquality.asInstanceOf[Equality[A[C]]]
}
