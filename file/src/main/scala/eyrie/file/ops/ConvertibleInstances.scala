package eyrie.file.ops

import eyrie.file.{AbsoluteFile, Emptiness, FilePath, IdentityFilePath, RelativeFile, Relativity, RootDirectory}
import eyrie.ops.{Convertible, PotentialSuccessor}

import scala.reflect.ClassTag

private[file]
class FilePathConvertible[A[_], B[_]](implicit A: ClassTag[A[_]]) extends Convertible[A[_], B[_]] {
  override
  def widen: A[_] => B[_] =
    _.asInstanceOf[B[_]]

  override
  def narrow: B[_] => Option[A[_]] =
    A.unapply
}

private[file]
class ConvertibleInstances extends LowPriorityConvertibleInstances {
  implicit def emptyConvertibleInstance[C]: Convertible[FilePath.Empty[C], FilePath[C]] =
    _emptyConvertibleInstance.asInstanceOf[Convertible[FilePath.Empty[C], FilePath[C]]]
  implicit def nonEmptyConvertibleInstance[C]: Convertible[FilePath.NonEmpty[C], FilePath[C]] =
    _nonEmptyConvertibleInstance.asInstanceOf[Convertible[FilePath.NonEmpty[C], FilePath[C]]]
  implicit def relativeConvertibleInstance[C]: Convertible[FilePath.Relative[C], FilePath[C]] =
    _relativeConvertibleInstance.asInstanceOf[Convertible[FilePath.Relative[C], FilePath[C]]]
  implicit def absoluteConvertibleInstance[C]: Convertible[FilePath.Absolute[C], FilePath[C]] =
    _absoluteConvertibleInstance.asInstanceOf[Convertible[FilePath.Absolute[C], FilePath[C]]]

  implicit def identityFilePathRelativityConvertibleInstance[C]: Convertible.Aux[Emptiness, IdentityFilePath[C], FilePath.Relative[C]] =
    _identityFilePathConvertibleInstance.asInstanceOf[Convertible.Aux[Emptiness, IdentityFilePath[C], FilePath.Relative[C]]]
  implicit def rootDirectoryRelativityConvertibleInstance[C]: Convertible.Aux[Emptiness, RootDirectory[C], FilePath.Absolute[C]] =
    _rootDirectoryConvertibleInstance.asInstanceOf[Convertible.Aux[Emptiness, RootDirectory[C], FilePath.Absolute[C]]]
  implicit def relativeFileRelativityConvertibleInstance[C]: Convertible.Aux[Emptiness, RelativeFile[C], FilePath.Relative[C]] =
    _relativeFileConvertibleInstance.asInstanceOf[Convertible.Aux[Emptiness, RelativeFile[C], FilePath.Relative[C]]]
  implicit def absoluteFileRelativityConvertibleInstance[C]: Convertible.Aux[Emptiness, AbsoluteFile[C], FilePath.Absolute[C]] =
    _absoluteFileConvertibleInstance.asInstanceOf[Convertible.Aux[Emptiness, AbsoluteFile[C], FilePath.Absolute[C]]]
}

private[file]
trait LowPriorityConvertibleInstances extends LowPriorityConvertibleInstances1 {
  implicit def identityFilePathEmptinessConvertibleInstance[C]: Convertible.Aux[Relativity, IdentityFilePath[C], FilePath.Empty[C]] =
    _identityFilePathConvertibleInstance.asInstanceOf[Convertible.Aux[Relativity, IdentityFilePath[C], FilePath.Empty[C]]]
  implicit def rootDirectoryEmptinessConvertibleInstance[C]: Convertible.Aux[Relativity, RootDirectory[C], FilePath.Empty[C]] =
    _rootDirectoryConvertibleInstance.asInstanceOf[Convertible.Aux[Relativity, RootDirectory[C], FilePath.Empty[C]]]
  implicit def relativeFileEmptinessConvertibleInstance[C]: Convertible.Aux[Relativity, RelativeFile[C], FilePath.NonEmpty[C]] =
    _relativeFileConvertibleInstance.asInstanceOf[Convertible.Aux[Relativity, RelativeFile[C], FilePath.NonEmpty[C]]]
  implicit def absoluteFileEmptinessConvertibleInstance[C]: Convertible.Aux[Relativity, AbsoluteFile[C], FilePath.NonEmpty[C]] =
    _absoluteFileConvertibleInstance.asInstanceOf[Convertible.Aux[Relativity, AbsoluteFile[C], FilePath.NonEmpty[C]]]

  implicit def identityFilePathBaseConvertibleInstance[C]: Convertible[IdentityFilePath[C], FilePath[C]] =
    _identityFilePathConvertibleInstance.asInstanceOf[Convertible[IdentityFilePath[C], FilePath[C]]]
  implicit def rootDirectoryBaseConvertibleInstance[C]: Convertible[RootDirectory[C], FilePath[C]] =
    _rootDirectoryConvertibleInstance.asInstanceOf[Convertible[RootDirectory[C], FilePath[C]]]
  implicit def relativeFileBaseConvertibleInstance[C]: Convertible[RelativeFile[C], FilePath[C]] =
    _relativeFileConvertibleInstance.asInstanceOf[Convertible[RelativeFile[C], FilePath[C]]]
  implicit def absoluteFileBaseConvertibleInstance[C]: Convertible[AbsoluteFile[C], FilePath[C]] =
    _absoluteFileConvertibleInstance.asInstanceOf[Convertible[AbsoluteFile[C], FilePath[C]]]
}

private[file]
trait LowPriorityConvertibleInstances1 {
  implicit def relativeFilePotentialSuccessorConvertibleInstance[C]: Convertible.Aux[PotentialSuccessor, RelativeFile[C], FilePath.Relative[C]] =
    _relativeFileConvertibleInstance.asInstanceOf[Convertible.Aux[PotentialSuccessor, RelativeFile[C], FilePath.Relative[C]]]
  implicit def absoluteFilePotentialSuccessorConvertibleInstance[C]: Convertible.Aux[PotentialSuccessor, AbsoluteFile[C], FilePath.Absolute[C]] =
    _absoluteFileConvertibleInstance.asInstanceOf[Convertible.Aux[PotentialSuccessor, AbsoluteFile[C], FilePath.Absolute[C]]]

  private[ops] lazy val _emptyConvertibleInstance = new FilePathConvertible[FilePath.Empty, FilePath]
  private[ops] lazy val _nonEmptyConvertibleInstance = new FilePathConvertible[FilePath.NonEmpty, FilePath]
  private[ops] lazy val _relativeConvertibleInstance = new FilePathConvertible[FilePath.Relative, FilePath]
  private[ops] lazy val _absoluteConvertibleInstance = new FilePathConvertible[FilePath.Absolute, FilePath]
  private[ops] lazy val _identityFilePathConvertibleInstance = new FilePathConvertible[IdentityFilePath, FilePath]
  private[ops] lazy val _rootDirectoryConvertibleInstance = new FilePathConvertible[RootDirectory, FilePath]
  private[ops] lazy val _relativeFileConvertibleInstance = new FilePathConvertible[RelativeFile, FilePath]
  private[ops] lazy val _absoluteFileConvertibleInstance = new FilePathConvertible[AbsoluteFile, FilePath]
}
