package eyrie.syntax

import eyrie.ops.Successor

trait SuccessorSyntax {
  implicit def toSuccessorOps[A](a: A): SuccessorOps[A] =
    new SuccessorOps(a)
}

final class SuccessorOps[A](private val a: A) extends AnyVal {
  def parent(implicit A: Successor[A]): A.Prefix =
    A.parent(a)

  def lastSegment(implicit A: Successor[A]): A.Segment =
    A.lastSegment(a)
}
