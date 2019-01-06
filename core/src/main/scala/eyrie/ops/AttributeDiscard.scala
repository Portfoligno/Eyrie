package eyrie.ops

trait AttributeDiscard[Attr[_], A] {
  type Out

  def widen: A => Out

  def narrow: Out => Option[A]
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
    new AttributeDiscard[Attr, A] {
      override
      type Out = B

      override
      def widen: A => B =
        convertible.widen

      override
      def narrow: B => Option[A] =
        convertible.narrow
    }
}
