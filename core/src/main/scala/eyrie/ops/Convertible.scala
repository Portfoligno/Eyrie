package eyrie.ops

trait Convertible[A, B] {
  type Attribute[_]

  def widen: A => B

  def narrow: B => Option[A]
}

object Convertible {
  type Aux[A, B, C[_]] = Convertible[A, B] {
    type Attribute[X] = C[X]
  }

  @inline
  def apply[A, B](implicit F: Convertible[A, B]): Convertible[A, B] = F
}
