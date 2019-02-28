package eyrie.nio.context

import java.nio.file._

import eyrie.nio.FilePath
import eyrie.nio.FilePath.Internal
import eyrie.nio.ops.{FilePathScheme, JavaMirror}

sealed trait Sys

object Sys {
  implicit val eyrieNioSysInstance: Sys =
    new Sys { }

  implicit val eyrieNioAsJavaInstance: JavaMirror.Aux[Sys, FileSystem] =
    new JavaMirror[Sys] with JavaMirror.Ops[FileSystem] {
      override
      type Out = FileSystem

      override
      def asJavaOps(a: Sys): JavaMirror.Ops[FileSystem] =
        this

      override
      def asJava: FileSystem =
        FileSystems.getDefault
    }

  private
  def relative(path: Path): FilePath[Sys] =
    if (path.getNameCount < 2 && Option(path.getFileName).forall(_.toString.isEmpty)) {
      Internal.IdentityFilePath(path)
    } else {
      Internal.RelativeFile(path)
    }

  private
  def absolute(path: Path): FilePath[Sys] =
    if (path.getNameCount < 1) {
      Internal.RootDirectory(path)
    } else {
      Internal.AbsoluteFile(path)
    }

  implicit val eyrieNioFilePathScheme: FilePathScheme[Sys] =
    string =>
      try {
        Some {
          val parsed = Paths.get(string)

          if (parsed.isAbsolute) {
            absolute(parsed)
          } else {
            relative(parsed)
          }
        }
      } catch {
        case _: InvalidPathException =>
          None
      }
}
