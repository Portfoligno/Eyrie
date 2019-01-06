package eyrie.ops

trait Convertible[A, B] {
  type Attribute[_]
  type Parameter

  def widen: A => B

  def narrow: B => Option[A]
}

object Convertible {
  type Aux[Attr[_], P, A, B] = Convertible[A, B] {
    type Attribute[X] = Attr[X]
    type Parameter = P
  }

  @inline
  def apply[A, B](implicit F: Convertible[A, B]): Convertible[A, B] = F


  trait ByAttribute[Attr[_], A] extends Any {
    type Out

    def widen: A => Out

    def narrow: Out => Option[A]
  }

  object ByAttribute {
    type Aux[Attr[_], A, B] = ByAttribute[Attr, A] {
      type Out = B
    }

    @inline
    def apply[Attr[_], A](implicit F: ByAttribute[Attr, A]): ByAttribute[Attr, A] = F


    implicit def eyrieByAttributeInstance[Attr[_], A, B](implicit F: Convertible.Aux[Attr, _, A, B]): Aux[Attr, A, B] =
      new ByAttribute[Attr, A] {
        override
        type Out = B

        override
        def widen: A => B =
          F.widen

        override
        def narrow: B => Option[A] =
          F.narrow
      }
  }


  trait ByQuality[Qual, B] extends Any {
    type Out

    def widen: Out => B

    def narrow: B => Option[Out]
  }

  object ByQuality {
    type Aux[Qual, A, B] = ByQuality[Qual, B] {
      type Out = A
    }

    @inline
    def apply[Qual, B](implicit F: ByQuality[Qual, B]): ByQuality[Qual, B] = F


    implicit def eyrieByQualityInstance[Attr[_], Param, A, B](
      implicit F: Convertible.Aux[Attr, Param, A, B]
    ): Aux[Attr[Param], A, B] =
      new ByQuality[Attr[Param], B] {
        override
        type Out = A

        override
        def widen: A => B =
          F.widen

        override
        def narrow: B => Option[A] =
          F.narrow
      }
  }
}
