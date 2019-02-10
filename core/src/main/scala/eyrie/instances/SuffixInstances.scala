package eyrie.instances

import eyrie.ops.{Convertible, Subdivision, Suffix}
import eyrie.{Emptiness, False}

private[eyrie]
trait SuffixInstances extends LowPrioritySuffixInstances {
  implicit def eyrieConvertibleBasedInstance[A, B, C](
    implicit
    A: Convertible.ByQuality.Aux[Emptiness[False], C, A],
    C: Suffix[C, B]
  ): Suffix[A, B] =
    new Suffix[A, B] {
      override
      def endsWith: (A, B) => Boolean =
        (a, b) => A.narrow(a).fold(false)(
          C.endsWith(_, b)
        )
    }
}

private[eyrie]
trait LowPrioritySuffixInstances {
  implicit def eyrieSubdivisionBasedInstance[A, B, L, R](
    implicit
    A: Subdivision[A, L, R],
    L: Suffix[L, B],
    R: Suffix[R, B]
  ): Suffix[A, B] =
    new Suffix[A, B] {
      override
      def endsWith: (A, B) => Boolean =
        (a, b) => A.subdivide(a).fold(
          L.endsWith(_, b),
          R.endsWith(_, b))
    }
}
