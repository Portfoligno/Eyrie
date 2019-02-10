package eyrie.instances

import eyrie.ops.{Subdivision, Suffix}

private[eyrie]
trait SuffixInstances {
  implicit def eyrieSubdivisionBasedInstance[A, B, L, R](
    implicit
    A: Subdivision[A, L, R],
    L: Suffix[L, B],
    R: Suffix[R, B]
  ): Suffix[A, B] =
    new Suffix[A, B] {
      override
      def endsWith: (A, B) => Boolean =
        (a, b) => A.subdivide(a).fold(L.endsWith(_, b), R.endsWith(_, b))
    }
}
