package eyrie.ops

import simulacrum.{op, typeclass}

@typeclass
trait Equality[A] {
  @op("===")
  def equal(x: A, y: A): Boolean
}
