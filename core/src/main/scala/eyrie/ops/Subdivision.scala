package eyrie.ops

import eyrie.instances.{SubdivisionByAttributeInstances, SubdivisionByInputInstances}
import simulacrum.typeclass

trait Subdivision[A, L, R] {
  type Attribute[_]

  def subdivide: A => Either[L, R]
}

object Subdivision {
  type Aux[Attr[_], A, L, R] = Subdivision[A, L, R] {
    type Attribute[X] = Attr[X]
  }

  @inline
  def apply[A, L, R](implicit A: Subdivision[A, L, R]): Subdivision[A, L, R] = A


  trait ByAttribute[Attr[_], A] {
    type Left
    type Right

    def subdivide: A => Either[Left, Right]
  }

  object ByAttribute extends SubdivisionByAttributeInstances {
    type Aux[Attr[_], A, L, R] = ByAttribute[Attr, A] {
      type Left = L
      type Right = R
    }

    @inline
    def apply[Attr[_], A](implicit A: ByAttribute[Attr, A]): ByAttribute[Attr, A] = A
  }


  @typeclass
  trait ByInput[A] {
    type Attribute[_]
    type Left
    type Right

    def subdivide: A => Either[Left, Right]
  }

  object ByInput extends SubdivisionByInputInstances {
    type Aux[Attr[_], A, L, R] = ByInput[A] {
      type Attribute[X] = Attr[X]
      type Left = L
      type Right = R
    }
  }
}
