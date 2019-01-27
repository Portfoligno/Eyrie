package eyrie.file.instances

import eyrie.file.FilePath
import eyrie.ops.Subdivision
import eyrie.{Emptiness, Relativity}

private[file]
trait FilePathInstances extends FilePathLowPriorityInstances with EqualityInstances[FilePath] {
  private
  lazy val _relativitySubdivisionInstance =
    new FilePathSubdivision[Relativity, FilePath, FilePath.Absolute, FilePath.Relative]

  implicit def eyrieFileRelativitySubdivisionInstance[C]: Subdivision.Aux[
    Relativity, FilePath[C], FilePath.Absolute[C], FilePath.Relative[C]] =
    _relativitySubdivisionInstance.of[C]
}

private[file]
trait FilePathLowPriorityInstances {
  private
  lazy val _emptinessSubdivisionInstance =
    new FilePathSubdivision[Emptiness, FilePath, FilePath.NonEmpty, FilePath.Empty]

  implicit def eyrieFileEmptinessSubdivisionInstance[C]: Subdivision.Aux[
    Emptiness, FilePath[C], FilePath.NonEmpty[C], FilePath.Empty[C]] =
    _emptinessSubdivisionInstance.of[C]
}
