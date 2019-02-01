package eyrie.ops

import eyrie.instances.PrefixInstances
import simulacrum.typeclass

@typeclass
trait Prefix[A] {
  def startsWith(x: A, y: A): Boolean
}

object Prefix extends PrefixInstances
