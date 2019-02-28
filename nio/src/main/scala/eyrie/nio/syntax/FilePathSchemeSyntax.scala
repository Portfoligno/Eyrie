package eyrie.nio.syntax

import eyrie.nio.FilePath
import eyrie.nio.ops.FilePathScheme

trait FilePathSchemeSyntax {
  implicit def toFilePathSchemeOps(s: String): FilePathSchemeOps =
    new FilePathSchemeOps(s)
}

final class FilePathSchemeOps(private val s: String) extends AnyVal {
  def toFilePath[A](implicit A: FilePathScheme[A]): Option[FilePath[A]] =
    A.getPath(s)
}
