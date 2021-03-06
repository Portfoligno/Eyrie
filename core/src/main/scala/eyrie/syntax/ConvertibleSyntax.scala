package eyrie.syntax

import eyrie.ops.Convertible

trait ConvertibleSyntax {
  implicit def toConvertibleOps[A](a: A): ConvertibleOps[A] =
    new ConvertibleOps(a)
}

final class ConvertibleOps[A](private val a: A) extends AnyVal {
  def widen[B](implicit F: Convertible[A, B]): B =
    F.widen(a)

  def widenBy[Attr[_]](implicit F: Convertible.ByAttribute[Attr, A]): F.Out =
    F.widen(a)

  def narrow[B](implicit F: Convertible[B, A]): Option[B] =
    F.narrow(a)

  def narrowBy[Qual](implicit F: Convertible.ByQuality[Qual, A]): Option[F.Out] =
    F.narrow(a)

  def as[B](implicit F: Convertible[B, A]): Option[B] =
    F.narrow(a)
}
