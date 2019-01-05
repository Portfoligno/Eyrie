package eyrie.syntax

import eyrie.ops.Descendant

trait DescendantSyntax {
  implicit def toDescendantOps[A](a: A): DescendantOps[A] =
    new DescendantOps(a)
}

final class DescendantOps[A](private val a: A) extends AnyVal {
  def root(implicit A: Descendant[A]): A.Root =
    A.root(a)
}
