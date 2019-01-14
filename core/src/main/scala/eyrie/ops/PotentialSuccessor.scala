package eyrie.ops

import simulacrum.typeclass

@typeclass
trait PotentialSuccessor[A] {
  type Segment

  def parentOption: A => Option[A]
  def lastSegmentOption: A => Option[Segment]
}

object PotentialSuccessor {
  type Aux[A, C] = PotentialSuccessor[A] {
    type Segment = C
  }

  implicit def eyrieSuccessorBasedPotentialSuccessorInstance[A, B, C](
    implicit B: Successor[A, B, C], F: Convertible[A, B]
  ): PotentialSuccessor.Aux[B, C] =
    new PotentialSuccessor[B] {
      override
      type Segment = C

      override
      def parentOption: B => Option[B] =
        F.narrow(_).map(B.parent)

      override
      def lastSegmentOption: B => Option[C] =
        F.narrow(_).map(B.lastSegment)
    }
}
