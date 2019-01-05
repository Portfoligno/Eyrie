package eyrie.syntax

import eyrie.ops.Segment

trait SegmentSyntax {
  implicit def toSegmentOps[A](a: A): SegmentOps[A] =
    new SegmentOps(a)
}

final class SegmentOps[A](private val a: A) extends AnyVal {
  def singletonPath(implicit A: Segment[A]): A.Singleton =
    A.singleton(a)
}
