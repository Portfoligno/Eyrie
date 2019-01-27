package eyrie.instances

import eyrie.ops.Descendant

private[eyrie]
trait DescendantByInputInstances {
  implicit def eyrieByInputInstance[A, B, C, D](
    implicit A: Descendant[A, B, C, D]
  ): Descendant.ByInput.Aux[A, B, C, D] =
    new Descendant.ByInput[A] {
      override
      type Prefix = B
      override
      type Segment = C
      override
      type Root = D

      override
      def parent: A => B =
        A.parent

      override
      def lastSegment: A => C =
        A.lastSegment

      override
      def root: A => D =
        A.root
    }
}
