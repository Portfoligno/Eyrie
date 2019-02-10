package eyrie.syntax

import eyrie.ops.Suffix

trait SuffixSyntax {
  implicit def toSuffixHierarchyOps[A](a: A): SuffixOps[A] =
    new SuffixOps(a)
}

final class SuffixOps[A](private val x: A) extends AnyVal {
  def endsWith[B](y: B)(implicit A: Suffix[A, B]): Boolean =
    A.endsWith(x, y)
}
