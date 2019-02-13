package eyrie.instances

import cats.instances.all._
import cats.syntax.compose._
import eyrie.ops._

private[eyrie]
trait GeneralizedSuccessorInstances {
  implicit def eyrieNonSuccessorBasedInstance[A, L, R](
    implicit
    A: Subdivision[A, L, R],
    L: NonSuccessor[L],
    R: NonSuccessor[R]
  ): NonSuccessor[A] =
    new NonSuccessor[A] { }
}

private[eyrie]
trait SuccessorInstances {
  implicit def eyrieDiSuccessorBasedInstance[A, L, R, B, C](
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
trait PartialSuccessorInstances {
  implicit def eyrieLeftSuccessorBasedInstance[A, L, R, B, C](
    implicit
    A: Subdivision[A, L, R],
    L: Successor[L, B, C],
    R: NonSuccessor[R]
  ): PartialSuccessor[A, B, C] =
    new PartialSuccessor[A, B, C] {
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
  ): PartialSuccessor[A, B, C] =
    new PartialSuccessor[A, B, C] {
      override
      def parentOption: A => Option[B] =
        A.subdivide(_).toOption.map(R.parent)

      override
      def lastSegmentOption: A => Option[C] =
        A.subdivide(_).toOption.map(R.lastSegment)
    }

  implicit def eyrieDiPartialSuccessorBasedInstance[A, L, R, C](
    implicit
    A: DiPartialSuccessor[A, L, R, C],
    L: Convertible[L, A],
    R: Convertible[R, A]
  ): PartialSuccessor[A, A, C] =
    new PartialSuccessor[A, A, C] {
      override
      def parentOption: A => Option[A] =
        A.parentEitherOption >>> (_.map(_.fold(L.widen, R.widen)))

      override
      def lastSegmentOption: A => Option[C] =
        A.lastSegmentOption
    }
}

private[eyrie]
trait PartialSuccessorByInputInstances {
  implicit def eyriePartialSuccessorByInputInstance[A, B, C](
    implicit A: PartialSuccessor[A, B, C]
  ): PartialSuccessor.ByInput.Aux[A, B, C] =
    new PartialSuccessor.ByInput[A] {
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
