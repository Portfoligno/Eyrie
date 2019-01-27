package eyrie.instances

import cats.instances.all._
import cats.syntax.compose._
import cats.syntax.either._
import eyrie.ops._

private[eyrie]
trait DiSuccessorInstances {
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
}

private[eyrie]
trait DiSuccessorByInputInstances {
  implicit def eyrieByInputInstance[A, L, R, C](
    implicit A: DiSuccessor[A, L, R, C]
  ): DiSuccessor.ByInput.Aux[A, L, R, C] =
    new DiSuccessor.ByInput[A] {
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

private[eyrie]
trait DiPotentialSuccessorInstances {
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
}

private[eyrie]
trait DiPotentialSuccessorByInputInstances {
  implicit def eyrieByInputInstance[A, L, R, C](
    implicit A: DiPotentialSuccessor[A, L, R, C]
  ): DiPotentialSuccessor.ByInput.Aux[A, L, R, C] =
    new DiPotentialSuccessor.ByInput[A] {
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
