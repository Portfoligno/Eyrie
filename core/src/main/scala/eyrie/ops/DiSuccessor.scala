package eyrie.ops

import cats.syntax.either._
import simulacrum.typeclass

trait DiSuccessor[A, L, R, C] {
  def parentEither: A => Either[L, R]
  def lastSegment: A => C
}

object DiSuccessor {
  @inline
  def apply[A, L, R, C](implicit A: DiSuccessor[A, L, R, C]): DiSuccessor[A, L, R, C] = A

  implicit def eyrieSubdivisionBasedInstance[A, LA, RA, LB, RB, C](
    implicit
    A: Subdivision[A, LA, RA],
    LA: Successor[LA, LB, C],
    RA: Successor[RA, RB, C]
  ): DiSuccessor[A, LB, RB, C] =
    new DiSuccessor[A, LB, RB, C] {
      override
      def parentEither: A => Either[LB, RB] =
        A.subdivide(_).bimap(LA.parent, RA.parent)

      override
      def lastSegment: A => C =
        A.subdivide(_).fold(LA.lastSegment, RA.lastSegment)
    }


  @typeclass
  trait ByInput[A] {
    type LeftParent
    type RightParent
    type Segment

    def parentEither: A => Either[LeftParent, RightParent]
    def lastSegment: A => Segment
  }

  object ByInput {
    type Aux[A, L, R, C] = ByInput[A] {
      type LeftParent = L
      type RightParent = R
      type Segment = C
    }

    implicit def eyrieByInputInstance[A, L, R, C](implicit A: DiSuccessor[A, L, R, C]): Aux[A, L, R, C] =
      new ByInput[A] {
        override
        type LeftParent = L
        override
        type RightParent = R
        override
        type Segment = C

        override
        def parentEither: A => Either[L, R] =
          A.parentEither

        override
        def lastSegment: A => C =
          A.lastSegment
      }
  }
}
