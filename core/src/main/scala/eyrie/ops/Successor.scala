package eyrie.ops

import simulacrum.typeclass

@typeclass
trait Successor[A] {
  type Prefix
  type Segment

  def parent(a: A): Prefix
  def lastSegment(a: A): Segment
}

object Successor {
  type Aux[A, B, C] = Successor[A] {
    type Prefix = B
    type Segment = C
  }
}
