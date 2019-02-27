package eyrie.file.syntax

import cats.effect.Sync
import eyrie.file.ops.Directory
import fs2.Stream

trait DirectorySyntax {
  implicit def toDirectoryOps[A](a: A): DirectoryOps[A] =
    new DirectoryOps(a)
}

final class DirectoryOps[A](private val a: A) extends AnyVal {
  def listFiles[F[_] : Sync](implicit A: Directory.ByInput[A]): Stream[F, A.Segment] =
    A.listFiles(a)
}
