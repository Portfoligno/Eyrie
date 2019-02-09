import shapeless.Witness

package object eyrie {
  trait Emptiness[+X]
  trait Relativity[+X]
  trait EmptinessAndRelativity[+X]

  type True = Witness.`true`.T
  type False = Witness.`false`.T
}
