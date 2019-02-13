package eyrie.ops

import simulacrum.typeclass

@typeclass
trait TrivialDescendant[A] extends Descendant[A, A] {
  override
  final def root: A => A =
    identity
}
