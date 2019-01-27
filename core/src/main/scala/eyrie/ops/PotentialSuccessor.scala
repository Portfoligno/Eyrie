package eyrie.ops

import eyrie.instances.{PotentialSuccessorByInputInstances, PotentialSuccessorInstances}
import simulacrum.typeclass

trait PotentialSuccessor[A, C] {
  def parentOption: A => Option[A]
  def lastSegmentOption: A => Option[C]
}

object PotentialSuccessor extends PotentialSuccessorInstances  {
  @inline
  def apply[A, C](implicit A: PotentialSuccessor[A, C]): PotentialSuccessor[A, C] = A


  @typeclass
  trait ByInput[A] {
    type Segment

    def parentOption: A => Option[A]
    def lastSegmentOption: A => Option[Segment]
  }

  object ByInput extends PotentialSuccessorByInputInstances {
    type Aux[A, C] = ByInput[A] {
      type Segment = C
    }
  }
}
