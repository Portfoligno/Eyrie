package eyrie

package object ops {
  type NonSuccessor[A] = Successor[A, Nothing, Nothing]

  type TrivialDescendant[A] = Descendant[A, A]
  type NonDescendant[A] = Descendant[A, Nothing]
}
