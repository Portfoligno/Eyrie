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
  implicit def eyrieTrivialByInputInstance[A](
    implicit A: TrivialDescendant[A]
  ): Descendant.ByInput.Aux[A, A] =
    eyrieAmbiguousTrivialByInputInstance

  implicit def eyrieAmbiguousTrivialByInputInstance[A](
    implicit A: TrivialDescendant[A]
  ): Descendant.ByInput.Aux[A, A] =
    new Descendant.ByInput[A] {
      override
      type Root = A

      override
      def root: A => A =
        A.root
    }

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
  implicit def eyriePotentialDescendantByInputInstance[A, D](
    implicit A: PotentialDescendant[A, D]
  ): PotentialDescendant.ByInput.Aux[A, D] =
    new PotentialDescendant.ByInput[A] {
      override
      type Root = D

      override
      def rootOption: A => Option[D] =
        A.rootOption
    }
}
