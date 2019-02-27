package eyrie.file.ops

import cats.effect.Sync
import eyrie.file.instances.DirectoryByInputInstances
import fs2.Stream
import simulacrum.typeclass

trait Directory[A, B] {
  def listFiles[F[_] : Sync](a: A): Stream[F, B]
}

object Directory {
  @inline
  def apply[A, B](implicit A: Directory[A, B]): Directory[A, B] = A


  @typeclass
  trait ByInput[A] {
    type Segment

    def listFiles[F[_] : Sync](a: A): Stream[F, Segment]
  }

  object ByInput extends DirectoryByInputInstances {
    type Aux[A, B] = ByInput[A] {
      type Segment = B
    }
  }
}
