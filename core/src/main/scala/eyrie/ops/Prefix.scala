package eyrie.ops

import eyrie.instances.PrefixInstances
import simulacrum.typeclass

@typeclass
trait Prefix[A] {
  def startsWith: (A, A) => Boolean
}

object Prefix extends PrefixInstances
