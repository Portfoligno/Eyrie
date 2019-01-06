import shapeless.Witness

package object eyrie {
  trait Emptiness[+X]
  trait Relativity[+X]

  type True = Witness.`true`.T
  type False = Witness.`false`.T


  private[eyrie]
  type ∏[A, B] = (A, B)

  private[eyrie]
  type -∏-[A[_], B[_]] = {
    type λ[X] = A[X] ∏ B[X]
  }
}
