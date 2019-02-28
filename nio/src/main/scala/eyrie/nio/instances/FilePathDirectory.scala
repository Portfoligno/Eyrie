package eyrie.nio.instances

import java.nio.file.Files

import cats.effect.{Concurrent, Resource, Sync}
import eyrie.file.ops.Directory
import eyrie.nio.FilePath.Internal
import eyrie.nio.{AbsoluteFile, FileName}
import fs2.Stream

import scala.collection.JavaConverters._

private[instances]
object FilePathDirectory extends Directory[AbsoluteFile[Any], FileName[Any]] {
  import eyrie.nio.syntax.javaMirror._

  override
  def listFiles[F[_]](a: AbsoluteFile[Any])(implicit F: Sync[F]): Stream[F, FileName[Any]] =
    Stream
      .bracket(
        F.delay(Files.newDirectoryStream(a.asJava))
      )(ds =>
        F.delay(ds.close())
      )
      .flatMap(ds =>
        Stream.fromIterator(ds.iterator().asScala)
      )
      .map(p => Internal.FileName[Any](p.getFileName))
}
