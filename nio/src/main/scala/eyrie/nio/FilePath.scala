package eyrie.nio

import java.nio.file.Path

import eyrie.nio
import eyrie.nio.instances._
import eyrie.nio.ops.JavaMirror

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


  private[nio]
  object Internal {
    final case class FileName[C](override val asJava: Path) extends AnyVal
      with nio.FileName[C] with JavaMirror.Ops[Path]

    final case class IdentityFilePath[C](override val asJava: Path) extends AnyVal
      with nio.IdentityFilePath[C] with Relative[C] with Empty[C] with FilePath[C] with JavaMirror.Ops[Path]

    final case class RootDirectory[C](override val asJava: Path) extends AnyVal
      with nio.RootDirectory[C] with Absolute[C] with Empty[C] with FilePath[C] with JavaMirror.Ops[Path]

    final case class RelativeFile[C](override val asJava: Path) extends AnyVal
      with nio.RelativeFile[C] with Relative[C] with NonEmpty[C] with FilePath[C] with JavaMirror.Ops[Path]

    final case class AbsoluteFile[C](override val asJava: Path) extends AnyVal
      with nio.AbsoluteFile[C] with Absolute[C] with NonEmpty[C] with FilePath[C] with JavaMirror.Ops[Path]
  }
}
