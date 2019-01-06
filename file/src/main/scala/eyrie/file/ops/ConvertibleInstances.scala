package eyrie.file.ops

import eyrie.file._
import eyrie.ops.Convertible

import scala.reflect.ClassTag

private[file]
class FilePathConvertible[A[_], B[_]](implicit A: ClassTag[A[_]]) extends Convertible[A[_], B[_]] {
  override
  def widen(a: A[_]): B[_] =
    a.asInstanceOf[B[_]]

  override
  def narrow(b: B[_]): Option[A[_]] =
    A.unapply(b)
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

  implicit def identityFilePathRelativityConvertibleInstance[C]: Convertible[IdentityFilePath[C], FilePath.Relative[C]] =
    _identityFilePathConvertibleInstance.asInstanceOf[Convertible[IdentityFilePath[C], FilePath.Relative[C]]]
  implicit def rootDirectoryRelativityConvertibleInstance[C]: Convertible[RootDirectory[C], FilePath.Absolute[C]] =
    _rootDirectoryConvertibleInstance.asInstanceOf[Convertible[RootDirectory[C], FilePath.Absolute[C]]]
  implicit def relativeFileRelativityConvertibleInstance[C]: Convertible[RelativeFile[C], FilePath.Relative[C]] =
    _relativeFileConvertibleInstance.asInstanceOf[Convertible[RelativeFile[C], FilePath.Relative[C]]]
  implicit def absoluteFileRelativityConvertibleInstance[C]: Convertible[AbsoluteFile[C], FilePath.Absolute[C]] =
    _absoluteFileConvertibleInstance.asInstanceOf[Convertible[AbsoluteFile[C], FilePath.Absolute[C]]]
}

private[file]
trait LowPriorityConvertibleInstances extends LowPriorityConvertibleInstances1 {
  implicit def identityFilePathEmptinessConvertibleInstance[C]: Convertible[IdentityFilePath[C], FilePath.Empty[C]] =
    _identityFilePathConvertibleInstance.asInstanceOf[Convertible[IdentityFilePath[C], FilePath.Empty[C]]]
  implicit def rootDirectoryEmptinessConvertibleInstance[C]: Convertible[RootDirectory[C], FilePath.Empty[C]] =
    _rootDirectoryConvertibleInstance.asInstanceOf[Convertible[RootDirectory[C], FilePath.Empty[C]]]
  implicit def relativeFileEmptinessConvertibleInstance[C]: Convertible[RelativeFile[C], FilePath.NonEmpty[C]] =
    _relativeFileConvertibleInstance.asInstanceOf[Convertible[RelativeFile[C], FilePath.NonEmpty[C]]]
  implicit def absoluteFileEmptinessConvertibleInstance[C]: Convertible[AbsoluteFile[C], FilePath.NonEmpty[C]] =
    _absoluteFileConvertibleInstance.asInstanceOf[Convertible[AbsoluteFile[C], FilePath.NonEmpty[C]]]
}

private[file]
trait LowPriorityConvertibleInstances1 {
  private[ops] lazy val _identityFilePathConvertibleInstance = new FilePathConvertible[IdentityFilePath, FilePath]
  private[ops] lazy val _rootDirectoryConvertibleInstance = new FilePathConvertible[RootDirectory, FilePath]
  private[ops] lazy val _relativeFileConvertibleInstance = new FilePathConvertible[RelativeFile, FilePath]
  private[ops] lazy val _absoluteFileConvertibleInstance = new FilePathConvertible[AbsoluteFile, FilePath]

  implicit def identityFilePathBaseConvertibleInstance[C]: Convertible[IdentityFilePath[C], FilePath[C]] =
    _identityFilePathConvertibleInstance.asInstanceOf[Convertible[IdentityFilePath[C], FilePath[C]]]
  implicit def rootDirectoryBaseConvertibleInstance[C]: Convertible[RootDirectory[C], FilePath[C]] =
    _rootDirectoryConvertibleInstance.asInstanceOf[Convertible[RootDirectory[C], FilePath[C]]]
  implicit def relativeFileBaseConvertibleInstance[C]: Convertible[RelativeFile[C], FilePath[C]] =
    _relativeFileConvertibleInstance.asInstanceOf[Convertible[RelativeFile[C], FilePath[C]]]
  implicit def absoluteFileBaseConvertibleInstance[C]: Convertible[AbsoluteFile[C], FilePath[C]] =
    _absoluteFileConvertibleInstance.asInstanceOf[Convertible[AbsoluteFile[C], FilePath[C]]]
}
