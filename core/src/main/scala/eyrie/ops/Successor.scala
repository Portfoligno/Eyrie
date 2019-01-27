package eyrie.ops

import eyrie.instances.{SuccessorByInputInstances, SuccessorInstances}
import simulacrum.typeclass

trait Successor[A, B, C] {
  def parent: A => B
  def lastSegment: A => C
}

object Successor extends SuccessorInstances {
  @inline
  def apply[A, B, C](implicit A: Successor[A, B, C]): Successor[A, B, C] = A


  @typeclass
  trait ByInput[A] {
    type Prefix
    type Segment

    def parent: A => Prefix
    def lastSegment: A => Segment
  }

  object ByInput extends SuccessorByInputInstances {
    type Aux[A, B, C] = ByInput[A] {
      type Prefix = B
      type Segment = C
    }
  }
}
