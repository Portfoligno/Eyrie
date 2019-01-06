package eyrie.ops

trait AttributeConvertible[A[_], B] {
  type Out

  def widen: B => Out

  def narrow: Out => Option[B]
}

object AttributeConvertible {
  type Aux[A[_], B, C] = AttributeConvertible[A, B] {
    type Out = C
  }

  @inline
  def apply[A[_], B](implicit F: AttributeConvertible[A, B]): AttributeConvertible[A, B] = F

  implicit def convertibleAttributeConvertibleInstance[A, B, C[_]](
    implicit convertible: Convertible.Aux[A, B, C]
  ): AttributeConvertible.Aux[C, A, B] =
    new AttributeConvertible[C, A] {
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
