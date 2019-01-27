package eyrie.instances

import eyrie.ops.{Descendant, TrivialDescendant}

private[eyrie]
trait DescendantByInputInstances extends LowPriorityDescendantByInputInstances {
  implicit def eyrieTrivialByInputInstance[A](
    implicit A: TrivialDescendant[A]
  ): Descendant.ByInput.Aux[A, A] =
    eyrieAmbiguousTrivialByInputInstance

  implicit def eyrieAmbiguousTrivialByInputInstance[A](
    implicit A: TrivialDescendant[A]
  ): Descendant.ByInput.Aux[A, A] =
    new Descendant.ByInput[A] {
      override
      type Root = A

      override
      def root: A => A =
        A.root
    }
}

private[eyrie]
trait LowPriorityDescendantByInputInstances {
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
