package eyrie

import eyrie.file.ops.ConvertibleInstances

package object file extends ConvertibleInstances {
  trait Emptiness[Boolean]
  trait Relativity[Boolean]
}
