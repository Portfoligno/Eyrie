package eyrie.ops

trait Convertible[A, B] {
  type Attribute[_]
  type Parameter

  def widen: A => B

  def narrow: B => Option[A]
}

object Convertible {
  type Aux[Attr[_], Param, A, B] = Convertible[A, B] {
    type Attribute[X] = Attr[X]
    type Parameter = Param
  }

  @inline
  def apply[A, B](implicit F: Convertible[A, B]): Convertible[A, B] = F


  trait Widen[Attr[_], A] extends Any {
    type Out

    def widen: A => Out
  }

  object Widen {
    type Aux[Attr[_], A, B] = Widen[Attr, A] {
      type Out = B
    }

    @inline
    def apply[Attr[_], A](implicit F: Widen[Attr, A]): Widen[Attr, A] = F

    implicit def eyrieWidenInstance[Attr[_], A, B](implicit F: Convertible.Aux[Attr, _, A, B]): Aux[Attr, A, B] =
      new AuxImpl(F.widen)

    private
    final class AuxImpl[Attr[_], A, B](override val widen: A => B) extends AnyVal with Widen[Attr, A] {
      override
      type Out = B
    }
  }
}
