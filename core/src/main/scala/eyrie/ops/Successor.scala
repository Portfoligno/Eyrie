package eyrie.ops

import simulacrum.typeclass

@typeclass
trait Successor[A] {
  type Prefix
  type Segment

  def parent: A => Prefix
  def lastSegment: A => Segment
}

object Successor {
  type Aux[A, B, C] = Successor[A] {
    type Prefix = B
    type Segment = C
  }
}
