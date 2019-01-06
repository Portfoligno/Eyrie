package eyrie.file.ops

import eyrie.file.{AbsoluteFile, Emptiness, FilePath, IdentityFilePath, RelativeFile, Relativity, RootDirectory}
import eyrie.ops.{Convertible, PotentialSuccessor}

import scala.reflect.ClassTag

private
class FilePathConvertible[A[_]](implicit A: ClassTag[A[_]]) extends Convertible[A[_], FilePath[_]] {
  override
  def widen: A[_] => FilePath[_] =
    _.asInstanceOf[FilePath[_]]

  override
  def narrow: FilePath[_] => Option[A[_]] =
    A.unapply


  def of[C, B[_]]: Convertible[A[C], B[C]] =
    asInstanceOf[Convertible[A[C], B[C]]]

  def ofAux[C, Attr[_], B[_]]: Convertible.Aux[Attr, A[C], B[C]] =
    asInstanceOf[Convertible.Aux[Attr, A[C], B[C]]]
}

private[file]
class ConvertibleInstances extends LowPriorityConvertibleInstances {
  implicit def emptyConvertibleInstance[C]: Convertible[FilePath.Empty[C], FilePath[C]] =
    _emptyConvertibleInstance.of[C, FilePath]
  implicit def nonEmptyConvertibleInstance[C]: Convertible[FilePath.NonEmpty[C], FilePath[C]] =
    _nonEmptyConvertibleInstance.of[C, FilePath]
  implicit def relativeConvertibleInstance[C]: Convertible[FilePath.Relative[C], FilePath[C]] =
    _relativeConvertibleInstance.of[C, FilePath]
  implicit def absoluteConvertibleInstance[C]: Convertible[FilePath.Absolute[C], FilePath[C]] =
    _absoluteConvertibleInstance.of[C, FilePath]

  implicit def identityFilePathRelativityConvertibleInstance[C]: Convertible.Aux[
    Emptiness, IdentityFilePath[C], FilePath.Relative[C]] =
    _identityFilePathConvertibleInstance.ofAux[C, Emptiness, FilePath.Relative]
  implicit def rootDirectoryRelativityConvertibleInstance[C]: Convertible.Aux[
    Emptiness, RootDirectory[C], FilePath.Absolute[C]] =
    _rootDirectoryConvertibleInstance.ofAux[C, Emptiness, FilePath.Absolute]
  implicit def relativeFileRelativityConvertibleInstance[C]: Convertible.Aux[
    Emptiness, RelativeFile[C], FilePath.Relative[C]] =
    _relativeFileConvertibleInstance.ofAux[C, Emptiness, FilePath.Relative]
  implicit def absoluteFileRelativityConvertibleInstance[C]: Convertible.Aux[
    Emptiness, AbsoluteFile[C], FilePath.Absolute[C]] =
    _absoluteFileConvertibleInstance.ofAux[C, Emptiness, FilePath.Absolute]
}

private[file]
trait LowPriorityConvertibleInstances extends LowPriorityConvertibleInstances1 {
  implicit def identityFilePathEmptinessConvertibleInstance[C]: Convertible.Aux[
    Relativity, IdentityFilePath[C], FilePath.Empty[C]] =
    _identityFilePathConvertibleInstance.ofAux[C, Relativity, FilePath.Empty]
  implicit def rootDirectoryEmptinessConvertibleInstance[C]: Convertible.Aux[
    Relativity, RootDirectory[C], FilePath.Empty[C]] =
    _rootDirectoryConvertibleInstance.ofAux[C, Relativity, FilePath.Empty]
  implicit def relativeFileEmptinessConvertibleInstance[C]: Convertible.Aux[
    Relativity, RelativeFile[C], FilePath.NonEmpty[C]] =
    _relativeFileConvertibleInstance.ofAux[C, Relativity, FilePath.NonEmpty]
  implicit def absoluteFileEmptinessConvertibleInstance[C]: Convertible.Aux[
    Relativity, AbsoluteFile[C], FilePath.NonEmpty[C]] =
    _absoluteFileConvertibleInstance.ofAux[C, Relativity, FilePath.NonEmpty]

  implicit def identityFilePathBaseConvertibleInstance[C]: Convertible[IdentityFilePath[C], FilePath[C]] =
    _identityFilePathConvertibleInstance.of[C, FilePath]
  implicit def rootDirectoryBaseConvertibleInstance[C]: Convertible[RootDirectory[C], FilePath[C]] =
    _rootDirectoryConvertibleInstance.of[C, FilePath]
  implicit def relativeFileBaseConvertibleInstance[C]: Convertible[RelativeFile[C], FilePath[C]] =
    _relativeFileConvertibleInstance.of[C, FilePath]
  implicit def absoluteFileBaseConvertibleInstance[C]: Convertible[AbsoluteFile[C], FilePath[C]] =
    _absoluteFileConvertibleInstance.of[C, FilePath]
}

private[file]
trait LowPriorityConvertibleInstances1 {
  implicit def relativeFilePotentialSuccessorConvertibleInstance[C]: Convertible.Aux[
    PotentialSuccessor, RelativeFile[C], FilePath.Relative[C]] =
    _relativeFileConvertibleInstance.ofAux[C, PotentialSuccessor, FilePath.Relative]
  implicit def absoluteFilePotentialSuccessorConvertibleInstance[C]: Convertible.Aux[
    PotentialSuccessor, AbsoluteFile[C], FilePath.Absolute[C]] =
    _absoluteFileConvertibleInstance.ofAux[C, PotentialSuccessor, FilePath.Absolute]

  private[ops] lazy val _emptyConvertibleInstance = new FilePathConvertible[FilePath.Empty]
  private[ops] lazy val _nonEmptyConvertibleInstance = new FilePathConvertible[FilePath.NonEmpty]
  private[ops] lazy val _relativeConvertibleInstance = new FilePathConvertible[FilePath.Relative]
  private[ops] lazy val _absoluteConvertibleInstance = new FilePathConvertible[FilePath.Absolute]
  private[ops] lazy val _identityFilePathConvertibleInstance = new FilePathConvertible[IdentityFilePath]
  private[ops] lazy val _rootDirectoryConvertibleInstance = new FilePathConvertible[RootDirectory]
  private[ops] lazy val _relativeFileConvertibleInstance = new FilePathConvertible[RelativeFile]
  private[ops] lazy val _absoluteFileConvertibleInstance = new FilePathConvertible[AbsoluteFile]
}
