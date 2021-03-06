package eyrie.nio.instances

import eyrie._
import eyrie.nio.{AbsoluteFile, FilePath, IdentityFilePath, RelativeFile, RootDirectory}
import eyrie.ops.Convertible

import scala.reflect.ClassTag

private
class FilePathConvertible[A[_]](implicit A: ClassTag[A[_]]) extends Convertible[A[_], FilePath[_]] {
  override
  def widen: A[_] => FilePath[_] =
    _.asInstanceOf[FilePath[_]]

  override
  def narrow: FilePath[_] => Option[A[_]] =
    A.unapply


  def of[C, Attr[_], Param, B[_]]: Convertible.Aux[Attr, Param, A[C], B[C]] =
    asInstanceOf[Convertible.Aux[Attr, Param, A[C], B[C]]]
}

private[nio]
trait ConvertibleInstances extends ConvertibleLowPriorityInstances {
  implicit def eyrieNioEmptyConvertibleInstance[C]: Convertible.Aux[
    Emptiness, True, FilePath.Empty[C], FilePath[C]] =
    _emptyConvertibleInstance.of[C, Emptiness, True, FilePath]
  implicit def eyrieNioNonEmptyConvertibleInstance[C]: Convertible.Aux[
    Emptiness, False, FilePath.NonEmpty[C], FilePath[C]] =
    _nonEmptyConvertibleInstance.of[C, Emptiness, False, FilePath]
  implicit def eyrieNioRelativeConvertibleInstance[C]: Convertible.Aux[
    Relativity, True, FilePath.Relative[C], FilePath[C]] =
    _relativeConvertibleInstance.of[C, Relativity, True, FilePath]
  implicit def eyrieNioAbsoluteConvertibleInstance[C]: Convertible.Aux[
    Relativity, False, FilePath.Absolute[C], FilePath[C]] =
    _absoluteConvertibleInstance.of[C, Relativity, False, FilePath]

  implicit def eyrieNioIdentityFilePathRelativityConvertibleInstance[C]: Convertible.Aux[
    Emptiness, True, IdentityFilePath[C], FilePath.Relative[C]] =
    _identityFilePathConvertibleInstance.of[C, Emptiness, True, FilePath.Relative]
  implicit def eyrieNioRootDirectoryRelativityConvertibleInstance[C]: Convertible.Aux[
    Emptiness, True, RootDirectory[C], FilePath.Absolute[C]] =
    _rootDirectoryConvertibleInstance.of[C, Emptiness, True, FilePath.Absolute]
  implicit def eyrieNioRelativeFileRelativityConvertibleInstance[C]: Convertible.Aux[
    Emptiness, False, RelativeFile[C], FilePath.Relative[C]] =
    _relativeFileConvertibleInstance.of[C, Emptiness, False, FilePath.Relative]
  implicit def eyrieNioAbsoluteFileRelativityConvertibleInstance[C]: Convertible.Aux[
    Emptiness, False, AbsoluteFile[C], FilePath.Absolute[C]] =
    _absoluteFileConvertibleInstance.of[C, Emptiness, False, FilePath.Absolute]
}

private[nio]
trait ConvertibleLowPriorityInstances extends ConvertibleLowPriorityInstances1 {
  implicit def eyrieNioIdentityFilePathEmptinessConvertibleInstance[C]: Convertible.Aux[
    Relativity, True, IdentityFilePath[C], FilePath.Empty[C]] =
    _identityFilePathConvertibleInstance.of[C, Relativity, True, FilePath.Empty]
  implicit def eyrieNioRootDirectoryEmptinessConvertibleInstance[C]: Convertible.Aux[
    Relativity, False, RootDirectory[C], FilePath.Empty[C]] =
    _rootDirectoryConvertibleInstance.of[C, Relativity, False, FilePath.Empty]
  implicit def eyrieNioRelativeFileEmptinessConvertibleInstance[C]: Convertible.Aux[
    Relativity, True, RelativeFile[C], FilePath.NonEmpty[C]] =
    _relativeFileConvertibleInstance.of[C, Relativity, True, FilePath.NonEmpty]
  implicit def eyrieNioAbsoluteFileEmptinessConvertibleInstance[C]: Convertible.Aux[
    Relativity, False, AbsoluteFile[C], FilePath.NonEmpty[C]] =
    _absoluteFileConvertibleInstance.of[C, Relativity, False, FilePath.NonEmpty]

  implicit def eyrieNioIdentityFilePathBaseConvertibleInstance[C]: Convertible.Aux[
    EmptinessAndRelativity, (True, True), IdentityFilePath[C], FilePath[C]] =
    _identityFilePathConvertibleInstance.of[C, EmptinessAndRelativity, (True, True), FilePath]
  implicit def eyrieNioRootDirectoryBaseConvertibleInstance[C]: Convertible.Aux[
    EmptinessAndRelativity, (True, False), RootDirectory[C], FilePath[C]] =
    _rootDirectoryConvertibleInstance.of[C, EmptinessAndRelativity, (True, False), FilePath]
  implicit def eyrieNioRelativeFileBaseConvertibleInstance[C]: Convertible.Aux[
    EmptinessAndRelativity, (False, True), RelativeFile[C], FilePath[C]] =
    _relativeFileConvertibleInstance.of[C, EmptinessAndRelativity, (False, True), FilePath]
  implicit def eyrieNioAbsoluteFileBaseConvertibleInstance[C]: Convertible.Aux[
    EmptinessAndRelativity, (False, False), AbsoluteFile[C], FilePath[C]] =
    _absoluteFileConvertibleInstance.of[C, EmptinessAndRelativity, (False, False), FilePath]
}

private[nio]
trait ConvertibleLowPriorityInstances1 {
  private[instances] lazy val _emptyConvertibleInstance = new FilePathConvertible[FilePath.Empty]
  private[instances] lazy val _nonEmptyConvertibleInstance = new FilePathConvertible[FilePath.NonEmpty]
  private[instances] lazy val _relativeConvertibleInstance = new FilePathConvertible[FilePath.Relative]
  private[instances] lazy val _absoluteConvertibleInstance = new FilePathConvertible[FilePath.Absolute]
  private[instances] lazy val _identityFilePathConvertibleInstance = new FilePathConvertible[IdentityFilePath]
  private[instances] lazy val _rootDirectoryConvertibleInstance = new FilePathConvertible[RootDirectory]
  private[instances] lazy val _relativeFileConvertibleInstance = new FilePathConvertible[RelativeFile]
  private[instances] lazy val _absoluteFileConvertibleInstance = new FilePathConvertible[AbsoluteFile]
}
