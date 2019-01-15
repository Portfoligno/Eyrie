package eyrie.syntax

import eyrie.ops.DiSuccessor

trait DiSuccessorSyntax {
  implicit def toDiSuccessorOps[A](a: A): DiSuccessorOps[A] =
    new DiSuccessorOps(a)
}

final class DiSuccessorOps[A](private val a: A) extends AnyVal {
  def parentEither(implicit A: DiSuccessor.ByInput[A]): Either[A.LeftParent, A.RightParent] =
    A.parentEither(a)

  def lastSegment(implicit A: DiSuccessor.ByInput[A]): A.Segment =
    A.lastSegment(a)
}
