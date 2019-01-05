package eyrie.ops

trait Convertible[A, B] {
  def widen(a: A): B

  def narrow(b: B): Option[A]
}

object Convertible {
  @inline
  def apply[A, B](implicit convertible: Convertible[A, B]): Convertible[A, B] = convertible
}
