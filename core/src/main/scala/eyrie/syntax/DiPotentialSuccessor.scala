package eyrie.syntax

import eyrie.ops.DiPartialSuccessor

trait DiPartialSuccessorSyntax {
  implicit def toDiPartialSuccessorOps[A](a: A): DiPartialSuccessorOps[A] =
    new DiPartialSuccessorOps(a)
}

final class DiPartialSuccessorOps[A](private val a: A) extends AnyVal {
  def parentEitherOption(implicit A: DiPartialSuccessor.ByInput[A]): Option[Either[A.Left, A.Right]] =
    A.parentEitherOption(a)

  def lastSegmentOption(implicit A: DiPartialSuccessor.ByInput[A]): Option[A.Segment] =
    A.lastSegmentOption(a)
}
