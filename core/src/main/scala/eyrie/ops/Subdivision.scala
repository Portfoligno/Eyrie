package eyrie.ops

import simulacrum.typeclass

@typeclass
trait Subdivision[A] {
  type Attribute[_]
  type Left
  type Right

  def subdivide: A => Either[Left, Right]
}

object Subdivision {
  type Aux[Attr[_], A, L, R] = Subdivision[A] {
    type Attribute[X] = Attr[X]
    type Left = L
    type Right = R
  }


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
    def apply[Attr[_], A](implicit F: ByAttribute[Attr, A]): ByAttribute[Attr, A] = F

    implicit def eyrieByAttributeInstance[Attr[_], A, L, R](implicit F: Subdivision.Aux[Attr, A, L, R]): Aux[Attr, A, L, R] =
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
}
