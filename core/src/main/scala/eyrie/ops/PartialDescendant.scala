package eyrie.ops

import eyrie.instances.{PartialDescendantByInputInstances, PartialDescendantInstances, PartialDescendantTrivialByInputInstances}
import simulacrum.typeclass

trait PartialDescendant[A, B] {
  def rootOption: A => Option[B]
}

object PartialDescendant extends PartialDescendantInstances {
  @inline
  def apply[A, B](implicit A: PartialDescendant[A, B]): PartialDescendant[A, B] = A


  @typeclass
  trait ByInput[A] {
    type Root

    def rootOption: A => Option[Root]
  }

  object ByInput extends PartialDescendantByInputInstances {
    type Aux[A, B] = ByInput[A] {
      type Root = B
    }
  }

  @typeclass
  trait TrivialByInput[A] {
    type Root

    def asRoot: A => Option[Root]
  }

  object TrivialByInput extends PartialDescendantTrivialByInputInstances {
    type Aux[A, B] = TrivialByInput[A] {
      type Root = B
    }
  }
}
