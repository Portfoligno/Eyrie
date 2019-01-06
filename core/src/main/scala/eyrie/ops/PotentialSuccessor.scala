package eyrie.ops

import eyrie.{Emptiness, False}
import simulacrum.typeclass

@typeclass
trait PotentialSuccessor[A] {
  type Prefix
  type Segment

  def parentOption: A => Option[Prefix]
  def lastSegmentOption: A => Option[Segment]
}

object PotentialSuccessor {
  type Aux[A, B, C] = PotentialSuccessor[A] {
    type Prefix = B
    type Segment = C
  }

  implicit def eyrieConvertibleBasedPotentialSuccessorInstance[A, B](
    implicit F: Convertible.Aux[Emptiness, False, B, A], B: Successor.ByInput[B]
  ): PotentialSuccessor.Aux[A, B.Prefix, B.Segment] =
    new PotentialSuccessor[A] {
      override
      type Prefix = B.Prefix
      override
      type Segment = B.Segment

      override
      def parentOption: A => Option[B.Prefix] =
        F.narrow(_).map(B.parent)

      override
      def lastSegmentOption: A => Option[B.Segment] =
        F.narrow(_).map(B.lastSegment)
    }
}
