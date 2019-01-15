package eyrie.ops

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

  object ByAttribute {
    type Aux[Attr[_], A, L, R] = ByAttribute[Attr, A] {
      type Left = L
      type Right = R
    }

    @inline
    def apply[Attr[_], A](implicit A: ByAttribute[Attr, A]): ByAttribute[Attr, A] = A

    implicit def eyrieByAttributeInstance[Attr[_], A, L, R](
      implicit F: Subdivision.Aux[Attr, A, L, R]
    ): Aux[Attr, A, L, R] =
      new ByAttribute[Attr, A] {
        override
        type Left = L
        override
        type Right = R

        override
        def subdivide: A => Either[L, R] =
          F.subdivide
      }
  }


  @typeclass
  trait ByInput[A] {
    type Attribute[_]
    type Left
    type Right

    def subdivide: A => Either[Left, Right]
  }

  object ByInput {
    type Aux[Attr[_], A, L, R] = ByInput[A] {
      type Attribute[X] = Attr[X]
      type Left = L
      type Right = R
    }

    implicit def eyrieByAttributeInstance[Attr[_], A, L, R](
      implicit F: Subdivision.Aux[Attr, A, L, R]
    ): Aux[Attr, A, L, R] =
      new ByInput[A] {
        override
        type Attribute[X] = Attr[X]
        override
        type Left = L
        override
        type Right = R

        override
        def subdivide: A => Either[L, R] =
          F.subdivide
      }
  }
}
