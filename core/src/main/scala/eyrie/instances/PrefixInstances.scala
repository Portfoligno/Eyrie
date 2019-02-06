package eyrie.instances

import eyrie.{Emptiness, False}
import eyrie.ops.{Convertible, NonDescendant, Prefix}

private[eyrie]
trait PrefixInstances {
  implicit def eyrieNonDescendantInducedInstance[A, B](
    implicit
    A: Convertible.ByQuality.Aux[Emptiness[False], B, A],
    B: Prefix[B],
    ev: NonDescendant[B]
  ): Prefix[A] =
    new Prefix[A] {
      def startsWith: (A, A) => Boolean =
        (x, y) => A.narrow(y).fold(true)(yy =>
          A.narrow(x).fold(false)(
            B.startsWith(_, yy)
          )
        )
    }
}
