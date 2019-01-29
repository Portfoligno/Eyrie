package eyrie.ops

import simulacrum.typeclass

@typeclass
trait PrefixHierarchy[A] {
  def startsWith(x: A, y: A): Boolean
}
