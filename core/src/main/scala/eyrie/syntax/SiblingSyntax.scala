package eyrie.syntax

import eyrie.ops.Sibling

trait SiblingSyntax {
  implicit def toSiblingOps[A](a: A): SiblingOps[A] =
    new SiblingOps(a)
}

final class SiblingOps[A](private val a: A) extends AnyVal {
  def parent(implicit A: Sibling[A]): A.Prefix =
    A.parent(a)

  def lastSegment(implicit A: Sibling[A]): A.Segment =
    A.lastSegment(a)
}
