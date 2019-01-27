package eyrie.instances

import eyrie.ops.Subdivision

private[eyrie]
trait SubdivisionByAttributeInstances {
  implicit def eyrieByAttributeInstance[Attr[_], A, L, R](
    implicit F: Subdivision.Aux[Attr, A, L, R]
  ): Subdivision.ByAttribute.Aux[Attr, A, L, R] =
    new Subdivision.ByAttribute[Attr, A] {
      override
      type Left = L
      override
      type Right = R

      override
      def subdivide: A => Either[L, R] =
        F.subdivide
    }
}

private[eyrie]
trait SubdivisionByInputInstances {
  implicit def eyrieByInputInstance[Attr[_], A, L, R](
    implicit F: Subdivision.Aux[Attr, A, L, R]
  ): Subdivision.ByInput.Aux[Attr, A, L, R] =
    new Subdivision.ByInput[A] {
      override
      type Attribute[X] = Attr[X]
      override
      type Left = L
      override
      type Right = R

      override
      def subdivide: A => Either[L, R] =
        F.subdivide
    }
}
