package eyrie.ops

import eyrie.instances.{PotentialDescendantByInputInstances, PotentialDescendantInstances, PotentialDescendantTrivialByInputInstances}
import simulacrum.typeclass

trait PotentialDescendant[A, B] {
  def rootOption: A => Option[B]
}

object PotentialDescendant extends PotentialDescendantInstances {
  @inline
  def apply[A, B](implicit A: PotentialDescendant[A, B]): PotentialDescendant[A, B] = A


  @typeclass
  trait ByInput[A] {
    type Root

    def rootOption: A => Option[Root]
  }

  object ByInput extends PotentialDescendantByInputInstances {
    type Aux[A, B] = ByInput[A] {
      type Root = B
    }
  }

  @typeclass
  trait TrivialByInput[A] {
    type Root

    def asRoot: A => Option[Root]
  }

  object TrivialByInput extends PotentialDescendantTrivialByInputInstances {
    type Aux[A, B] = TrivialByInput[A] {
      type Root = B
    }
  }
}
