package eyrie

package object ops {
  type NonSuccessor[A] = GeneralizedSuccessor[A, Nothing, Nothing]
  type NonDescendant[A] = GeneralizedDescendant[A, Nothing]
}
