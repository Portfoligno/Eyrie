package eyrie.syntax

import eyrie.ops.PotentialDescendant

trait PotentialDescendantSyntax {
  implicit def toPotentialDescendantOps[A](a: A): PotentialDescendantOps[A] =
    new PotentialDescendantOps(a)
}

final class PotentialDescendantOps[A](private val a: A) extends AnyVal {
  def rootOption(implicit A: PotentialDescendant.ByInput[A]): Option[A.Root] =
    A.rootOption(a)

  def asRoot(implicit A: PotentialDescendant.TrivialByInput[A]): Option[A.Root] =
    A.asRoot(a)
}
