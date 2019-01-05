package eyrie.file.syntax

import java.nio.file.Path

import eyrie.file._
import eyrie.file.ops.AsJava

trait FileAsJavaSyntax {
  implicit def toAsJava(file: FileName[_]): AsJava[Path] =
    file.asInstanceOf[AsJava[Path]]

  implicit def toAsJava(file: FilePath[_]): AsJava[Path] =
    file.asInstanceOf[AsJava[Path]]

  implicit def toAsJava(file: IdentityFilePath[_]): AsJava[Path] =
    file.asInstanceOf[AsJava[Path]]

  implicit def toAsJava(file: RootDirectory[_]): AsJava[Path] =
    file.asInstanceOf[AsJava[Path]]

  implicit def toAsJava(file: RelativeFile[_]): AsJava[Path] =
    file.asInstanceOf[AsJava[Path]]

  implicit def toAsJava(file: AbsoluteFile[_]): AsJava[Path] =
    file.asInstanceOf[AsJava[Path]]

  implicit def toAsJava(file: FilePath.Empty[_]): AsJava[Path] =
    file.asInstanceOf[AsJava[Path]]

  implicit def toAsJava(file: FilePath.NonEmpty[_]): AsJava[Path] =
    file.asInstanceOf[AsJava[Path]]

  implicit def toAsJava(file: FilePath.Relative[_]): AsJava[Path] =
    file.asInstanceOf[AsJava[Path]]

  implicit def toAsJava(file: FilePath.Absolute[_]): AsJava[Path] =
    file.asInstanceOf[AsJava[Path]]
}
