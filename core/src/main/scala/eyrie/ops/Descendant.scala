package eyrie.ops

import simulacrum.typeclass

trait Descendant[A, B, C, D] extends Successor[A, B, C] {
  def root: A => D
}

object Descendant {
  @inline
  def apply[A, B, C, D](implicit A: Descendant[A, B, C, D]): Descendant[A, B, C, D] = A


  @typeclass
  trait ByInput[A] extends Successor.ByInput[A] {
    type Root

    def root: A => Root
  }

  object ByInput {
    type Aux[A, B, C, D] = ByInput[A] {
      type Prefix = B
      type Segment = C
      type Root = D
    }

    implicit def eyrieByInputInstance[A, B, C, D](
      implicit A: Descendant[A, B, C, D]
    ): Descendant.ByInput.Aux[A, B, C, D] =
      new Descendant.ByInput[A] {
        override
        type Prefix = B

        override
        type Segment = C

        override
        type Root = D

        override
        def parent: A => B =
          A.parent

        override
        def lastSegment: A => C =
          A.lastSegment

        override
        def root: A => D =
          A.root
      }
  }
}
