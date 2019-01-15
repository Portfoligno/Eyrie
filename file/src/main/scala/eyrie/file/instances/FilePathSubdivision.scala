package eyrie.file.instances

import cats.syntax.either._
import eyrie.ops.Subdivision

import scala.reflect.ClassTag

private[instances]
class FilePathSubdivision[Attr[_], A[_], L[_], R[_]](
  implicit L: ClassTag[L[_]], R: ClassTag[R[_]]
) extends Subdivision[A[_], L[_], R[_]] {
  override
  type Attribute[X] = Attr[X]

  override
  def subdivide: A[_] => Either[L[_], R[_]] = {
    case L(a) => a.asLeft[R[_]]
    case R(a) => a.asRight[L[_]]
  }


  def of[C]: Subdivision.Aux[Attr, A[C], L[C], R[C]] =
    asInstanceOf[Subdivision.Aux[Attr, A[C], L[C], R[C]]]
}

private[instances]
class SubdivisionInstances[Attr[_], A[_], L[_], R[_]](implicit L: ClassTag[L[_]], R: ClassTag[R[_]]) {
  private
  lazy val _subdivisionInstance = new FilePathSubdivision[Attr, A, L, R]

  implicit def eyrieFileSubDivisionInstance[C]: Subdivision.Aux[Attr, A[C], L[C], R[C]] =
    _subdivisionInstance.of[C]
}
