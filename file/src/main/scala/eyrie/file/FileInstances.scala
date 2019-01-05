package eyrie.file

import eyrie.file.ops.FileConvertible
import eyrie.ops.Convertible

private[file]
trait FileInstances {
  private lazy val _emptyConvertibleInstance = new FileConvertible[FilePath.Empty, FilePath]
  private lazy val _nonEmptyConvertibleInstance = new FileConvertible[FilePath.NonEmpty, FilePath]
  private lazy val _relativeConvertibleInstance = new FileConvertible[FilePath.Relative, FilePath]
  private lazy val _absoluteConvertibleInstance = new FileConvertible[FilePath.Absolute, FilePath]
  private lazy val _identityFilePathConvertibleInstance = new FileConvertible[IdentityFilePath, FilePath]
  private lazy val _rootDirectoryConvertibleInstance = new FileConvertible[RootDirectory, FilePath]
  private lazy val _relativeFileConvertibleInstance = new FileConvertible[RelativeFile, FilePath]
  private lazy val _absoluteFileConvertibleInstance = new FileConvertible[AbsoluteFile, FilePath]

  implicit def emptyConvertibleInstance[C]: Convertible[FilePath.Empty[C], FilePath[C]] =
    _emptyConvertibleInstance.asInstanceOf[Convertible[FilePath.Empty[C], FilePath[C]]]
  implicit def nonEmptyConvertibleInstance[C]: Convertible[FilePath.NonEmpty[C], FilePath[C]] =
    _nonEmptyConvertibleInstance.asInstanceOf[Convertible[FilePath.NonEmpty[C], FilePath[C]]]
  implicit def relativeConvertibleInstance[C]: Convertible[FilePath.Relative[C], FilePath[C]] =
    _relativeConvertibleInstance.asInstanceOf[Convertible[FilePath.Relative[C], FilePath[C]]]
  implicit def absoluteConvertibleInstance[C]: Convertible[FilePath.Absolute[C], FilePath[C]] =
    _absoluteConvertibleInstance.asInstanceOf[Convertible[FilePath.Absolute[C], FilePath[C]]]
  implicit def identityFilePathConvertibleInstance[C]: Convertible[IdentityFilePath[C], FilePath[C]] =
    _identityFilePathConvertibleInstance.asInstanceOf[Convertible[IdentityFilePath[C], FilePath[C]]]
  implicit def rootDirectoryConvertibleInstance[C]: Convertible[RootDirectory[C], FilePath[C]] =
    _rootDirectoryConvertibleInstance.asInstanceOf[Convertible[RootDirectory[C], FilePath[C]]]
  implicit def relativeFileConvertibleInstance[C]: Convertible[RelativeFile[C], FilePath[C]] =
    _relativeFileConvertibleInstance.asInstanceOf[Convertible[RelativeFile[C], FilePath[C]]]
  implicit def absoluteFileConvertibleInstance[C]: Convertible[AbsoluteFile[C], FilePath[C]] =
    _absoluteFileConvertibleInstance.asInstanceOf[Convertible[AbsoluteFile[C], FilePath[C]]]
}