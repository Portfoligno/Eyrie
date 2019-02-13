package eyrie.ops

import eyrie.instances.{DiPartialSuccessorByInputInstances, DiPartialSuccessorInstances}
import simulacrum.typeclass

trait DiPartialSuccessor[A, L, R, C] {
  def parentEitherOption: A => Option[Either[L, R]]
  def lastSegmentOption: A => Option[C]
}

object DiPartialSuccessor extends DiPartialSuccessorInstances {
  @inline
  def apply[A, L, R, C](implicit A: DiPartialSuccessor[A, L, R, C]): DiPartialSuccessor[A, L, R, C] = A


  @typeclass
  trait ByInput[A] {
    type Left
    type Right
    type Segment

    def parentEitherOption: A => Option[Either[Left, Right]]
    def lastSegmentOption: A => Option[Segment]
  }

  object ByInput extends DiPartialSuccessorByInputInstances {
    type Aux[A, L, R, C] = ByInput[A] {
      type Left = L
      type Right = R
      type Segment = C
    }
  }
}
