package eyrie.ops

trait AttributeDiscard[Attr[_], A] extends Any {
  type Out

  def widen: A => Out
}

private
final class AttributeDiscardAux[Attr[_], A, B](
  override val widen: A => B
) extends AnyVal with AttributeDiscard[Attr, A] {

  override
  type Out = B
}

object AttributeDiscard {
  type Aux[Attr[_], A, B] = AttributeDiscard[Attr, A] {
    type Out = B
  }

  @inline
  def apply[Attr[_], A](implicit F: AttributeDiscard[Attr, A]): AttributeDiscard[Attr, A] = F

  implicit def convertibleAttributeConvertibleInstance[Attr[_], A, B](
    implicit convertible: Convertible.Aux[Attr, A, B]
  ): AttributeDiscard.Aux[Attr, A, B] =
    new AttributeDiscardAux(convertible.widen)
}
