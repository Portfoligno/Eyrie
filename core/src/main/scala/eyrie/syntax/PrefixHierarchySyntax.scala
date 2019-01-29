package eyrie.syntax

import eyrie.ops.PrefixHierarchy

trait PrefixHierarchySyntax {
  implicit def toPrefixHierarchyOps[A](a: A): PrefixHierarchyOps[A] =
    new PrefixHierarchyOps(a)
}

final class PrefixHierarchyOps[A](private val x: A) extends AnyVal {
  def startsWith(y: A)(implicit A: PrefixHierarchy[A]): Boolean =
    A.startsWith(x, y)
}
