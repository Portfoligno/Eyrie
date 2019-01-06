package eyrie

import eyrie.file.ops.ConvertibleInstances

package object file extends ConvertibleInstances {
  trait Emptiness[Boolean]
  trait Relativity[Boolean]

  private[file]
  type ∨[A[_], B[_]] = {
    type λ[X] = A[X] with B[X]
  }
}
