package eyrie.ops

import cats.instances.all._
import cats.syntax.compose._
import cats.syntax.either._
import simulacrum.typeclass

trait DiPotentialSuccessor[A, L, R, C] {
  def parentEitherOption: A => Option[Either[L, R]]
  def lastSegmentOption: A => Option[C]
}

object DiPotentialSuccessor {
  @inline
  def apply[A, L, R, C](implicit A: DiPotentialSuccessor[A, L, R, C]): DiPotentialSuccessor[A, L, R, C] = A

  implicit def eyrieSubdivisionBasedInstance[A, L, R, C](
    implicit
    A: Subdivision[A, L, R],
    L: PotentialSuccessor[L, C],
    R: PotentialSuccessor[R, C]
  ): DiPotentialSuccessor[A, L, R, C] =
    new DiPotentialSuccessor[A, L, R, C] {
      override
      def parentEitherOption: A => Option[Either[L, R]] =
        A.subdivide(_).fold(L.parentOption >>> (_.map(_.asLeft)), R.parentOption >>> (_.map(_.asRight)))

      override
      def lastSegmentOption: A => Option[C] =
        A.subdivide(_).fold(L.lastSegmentOption, R.lastSegmentOption)
    }


  @typeclass
  trait ByInput[A] {
    type Left
    type Right
    type Segment

    def parentEitherOption: A => Option[Either[Left, Right]]
    def lastSegmentOption: A => Option[Segment]
  }

  object ByInput {
    type Aux[A, L, R, C] = ByInput[A] {
      type Left = L
      type Right = R
      type Segment = C
    }

    implicit def eyrieByInputInstance[A, L, R, C](implicit A: DiPotentialSuccessor[A, L, R, C]): Aux[A, L, R, C] =
      new ByInput[A] {
        override
        type Left = L
        override
        type Right = R
        override
        type Segment = C

        override
        def parentEitherOption: A => Option[Either[L, R]] =
          A.parentEitherOption

        override
        def lastSegmentOption: A => Option[C] =
          A.lastSegmentOption
      }
  }
}
