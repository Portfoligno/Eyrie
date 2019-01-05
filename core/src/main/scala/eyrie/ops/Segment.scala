package eyrie.ops

import simulacrum.typeclass

@typeclass
trait Segment[A] {
  type Singleton

  def singleton(a: A): Singleton
}

object Segment {
  type Aux[A, B] = Segment[A] {
    type Singleton = B
  }
}
