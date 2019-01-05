package eyrie.file

import java.nio.file.Path

import eyrie.file
import eyrie.file.ops.AsJava

sealed trait FileName[C] extends Any
object FileName

sealed trait File[C] extends Any

sealed trait IdentityFilePath[C] extends Any
object IdentityFilePath

sealed trait RootDirectory[C] extends Any
object RootDirectory

sealed trait RelativeFile[C] extends Any
object RelativeFile extends RelativeFileInstances

sealed trait AbsoluteFile[C] extends Any
object AbsoluteFile extends AbsoluteFileInstances

object File {
  sealed trait Empty[C] extends Any
  object Empty

  sealed trait NonEmpty[C] extends Any
  object NonEmpty

  sealed trait Relative[C] extends Any
  object Relative

  sealed trait Absolute[C] extends Any
  object Absolute


  private[file]
  object Internal {
    final case class FileName[C](override val asJava: Path) extends AnyVal
      with file.FileName[C] with AsJava[Path]

    final case class File[C](override val asJava: Path) extends AnyVal
      with file.File[C] with AsJava[Path]

    final case class AbsoluteFile[C](override val asJava: Path) extends AnyVal
      with file.AbsoluteFile[C] with AsJava[Path]

    final case class RelativeFile[C](override val asJava: Path) extends AnyVal
      with file.RelativeFile[C] with AsJava[Path]

    final case class IdentityFilePath[C](override val asJava: Path) extends AnyVal
      with file.IdentityFilePath[C] with AsJava[Path]

    final case class RootDirectory[C](override val asJava: Path) extends AnyVal
      with file.RootDirectory[C] with AsJava[Path]

    object File {
      final case class Empty[C](override val asJava: Path) extends AnyVal
        with file.File.Empty[C] with AsJava[Path]

      final case class NonEmpty[C](override val asJava: Path) extends AnyVal
        with file.File.NonEmpty[C] with AsJava[Path]

      final case class Relative[C](override val asJava: Path) extends AnyVal
        with file.File.Relative[C] with AsJava[Path]

      final case class Absolute[C](override val asJava: Path) extends AnyVal
        with file.File.Absolute[C] with AsJava[Path]
    }
  }
}
