package eyrie.file

import java.nio.file.Path

import eyrie.file
import eyrie.file.instances._
import eyrie.file.ops.AsJava

sealed trait FileName[C] extends Any
object FileName extends FileNameInstances

sealed trait FilePath[C] extends Any

sealed trait IdentityFilePath[C] extends Any
object IdentityFilePath extends IdentityFilePathInstances

sealed trait RootDirectory[C] extends Any
object RootDirectory extends RootDirectoryInstances

sealed trait RelativeFile[C] extends Any
object RelativeFile extends RelativeFileInstances

sealed trait AbsoluteFile[C] extends Any
object AbsoluteFile extends AbsoluteFileInstances

object FilePath extends FilePathInstances {
  sealed trait Empty[C] extends Any
  object Empty extends EmptyInstances

  sealed trait NonEmpty[C] extends Any
  object NonEmpty extends NonEmptyInstances

  sealed trait Relative[C] extends Any
  object Relative extends RelativeInstances

  sealed trait Absolute[C] extends Any
  object Absolute extends AbsoluteInstances


  private[file]
  object Internal {
    final case class FileName[C](override val asJava: Path) extends AnyVal
      with file.FileName[C] with AsJava.Ops[Path]

    final case class IdentityFilePath[C](override val asJava: Path) extends AnyVal
      with file.IdentityFilePath[C] with Relative[C] with Empty[C] with FilePath[C] with AsJava.Ops[Path]

    final case class RootDirectory[C](override val asJava: Path) extends AnyVal
      with file.RootDirectory[C] with Absolute[C] with Empty[C] with FilePath[C] with AsJava.Ops[Path]

    final case class RelativeFile[C](override val asJava: Path) extends AnyVal
      with file.RelativeFile[C] with Relative[C] with NonEmpty[C] with FilePath[C] with AsJava.Ops[Path]

    final case class AbsoluteFile[C](override val asJava: Path) extends AnyVal
      with file.AbsoluteFile[C] with Absolute[C] with NonEmpty[C] with FilePath[C] with AsJava.Ops[Path]
  }
}
