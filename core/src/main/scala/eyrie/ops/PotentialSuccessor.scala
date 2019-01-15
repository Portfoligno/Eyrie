package eyrie.ops

import simulacrum.typeclass

trait PotentialSuccessor[A, C] {
  def parentOption: A => Option[A]
  def lastSegmentOption: A => Option[C]
}

object PotentialSuccessor {
  implicit def eyrieSuccessorBasedPotentialSuccessorInstance[A, B, C](
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


  @typeclass
  trait ByInput[A] {
    type Segment

    def parentOption: A => Option[A]
    def lastSegmentOption: A => Option[Segment]
  }

  object ByInput {
    type Aux[A, C] = ByInput[A] {
      type Segment = C
    }

    implicit def eyrieByInputInstance[A, C](implicit A: PotentialSuccessor[A, C]): Aux[A, C] =
      new ByInput[A] {
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
}
