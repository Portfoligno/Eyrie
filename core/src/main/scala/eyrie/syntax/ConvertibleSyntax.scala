package eyrie.syntax

import eyrie.ops.{AttributeDiscard, Convertible}

trait ConvertibleSyntax {
  implicit def toConvertOps[A](a: A): ConvertibleOps[A] =
    new ConvertibleOps(a)
}

final class ConvertibleOps[A](private val a: A) extends AnyVal {
  def widen[B](implicit F: Convertible[A, B]): B =
    F.widen(a)

  def toOption[B](implicit F: Convertible[B, A]): Option[B] =
    F.narrow(a)

  def widening[B[_]](implicit F: AttributeDiscard[B, A]): F.Out =
    F.widen(a)
}
