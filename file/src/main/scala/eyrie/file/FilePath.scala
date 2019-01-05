package eyrie.file

import java.nio.file.Path

import eyrie.file
import eyrie.file.ops.{AsJava, FileConvertibleInstances}

sealed trait FileName[C] extends Any
object FileName

sealed trait FilePath[C] extends Any

sealed trait IdentityFilePath[C] extends Any
object IdentityFilePath

sealed trait RootDirectory[C] extends Any
object RootDirectory

sealed trait RelativeFile[C] extends Any
object RelativeFile extends RelativeFileInstances

sealed trait AbsoluteFile[C] extends Any
object AbsoluteFile extends AbsoluteFileInstances

object FilePath extends FileInstances {
  sealed trait Empty[C] extends Any
  object Empty extends FileConvertibleInstances[Empty, IdentityFilePath, RootDirectory]

  sealed trait NonEmpty[C] extends Any
  object NonEmpty extends FileConvertibleInstances[NonEmpty, RelativeFile, AbsoluteFile]

  sealed trait Relative[C] extends Any
  object Relative extends FileConvertibleInstances[Relative, IdentityFilePath, RelativeFile]

  sealed trait Absolute[C] extends Any
  object Absolute extends FileConvertibleInstances[Absolute, RootDirectory, AbsoluteFile]


  private[file]
  object Internal {
    final case class FileName[C](override val asJava: Path) extends AnyVal
      with file.FileName[C] with AsJava[Path]

    final case class IdentityFilePath[C](override val asJava: Path) extends AnyVal
      with file.IdentityFilePath[C] with Relative[C] with Empty[C] with FilePath[C] with AsJava[Path]

    final case class RootDirectory[C](override val asJava: Path) extends AnyVal
      with file.RootDirectory[C] with Absolute[C] with Empty[C] with FilePath[C] with AsJava[Path]

    final case class RelativeFile[C](override val asJava: Path) extends AnyVal
      with file.RelativeFile[C] with Relative[C] with NonEmpty[C] with FilePath[C] with AsJava[Path]

    final case class AbsoluteFile[C](override val asJava: Path) extends AnyVal
      with file.AbsoluteFile[C] with Absolute[C] with NonEmpty[C] with FilePath[C] with AsJava[Path]
  }
}
