package eyrie.ops

import eyrie.instances.DescendantByInputInstances
import simulacrum.typeclass

trait Descendant[A, B, C, D] extends Successor[A, B, C] {
  def root: A => D
}

object Descendant {
  @inline
  def apply[A, B, C, D](implicit A: Descendant[A, B, C, D]): Descendant[A, B, C, D] = A


  @typeclass
  trait ByInput[A] extends Successor.ByInput[A] {
    type Root

    def root: A => Root
  }

  object ByInput extends DescendantByInputInstances {
    type Aux[A, B, C, D] = ByInput[A] {
      type Prefix = B
      type Segment = C
      type Root = D
    }
  }
}
