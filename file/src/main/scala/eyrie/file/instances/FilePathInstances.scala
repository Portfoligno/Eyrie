package eyrie.file.instances

import eyrie.{Emptiness, Relativity}
import eyrie.file.FilePath
import eyrie.ops.Subdivision

private[file]
trait FilePathInstances extends LowPriorityFilePathInstances with EqualityInstances[FilePath] {
  private
  lazy val _relativitySubdivisionInstance =
    new FilePathSubdivision[Relativity, FilePath, FilePath.Relative, FilePath.Absolute]

  implicit def eyrieFileRelativitySubdivisionInstance[C]: Subdivision.Aux[
    Relativity, FilePath[C], FilePath.Relative[C], FilePath.Absolute[C]] =
    _relativitySubdivisionInstance.ofAux[C]
}

private[file]
trait LowPriorityFilePathInstances {
  private
  lazy val _emptinessSubdivisionInstance =
    new FilePathSubdivision[Emptiness, FilePath, FilePath.Empty, FilePath.NonEmpty]

  implicit def eyrieFileEmptinessSubdivisionInstance[C]: Subdivision.Aux[
    Emptiness, FilePath[C], FilePath.Empty[C], FilePath.NonEmpty[C]] =
    _emptinessSubdivisionInstance.ofAux[C]
}
