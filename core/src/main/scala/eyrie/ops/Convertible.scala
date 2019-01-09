package eyrie.ops

trait Convertible[A, B] {
  type Attribute[_]

  def widen: A => B

  def narrow: B => Option[A]
}

private
final class ConvertibleByAttributeAux[Attr[_], A, B](
  override val widen: A => B
) extends AnyVal with Convertible.ByAttribute[Attr, A] {
  override
  type Out = B
}

object Convertible {
  type Aux[Attr[_], A, B] = Convertible[A, B] {
    type Attribute[X] = Attr[X]
  }

  @inline
  def apply[A, B](implicit F: Convertible[A, B]): Convertible[A, B] = F


  trait ByAttribute[Attr[_], A] extends Any {
    type Out

    def widen: A => Out
  }

  object ByAttribute {
    type Aux[Attr[_], A, B] = ByAttribute[Attr, A] {
      type Out = B
    }

    @inline
    def apply[Attr[_], A](implicit F: ByAttribute[Attr, A]): ByAttribute[Attr, A] = F

    implicit def eyrieByAttributeInstance[Attr[_], A, B](implicit F: Convertible.Aux[Attr, A, B]): Aux[Attr, A, B] =
      new ConvertibleByAttributeAux(F.widen)
  }
}
