package eyrie.ops

import eyrie.instances.{DiPotentialSuccessorByInputInstances, DiPotentialSuccessorInstances}
import simulacrum.typeclass

trait DiPotentialSuccessor[A, L, R, C] {
  def parentEitherOption: A => Option[Either[L, R]]
  def lastSegmentOption: A => Option[C]
}

object DiPotentialSuccessor extends DiPotentialSuccessorInstances {
  @inline
  def apply[A, L, R, C](implicit A: DiPotentialSuccessor[A, L, R, C]): DiPotentialSuccessor[A, L, R, C] = A


  @typeclass
  trait ByInput[A] {
    type Left
    type Right
    type Segment

    def parentEitherOption: A => Option[Either[Left, Right]]
    def lastSegmentOption: A => Option[Segment]
  }

  object ByInput extends DiPotentialSuccessorByInputInstances {
    type Aux[A, L, R, C] = ByInput[A] {
      type Left = L
      type Right = R
      type Segment = C
    }
  }
}
