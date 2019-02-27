package eyrie.file.instances

import cats.effect.Sync
import eyrie.file.ops.Directory
import fs2.Stream

private[eyrie]
trait DirectoryByInputInstances {
  implicit def eyrieFileDirectoryByInputInstance[A, B](
    implicit A: Directory[A, B]
  ): Directory.ByInput.Aux[A, B] =
    new Directory.ByInput[A] {
      override
      type Segment = B

      override
      def listFiles[F[_] : Sync](a: A): Stream[F, Segment] =
        A.listFiles(a)
    }
}
