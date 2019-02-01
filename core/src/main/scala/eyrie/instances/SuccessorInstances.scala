package eyrie.instances

import cats.instances.all._
import cats.syntax.compose._
import eyrie.ops._

private[eyrie]
trait SuccessorInstances {
  implicit def eyrieDiSuccessorBasedInstance[A, B, C, L, R](
    implicit
    A: DiSuccessor[A, L, R, C],
    L: Convertible[L, B],
    R: Convertible[R, B]
  ): Successor[A, B, C] =
    new Successor[A, B, C] {
      override
      def parent: A => B =
        A.parentEither(_).fold(L.widen, R.widen)

      override
      def lastSegment: A => C =
        A.lastSegment
    }
}

private[eyrie]
trait SuccessorByInputInstances {
  implicit def eyrieSuccessorByInputInstance[A, B, C](implicit A: Successor[A, B, C]): Successor.ByInput.Aux[A, B, C] =
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

private[eyrie]
trait PotentialSuccessorInstances {
  implicit def eyrieLeftSuccessorBasedInstance[A, L, R, B, C](
    implicit
    A: Subdivision[A, L, R],
    L: Successor[L, B, C],
    R: NonSuccessor[R]
  ): PotentialSuccessor[A, B, C] =
    new PotentialSuccessor[A, B, C] {
      override
      def parentOption: A => Option[B] =
        A.subdivide(_).left.toOption.map(L.parent)

      override
      def lastSegmentOption: A => Option[C] =
        A.subdivide(_).left.toOption.map(L.lastSegment)
    }

  implicit def eyrieRightSuccessorBasedInstance[A, L, R, B, C](
    implicit
    A: Subdivision[A, L, R],
    L: NonSuccessor[L],
    R: Successor[R, B, C]
  ): PotentialSuccessor[A, B, C] =
    new PotentialSuccessor[A, B, C] {
      override
      def parentOption: A => Option[B] =
        A.subdivide(_).toOption.map(R.parent)

      override
      def lastSegmentOption: A => Option[C] =
        A.subdivide(_).toOption.map(R.lastSegment)
    }

  implicit def eyrieDiPotentialSuccessorBasedInstance[A, C, L, R](
    implicit
    A: DiPotentialSuccessor[A, L, R, C],
    L: Convertible[L, A],
    R: Convertible[R, A]
  ): PotentialSuccessor[A, A, C] =
    new PotentialSuccessor[A, A, C] {
      override
      def parentOption: A => Option[A] =
        A.parentEitherOption >>> (_.map(_.fold(L.widen, R.widen)))

      override
      def lastSegmentOption: A => Option[C] =
        A.lastSegmentOption
    }
}

private[eyrie]
trait PotentialSuccessorByInputInstances {
  implicit def eyriePotentialSuccessorByInputInstance[A, B, C](
    implicit A: PotentialSuccessor[A, B, C]
  ): PotentialSuccessor.ByInput.Aux[A, B, C] =
    new PotentialSuccessor.ByInput[A] {
      override
      type Out = B
      override
      type Segment = C

      override
      def parentOption: A => Option[B] =
        A.parentOption

      override
      def lastSegmentOption: A => Option[C] =
        A.lastSegmentOption
    }
}
