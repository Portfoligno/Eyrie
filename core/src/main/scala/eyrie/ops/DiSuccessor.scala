package eyrie.ops

import eyrie.instances.{DiSuccessorByInputInstances, DiSuccessorInstances}
import simulacrum.typeclass

trait DiSuccessor[A, L, R, C] {
  def parentEither: A => Either[L, R]
  def lastSegment: A => C
}

object DiSuccessor extends DiSuccessorInstances {
  @inline
  def apply[A, L, R, C](implicit A: DiSuccessor[A, L, R, C]): DiSuccessor[A, L, R, C] = A


  @typeclass
  trait ByInput[A] {
    type LeftParent
    type RightParent
    type Segment

    def parentEither: A => Either[LeftParent, RightParent]
    def lastSegment: A => Segment
  }

  object ByInput extends DiSuccessorByInputInstances {
    type Aux[A, L, R, C] = ByInput[A] {
      type LeftParent = L
      type RightParent = R
      type Segment = C
    }
  }
}
