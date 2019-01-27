package eyrie.instances

import cats.instances.all._
import cats.syntax.compose._
import eyrie.ops._

import scala.Function.const

private[eyrie]
trait DescendantInstances {
  implicit def eyrieDescendantBasedInstance[A, LA, RA, B](
    implicit
    A: Subdivision[A, LA, RA],
    LA: Descendant[LA, B],
    RA: Descendant[RA, B]
  ): Descendant[A, B] =
    new Descendant[A, B] {
      override
      def root: A => B =
        A.subdivide(_).fold(LA.root, RA.root)
    }

  implicit def eyrieNonDescendantBasedInstance[A, LA, RA](
    implicit
    A: Subdivision[A, LA, RA],
    LA: NonDescendant[LA],
    RA: NonDescendant[RA]
  ): NonDescendant[A] =
    eyrieDescendantBasedInstance[A, LA, RA, Nothing]
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
trait PotentialDescendantInstances {
  implicit def eyrieLeftDescendantBasedInstance[A, LA, RA, B](
    implicit
    A: Subdivision[A, LA, RA],
    LA: Descendant[LA, B],
    RA: NonDescendant[RA]
  ): PotentialDescendant[A, B] =
    new PotentialDescendant[A, B] {
      override
      def rootOption: A => Option[B] =
        A.subdivide(_).fold(LA.root >>> (Some(_)), const(None))
    }

  implicit def eyrieRightDescendantBasedInstance[A, LA, RA, B](
    implicit
    A: Subdivision[A, LA, RA],
    LA: NonDescendant[LA],
    RA: Descendant[RA, B]
  ): PotentialDescendant[A, B] =
    new PotentialDescendant[A, B] {
      override
      def rootOption: A => Option[B] =
        A.subdivide(_).fold(const(None), RA.root >>> (Some(_)))
    }
}

private[eyrie]
trait PotentialDescendantByInputInstances {
  implicit def eyriePotentialDescendantByInputInstance[A, B](
    implicit A: PotentialDescendant[A, B]
  ): PotentialDescendant.ByInput.Aux[A, B] =
    new PotentialDescendant.ByInput[A] {
      override
      type Root = B

      override
      def rootOption: A => Option[B] =
        A.rootOption
    }

  implicit def eyrieLeftTrivialPotentialDescendantByInputInstance[A, B](
    implicit A: PotentialDescendant[A, B], ev: Subdivision[A, B, _]
  ): PotentialDescendant.ByInput.Aux[A, B] =
    eyriePotentialDescendantByInputInstance[A, B]

  implicit def eyrieLeftAmbiguousTrivialPotentialDescendantByInputInstance[A, B](
    implicit A: PotentialDescendant[A, B], ev: Subdivision[A, B, _]
  ): PotentialDescendant.ByInput.Aux[A, B] =
    eyriePotentialDescendantByInputInstance[A, B]

  implicit def eyrieRightTrivialPotentialDescendantByInputInstance[A, B](
    implicit A: PotentialDescendant[A, B], ev: Subdivision[A, _, B]
  ): PotentialDescendant.ByInput.Aux[A, B] =
    eyriePotentialDescendantByInputInstance[A, B]

  implicit def eyrieRightAmbiguousTrivialPotentialDescendantByInputInstance[A, B](
    implicit A: PotentialDescendant[A, B], ev: Subdivision[A, _, B]
  ): PotentialDescendant.ByInput.Aux[A, B] =
    eyriePotentialDescendantByInputInstance[A, B]
}

private[eyrie]
trait PotentialDescendantTrivialByInputInstances {
  implicit def eyrieLeftPotentialDescendantTrivialByInputInstance[A, B](
    implicit A: PotentialDescendant[A, B], ev: Subdivision[A, B, _]
  ): PotentialDescendant.TrivialByInput.Aux[A, B] =
    new EyriePotentialDescendantInstance[A, B](A)

  implicit def eyrieRightPotentialDescendantTrivialByInputInstance[A, B](
    implicit A: PotentialDescendant[A, B], ev: Subdivision[A, _, B]
  ): PotentialDescendant.TrivialByInput.Aux[A, B] =
    new EyriePotentialDescendantInstance[A, B](A)

  private
  class EyriePotentialDescendantInstance[A, B](
    val A: PotentialDescendant[A, B]
  ) extends PotentialDescendant.TrivialByInput[A] {
    override
    type Root = B

    override
    def asRoot: A => Option[B] =
      A.rootOption
  }
}
