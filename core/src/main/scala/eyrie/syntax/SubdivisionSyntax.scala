package eyrie.syntax

import eyrie.ops.Subdivision

trait SubdivisionSyntax {
  implicit def toSubdivisionOps[A](a: A): SubdivisionOps[A] =
    new SubdivisionOps(a)
}

final class SubdivisionOps[A](private val a: A) extends AnyVal {
  def subdivide(implicit A: Subdivision.ByInput[A]): Either[A.Left, A.Right] =
    A.subdivide(a)

  def subdivideBy[Attr[_]](implicit A: Subdivision.ByAttribute[Attr, A]): Either[A.Left, A.Right] =
    A.subdivide(a)
}
