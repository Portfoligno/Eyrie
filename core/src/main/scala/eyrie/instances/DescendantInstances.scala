package eyrie.instances

import eyrie.ops.Descendant

private[eyrie]
trait DescendantByInputInstances {
  implicit def eyrieByInputInstance[A, B](
    implicit A: Descendant[A, B]
  ): Descendant.ByInput.Aux[A, B] =
    new Descendant.ByInput[A] {
      override
      type Root = B

      override
      def root: A => B =
        A.root
    }
}
