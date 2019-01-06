package eyrie.ops

import simulacrum.typeclass

trait Successor[A, B, C] {
  def parent: A => B
  def lastSegment: A => C
}

object Successor {
  @inline
  def apply[A, B, C](implicit A: Successor[A, B, C]): Successor[A, B, C] = A


  @typeclass
  trait ByInput[A] {
    type Prefix
    type Segment

    def parent: A => Prefix
    def lastSegment: A => Segment
  }

  object ByInput {
    type Aux[A, B, C] = ByInput[A] {
      type Prefix = B
      type Segment = C
    }

    implicit def eyrieByInputInstance[A, B, C](implicit A: Successor[A, B, C]): Successor.ByInput.Aux[A, B, C] =
      new Successor.ByInput[A] {
        override
        type Prefix = B

        override
        type Segment = C

        override
        def parent: A => B =
          A.parent

        override
        def lastSegment: A => C =
          A.lastSegment
      }
  }
}
