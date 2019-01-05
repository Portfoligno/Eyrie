package eyrie.file.ops

import eyrie.ops.Convertible

import scala.reflect.ClassTag

private[file]
class FilePathConvertible[A[_], B[_]](implicit A: ClassTag[A[_]]) extends Convertible[A[_], B[_]] {
  override
  def widen(a: A[_]): B[_] =
    a.asInstanceOf[B[_]]

  override
  def narrow(b: B[_]): Option[A[_]] =
    A.unapply(b)
}

private[file]
class ConvertibleInstances[Z[_], I[_], J[_]](implicit I: ClassTag[I[_]], J: ClassTag[J[_]])
  extends EqualityInstances[Z] {

  private
  lazy val _leftConvertibleInstance = new FilePathConvertible[I, Z]

  private
  lazy val _rightConvertibleInstance = new FilePathConvertible[J, Z]

  implicit def leftFileConvertibleInstance[C]: Convertible[I[C], Z[C]] =
    _leftConvertibleInstance.asInstanceOf[Convertible[I[C], Z[C]]]

  implicit def rightFileConvertibleInstance[C]: Convertible[J[C], Z[C]] =
    _rightConvertibleInstance.asInstanceOf[Convertible[J[C], Z[C]]]
}
