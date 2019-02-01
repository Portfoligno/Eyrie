package eyrie

package object ops {
  type NonSuccessor[A] = GeneralizedSuccessor[A, Nothing, Nothing]

  type TrivialDescendant[A] = Descendant[A, A]
  type NonDescendant[A] = GeneralizedDescendant[A, Nothing]
}
