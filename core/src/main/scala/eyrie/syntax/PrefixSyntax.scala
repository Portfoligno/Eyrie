package eyrie.syntax

import eyrie.ops.Prefix

trait PrefixSyntax {
  implicit def toPrefixHierarchyOps[A](a: A): PrefixOps[A] =
    new PrefixOps(a)
}

final class PrefixOps[A](private val x: A) extends AnyVal {
  def startsWith(y: A)(implicit A: Prefix[A]): Boolean =
    A.startsWith(x, y)
}
