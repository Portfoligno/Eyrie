package eyrie.syntax

import eyrie.ops.PotentialSuccessor

trait PotentialSuccessorSyntax {
  implicit def toPotentialSuccessorOps[A](a: A): PotentialSuccessorOps[A] =
    new PotentialSuccessorOps(a)
}

final class PotentialSuccessorOps[A](private val a: A) extends AnyVal {
  def parentOption(implicit A: PotentialSuccessor.ByInput[A]): Option[A] =
    A.parentOption(a)

  def lastSegmentOption(implicit A: PotentialSuccessor.ByInput[A]): Option[A.Segment] =
    A.lastSegmentOption(a)
}
