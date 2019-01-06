package eyrie.file.ops

import eyrie.file.{AbsoluteFile, Emptiness, FilePath, IdentityFilePath, RelativeFile, Relativity, RootDirectory}
import eyrie.ops.Convertible

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
  private lazy val _emptyConvertibleInstance = new FilePathConvertible[FilePath.Empty, FilePath]
  private lazy val _nonEmptyConvertibleInstance = new FilePathConvertible[FilePath.NonEmpty, FilePath]
  private lazy val _relativeConvertibleInstance = new FilePathConvertible[FilePath.Relative, FilePath]
  private lazy val _absoluteConvertibleInstance = new FilePathConvertible[FilePath.Absolute, FilePath]

  implicit def emptyConvertibleInstance[C]: Convertible[FilePath.Empty[C], FilePath[C]] =
    _emptyConvertibleInstance.asInstanceOf[Convertible[FilePath.Empty[C], FilePath[C]]]
  implicit def nonEmptyConvertibleInstance[C]: Convertible[FilePath.NonEmpty[C], FilePath[C]] =
    _nonEmptyConvertibleInstance.asInstanceOf[Convertible[FilePath.NonEmpty[C], FilePath[C]]]
  implicit def relativeConvertibleInstance[C]: Convertible[FilePath.Relative[C], FilePath[C]] =
    _relativeConvertibleInstance.asInstanceOf[Convertible[FilePath.Relative[C], FilePath[C]]]
  implicit def absoluteConvertibleInstance[C]: Convertible[FilePath.Absolute[C], FilePath[C]] =
    _absoluteConvertibleInstance.asInstanceOf[Convertible[FilePath.Absolute[C], FilePath[C]]]

  implicit def identityFilePathRelativityConvertibleInstance[C]: Convertible.Aux[IdentityFilePath[C], FilePath.Relative[C], Emptiness] =
    _identityFilePathConvertibleInstance.asInstanceOf[Convertible.Aux[IdentityFilePath[C], FilePath.Relative[C], Emptiness]]
  implicit def rootDirectoryRelativityConvertibleInstance[C]: Convertible.Aux[RootDirectory[C], FilePath.Absolute[C], Emptiness] =
    _rootDirectoryConvertibleInstance.asInstanceOf[Convertible.Aux[RootDirectory[C], FilePath.Absolute[C], Emptiness]]
  implicit def relativeFileRelativityConvertibleInstance[C]: Convertible.Aux[RelativeFile[C], FilePath.Relative[C], Emptiness] =
    _relativeFileConvertibleInstance.asInstanceOf[Convertible.Aux[RelativeFile[C], FilePath.Relative[C], Emptiness]]
  implicit def absoluteFileRelativityConvertibleInstance[C]: Convertible.Aux[AbsoluteFile[C], FilePath.Absolute[C], Emptiness] =
    _absoluteFileConvertibleInstance.asInstanceOf[Convertible.Aux[AbsoluteFile[C], FilePath.Absolute[C], Emptiness]]
}

private[file]
trait LowPriorityConvertibleInstances {
  private[ops] lazy val _identityFilePathConvertibleInstance = new FilePathConvertible[IdentityFilePath, FilePath]
  private[ops] lazy val _rootDirectoryConvertibleInstance = new FilePathConvertible[RootDirectory, FilePath]
  private[ops] lazy val _relativeFileConvertibleInstance = new FilePathConvertible[RelativeFile, FilePath]
  private[ops] lazy val _absoluteFileConvertibleInstance = new FilePathConvertible[AbsoluteFile, FilePath]

  implicit def identityFilePathEmptinessConvertibleInstance[C]: Convertible.Aux[IdentityFilePath[C], FilePath.Empty[C], Relativity] =
    _identityFilePathConvertibleInstance.asInstanceOf[Convertible.Aux[IdentityFilePath[C], FilePath.Empty[C], Relativity]]
  implicit def rootDirectoryEmptinessConvertibleInstance[C]: Convertible.Aux[RootDirectory[C], FilePath.Empty[C], Relativity] =
    _rootDirectoryConvertibleInstance.asInstanceOf[Convertible.Aux[RootDirectory[C], FilePath.Empty[C], Relativity]]
  implicit def relativeFileEmptinessConvertibleInstance[C]: Convertible.Aux[RelativeFile[C], FilePath.NonEmpty[C], Relativity] =
    _relativeFileConvertibleInstance.asInstanceOf[Convertible.Aux[RelativeFile[C], FilePath.NonEmpty[C], Relativity]]
  implicit def absoluteFileEmptinessConvertibleInstance[C]: Convertible.Aux[AbsoluteFile[C], FilePath.NonEmpty[C], Relativity] =
    _absoluteFileConvertibleInstance.asInstanceOf[Convertible.Aux[AbsoluteFile[C], FilePath.NonEmpty[C], Relativity]]

  implicit def identityFilePathBaseConvertibleInstance[C]: Convertible[IdentityFilePath[C], FilePath[C]] =
    _identityFilePathConvertibleInstance.asInstanceOf[Convertible[IdentityFilePath[C], FilePath[C]]]
  implicit def rootDirectoryBaseConvertibleInstance[C]: Convertible[RootDirectory[C], FilePath[C]] =
    _rootDirectoryConvertibleInstance.asInstanceOf[Convertible[RootDirectory[C], FilePath[C]]]
  implicit def relativeFileBaseConvertibleInstance[C]: Convertible[RelativeFile[C], FilePath[C]] =
    _relativeFileConvertibleInstance.asInstanceOf[Convertible[RelativeFile[C], FilePath[C]]]
  implicit def absoluteFileBaseConvertibleInstance[C]: Convertible[AbsoluteFile[C], FilePath[C]] =
    _absoluteFileConvertibleInstance.asInstanceOf[Convertible[AbsoluteFile[C], FilePath[C]]]
}
