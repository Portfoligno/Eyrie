package eyrie.syntax

import eyrie.ops.DiPotentialSuccessor

trait DiPotentialSuccessorSyntax {
  implicit def toDiPotentialSuccessorOps[A](a: A): DiPotentialSuccessorOps[A] =
    new DiPotentialSuccessorOps(a)
}

final class DiPotentialSuccessorOps[A](private val a: A) extends AnyVal {
  def parentEitherOption(implicit A: DiPotentialSuccessor.ByInput[A]): Option[Either[A.Left, A.Right]] =
    A.parentEitherOption(a)

  def lastSegmentOption(implicit A: DiPotentialSuccessor.ByInput[A]): Option[A.Segment] =
    A.lastSegmentOption(a)
}
