package eyrie.nio.instances

import eyrie.nio.FilePath
import eyrie.ops.Subdivision
import eyrie.{Emptiness, Relativity}

private[nio]
trait FilePathInstances extends FilePathLowPriorityInstances with EqualityInstances[FilePath] {
  private
  lazy val _relativitySubdivisionInstance =
    new FilePathSubdivision[Relativity, FilePath, FilePath.Absolute, FilePath.Relative]

  implicit def eyrieNioRelativitySubdivisionInstance[C]: Subdivision.Aux[
    Relativity, FilePath[C], FilePath.Absolute[C], FilePath.Relative[C]] =
    _relativitySubdivisionInstance.of[C]
}

private[nio]
trait FilePathLowPriorityInstances {
  private
  lazy val _emptinessSubdivisionInstance =
    new FilePathSubdivision[Emptiness, FilePath, FilePath.NonEmpty, FilePath.Empty]

  implicit def eyrieNioEmptinessSubdivisionInstance[C]: Subdivision.Aux[
    Emptiness, FilePath[C], FilePath.NonEmpty[C], FilePath.Empty[C]] =
    _emptinessSubdivisionInstance.of[C]
}
