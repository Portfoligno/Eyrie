package eyrie

package object ops {
  type TrivialDescendant[A] = Descendant[A, A]
  type NonDescendant[A] = Descendant[A, Nothing]
}
