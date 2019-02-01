package eyrie.ops

import eyrie.instances.{PotentialSuccessorByInputInstances, PotentialSuccessorInstances}
import simulacrum.typeclass

trait PotentialSuccessor[A, B, C] {
  def parentOption: A => Option[B]
  def lastSegmentOption: A => Option[C]
}

object PotentialSuccessor extends PotentialSuccessorInstances  {
  @inline
  def apply[A, B, C](implicit A: PotentialSuccessor[A, B, C]): PotentialSuccessor[A, B, C] = A


  @typeclass
  trait ByInput[A] {
    type Out
    type Segment

    def parentOption: A => Option[Out]
    def lastSegmentOption: A => Option[Segment]
  }

  object ByInput extends PotentialSuccessorByInputInstances {
    type Aux[A, B, C] = ByInput[A] {
      type Out = B
      type Segment = C
    }
  }
}
