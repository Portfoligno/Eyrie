package eyrie.instances

import eyrie.ops._
import eyrie.{Emptiness, False, Relativity}

private[eyrie]
trait PrefixInstances {
  implicit def eyrieNonDescendantBasedInstance[A, B](
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

  implicit def eyrieDescendantBasedInstance[A, L, R](
    implicit
    A: Subdivision.ByAttribute.Aux[Emptiness, A, L, R],
    prefixL: Prefix[L],
    descendantL: Descendant[L, R],
    R: Equality[R]
  ): Prefix[A] =
    new Prefix[A] {
      import eyrie.syntax.equality._

      override
      def startsWith: (A, A) => Boolean =
        (x, y) => (A.subdivide(x), A.subdivide(y)) match {
          case (Left(xx), Left(yy)) => prefixL.startsWith(xx, yy)
          case (Left(xx), Right(yy)) => descendantL.root(xx) === yy
          case (Right(xx), Right(yy)) => xx === yy
          case _ => false
        }
    }

  implicit def eyrieSubdivisionBasedInstance[A, L, R](
    implicit
    A: Subdivision.ByAttribute.Aux[Relativity, A, L, R],
    L: Prefix[L],
    R: Prefix[R]
  ): Prefix[A] =
    new Prefix[A] {
      override
      def startsWith: (A, A) => Boolean =
        (x, y) => (A.subdivide(x), A.subdivide(y)) match {
          // Both absolute
          case (Left(xx), Left(yy)) => L.startsWith(xx, yy)
          // Both relative
          case (Right(xx), Right(yy)) => R.startsWith(xx, yy)
          // Not matching relativity
          case _ => false
        }
    }
}
