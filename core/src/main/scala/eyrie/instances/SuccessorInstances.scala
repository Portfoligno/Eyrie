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

private[eyrie]
trait PotentialSuccessorInstances {
  implicit def eyrieSuccessorBasedInstance[A, B, C](
    implicit B: Successor[A, B, C], F: Convertible[A, B]
  ): PotentialSuccessor[B, C] =
    new PotentialSuccessor[B, C] {
      override
      def parentOption: B => Option[B] =
        F.narrow(_).map(B.parent)

      override
      def lastSegmentOption: B => Option[C] =
        F.narrow(_).map(B.lastSegment)
    }

  implicit def eyrieDiPotentialSuccessorBasedInstance[A, C, L, R](
    implicit
    A: DiPotentialSuccessor[A, L, R, C],
    L: Convertible[L, A],
    R: Convertible[R, A]
  ): PotentialSuccessor[A, C] =
    new PotentialSuccessor[A, C] {
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
  implicit def eyrieByInputInstance[A, C](implicit A: PotentialSuccessor[A, C]): PotentialSuccessor.ByInput.Aux[A, C] =
    new PotentialSuccessor.ByInput[A] {
      override
      type Segment = C

      override
      def parentOption: A => Option[A] =
        A.parentOption

      override
      def lastSegmentOption: A => Option[C] =
        A.lastSegmentOption
    }
}
