package eyrie.file

import eyrie.file.ops.{EqualityInstances, FilePathSubdivision}
import eyrie.ops.Subdivision

private[file]
trait FileInstances extends LowPriorityFileInstances with EqualityInstances[FilePath] {
  private
  lazy val _relativitySubdivisionInstance =
    new FilePathSubdivision[Relativity, FilePath, FilePath.Relative, FilePath.Absolute]

  implicit def relativitySubdivisionInstance[C]: Subdivision.Aux[
    Relativity, FilePath[C], FilePath.Relative[C], FilePath.Absolute[C]] =
    _relativitySubdivisionInstance.ofAux[C]
}

private[file]
trait LowPriorityFileInstances {
  private
  lazy val _emptinessSubdivisionInstance =
    new FilePathSubdivision[Emptiness, FilePath, FilePath.Empty, FilePath.NonEmpty]

  implicit def emptinessSubdivisionInstance[C]: Subdivision.Aux[
    Emptiness, FilePath[C], FilePath.Empty[C], FilePath.NonEmpty[C]] =
    _emptinessSubdivisionInstance.ofAux[C]
}
