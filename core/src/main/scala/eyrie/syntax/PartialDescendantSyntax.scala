package eyrie.syntax

import eyrie.ops.PartialDescendant

trait PartialDescendantSyntax {
  implicit def toPartialDescendantOps[A](a: A): PartialDescendantOps[A] =
    new PartialDescendantOps(a)
}

final class PartialDescendantOps[A](private val a: A) extends AnyVal {
  def rootOption(implicit A: PartialDescendant.ByInput[A]): Option[A.Root] =
    A.rootOption(a)

  def asRoot(implicit A: PartialDescendant.TrivialByInput[A]): Option[A.Root] =
    A.asRoot(a)
}
