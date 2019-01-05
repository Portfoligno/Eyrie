package eyrie.ops

import simulacrum.typeclass

@typeclass
trait Sibling[A] {
  type Prefix
  type Segment

  def parent(a: A): Prefix
  def lastSegment(a: A): Segment
}

object Sibling {
  type Aux[A, B, C] = Sibling[A] {
    type Prefix = B
    type Segment = C
  }
}
