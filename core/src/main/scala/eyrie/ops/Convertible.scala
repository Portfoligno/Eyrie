package eyrie.ops

trait Convertible[A, B] {
  type Attribute[_]

  def widen(a: A): B

  def narrow(b: B): Option[A]
}

object Convertible {
  type Aux[A, B, C[_]] = Convertible[A, B] {
    type Attribute[X] = C[X]
  }

  @inline
  def apply[A, B](implicit convertible: Convertible[A, B]): Convertible[A, B] = convertible
}
