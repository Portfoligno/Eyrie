package eyrie.syntax

import eyrie.ops.Convertible
import eyrie.ops.attribute.{Emptiness, Relativity}

trait ConvertibleSyntax {
  implicit def toConvertOps[A](a: A): ConvertibleOps[A] =
    new ConvertibleOps(a)
}

final class ConvertibleOps[A](private val a: A) extends AnyVal {
  def widen[B](implicit F: Convertible[A, B]): B =
    F.widen(a)

  def widenEmptiness[B](implicit F: Convertible.Aux[A, B, Emptiness]): B =
    F.widen(a)

  def widenRelativity[B](implicit F: Convertible.Aux[A, B, Relativity]): B =
    F.widen(a)

  def toOption[B](implicit F: Convertible[B, A]): Option[B] =
    F.narrow(a)
}
