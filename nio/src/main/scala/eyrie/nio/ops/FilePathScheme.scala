package eyrie.nio.ops

import eyrie.nio.FilePath
import simulacrum.typeclass

@typeclass
trait FilePathScheme[A] {
  def getPath(string: String): Option[FilePath[A]]
}
