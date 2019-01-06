package eyrie.ops

import simulacrum.typeclass

@typeclass
trait Descendant[A] extends Successor[A] {
  type Root

  def root: A => Root
}

object Descendant {
  type Aux[A, B, C, D] = Descendant[A] {
    type Prefix = B
    type Segment = C
    type Root = D
  }
}
