package eyrie.ops

import eyrie.instances.SuffixInstances

trait Suffix[A, B] {
  def endsWith: (A, B) => Boolean
}

object Suffix extends SuffixInstances {
  @inline
  def apply[A, B](implicit A: Suffix[A, B]): Suffix[A, B] = A
}
