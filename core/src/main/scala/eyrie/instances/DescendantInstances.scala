package eyrie.instances

import cats.instances.all._
import cats.syntax.compose._
import eyrie.ops._

import scala.Function.const

private[eyrie]
trait GeneralizedDescendantInstances {
  implicit def eyrieNonDescendantBasedInstance[A, L, R](
    implicit
    A: Subdivision[A, L, R],
    L: NonDescendant[L],
    R: NonDescendant[R]
  ): NonDescendant[A] =
    new NonDescendant[A] { }
}

private[eyrie]
trait DescendantInstances {
  implicit def eyrieDescendantBasedInstance[A, L, R, B](
    implicit
    A: Subdivision[A, L, R],
    L: Descendant[L, B],
    R: Descendant[R, B]
  ): Descendant[A, B] =
    new Descendant[A, B] {
      override
      def root: A => B =
        A.subdivide(_).fold(L.root, R.root)
    }
}

private[eyrie]
trait DescendantByInputInstances {
  implicit def eyrieDescendantByInputInstance[A, B](
    implicit A: Descendant[A, B]
  ): Descendant.ByInput.Aux[A, B] =
    new Descendant.ByInput[A] {
      override
      type Root = B

      override
      def root: A => B =
        A.root
    }

  implicit def eyrieTrivialDescendantByInputInstance[A](
    implicit A: TrivialDescendant[A]
  ): Descendant.ByInput.Aux[A, A] =
    eyrieDescendantByInputInstance[A, A]

  implicit def eyrieAmbiguousTrivialDescendantByInputInstance[A](
    implicit A: TrivialDescendant[A]
  ): Descendant.ByInput.Aux[A, A] =
    eyrieDescendantByInputInstance[A, A]
}

private[eyrie]
trait PartialDescendantInstances {
  implicit def eyrieLeftDescendantBasedInstance[A, L, R, B](
    implicit
    A: Subdivision[A, L, R],
    L: Descendant[L, B],
    R: NonDescendant[R]
  ): PartialDescendant[A, B] =
    new PartialDescendant[A, B] {
      override
      def rootOption: A => Option[B] =
        A.subdivide(_).fold(L.root >>> (Some(_)), const(None))
    }

  implicit def eyrieRightDescendantBasedInstance[A, L, R, B](
    implicit
    A: Subdivision[A, L, R],
    L: NonDescendant[L],
    R: Descendant[R, B]
  ): PartialDescendant[A, B] =
    new PartialDescendant[A, B] {
      override
      def rootOption: A => Option[B] =
        A.subdivide(_).fold(const(None), R.root >>> (Some(_)))
    }
}

private[eyrie]
trait PartialDescendantByInputInstances {
  implicit def eyriePartialDescendantByInputInstance[A, B](
    implicit A: PartialDescendant[A, B]
  ): PartialDescendant.ByInput.Aux[A, B] =
    new PartialDescendant.ByInput[A] {
      override
      type Root = B

      override
      def rootOption: A => Option[B] =
        A.rootOption
    }

  implicit def eyrieLeftAmbiguousTrivialPartialDescendantByInputInstance[A, B](
    implicit A: PartialDescendant[A, B], ev: Subdivision[A, B, _]
  ): PartialDescendant.ByInput.Aux[A, B] =
    eyriePartialDescendantByInputInstance[A, B]

  implicit def eyrieRightAmbiguousTrivialPartialDescendantByInputInstance[A, B](
    implicit A: PartialDescendant[A, B], ev: Subdivision[A, _, B]
  ): PartialDescendant.ByInput.Aux[A, B] =
    eyriePartialDescendantByInputInstance[A, B]
}

private[eyrie]
trait PartialDescendantTrivialByInputInstances {
  implicit def eyrieLeftPartialDescendantTrivialByInputInstance[A, B](
    implicit A: PartialDescendant[A, B], ev: Subdivision[A, B, _]
  ): PartialDescendant.TrivialByInput.Aux[A, B] =
    new EyriePartialDescendantInstance[A, B](A)

  implicit def eyrieRightPartialDescendantTrivialByInputInstance[A, B](
    implicit A: PartialDescendant[A, B], ev: Subdivision[A, _, B]
  ): PartialDescendant.TrivialByInput.Aux[A, B] =
    new EyriePartialDescendantInstance[A, B](A)

  private
  class EyriePartialDescendantInstance[A, B](
    val A: PartialDescendant[A, B]
  ) extends PartialDescendant.TrivialByInput[A] {
    override
    type Root = B

    override
    def asRoot: A => Option[B] =
      A.rootOption
  }
}
