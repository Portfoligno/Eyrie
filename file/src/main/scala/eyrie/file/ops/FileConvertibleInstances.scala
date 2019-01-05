package eyrie.file.ops

import eyrie.ops.Convertible

import scala.reflect.ClassTag

private[file]
class FileConvertible[A[_], B[_]](implicit A: ClassTag[A[_]]) extends Convertible[A[_], B[_]] {
  override
  def widen(a: A[_]): B[_] =
    a.asInstanceOf[B[_]]

  override
  def narrow(b: B[_]): Option[A[_]] =
    A.unapply(b)
}

private[file]
class FileConvertibleInstances[Z[_], I[_], J[_]](implicit I: ClassTag[I[_]], J: ClassTag[J[_]])
  extends FileEqualityInstances[Z] {

  private
  lazy val _leftConvertibleInstance = new FileConvertible[I, Z]

  private
  lazy val _rightConvertibleInstance = new FileConvertible[J, Z]

  implicit def leftFileConvertibleInstance[C]: Convertible[I[C], Z[C]] =
    _leftConvertibleInstance.asInstanceOf[Convertible[I[C], Z[C]]]

  implicit def rightFileConvertibleInstance[C]: Convertible[J[C], Z[C]] =
    _rightConvertibleInstance.asInstanceOf[Convertible[J[C], Z[C]]]
}
