package eyrie.instances

import eyrie.ops.Convertible

private[eyrie]
trait ConvertibleByAttributeInstances {
  implicit def eyrieByAttributeInstance[Attr[_], A, B](
    implicit F: Convertible.Aux[Attr, _, A, B]
  ): Convertible.ByAttribute.Aux[Attr, A, B] =
    new Convertible.ByAttribute[Attr, A] {
      override
      type Out = B

      override
      def widen: A => B =
        F.widen

      override
      def narrow: B => Option[A] =
        F.narrow
    }
}

private[eyrie]
trait ConvertibleByQualityInstances {
  implicit def eyrieByQualityInstance[Attr[_], Param, A, B](
    implicit F: Convertible.Aux[Attr, Param, A, B]
  ): Convertible.ByQuality.Aux[Attr[Param], A, B] =
    new Convertible.ByQuality[Attr[Param], B] {
      override
      type Out = A

      override
      def widen: A => B =
        F.widen

      override
      def narrow: B => Option[A] =
        F.narrow
    }
}
