package eyrie.syntax

import eyrie.ops.PartialSuccessor

trait PartialSuccessorSyntax {
  implicit def toPartialSuccessorOps[A](a: A): PartialSuccessorOps[A] =
    new PartialSuccessorOps(a)
}

final class PartialSuccessorOps[A](private val a: A) extends AnyVal {
  def parentOption(implicit A: PartialSuccessor.ByInput[A]): Option[A.Out] =
    A.parentOption(a)

  def lastSegmentOption(implicit A: PartialSuccessor.ByInput[A]): Option[A.Segment] =
    A.lastSegmentOption(a)
}
