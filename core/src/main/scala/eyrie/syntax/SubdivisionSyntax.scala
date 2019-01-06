package eyrie.syntax

import eyrie.ops.Subdivision

trait SubdivisionSyntax {
  implicit def toSubdivisionOps[A](a: A): SubdivisionOps[A] =
    new SubdivisionOps(a)
}

final class SubdivisionOps[A](private val a: A) extends AnyVal {
  def subdivide(implicit F: Subdivision[A]): Either[F.Left, F.Right] =
    F.subdivide(a)

  def subdivideBy[Attr[_]](implicit F: Subdivision.ByAttribute[Attr, A]): Either[F.Left, F.Right] =
    F.subdivide(a)
}
