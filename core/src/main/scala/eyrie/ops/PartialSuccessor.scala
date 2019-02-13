package eyrie.ops

import eyrie.instances.{PartialSuccessorByInputInstances, PartialSuccessorInstances}
import simulacrum.typeclass

trait PartialSuccessor[A, B, C] {
  def parentOption: A => Option[B]
  def lastSegmentOption: A => Option[C]
}

object PartialSuccessor extends PartialSuccessorInstances  {
  @inline
  def apply[A, B, C](implicit A: PartialSuccessor[A, B, C]): PartialSuccessor[A, B, C] = A


  @typeclass
  trait ByInput[A] {
    type Out
    type Segment

    def parentOption: A => Option[Out]
    def lastSegmentOption: A => Option[Segment]
  }

  object ByInput extends PartialSuccessorByInputInstances {
    type Aux[A, B, C] = ByInput[A] {
      type Out = B
      type Segment = C
    }
  }
}
