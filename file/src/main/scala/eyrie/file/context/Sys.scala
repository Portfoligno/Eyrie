package eyrie.file.context

import java.nio.file.{FileSystem, FileSystems}

import eyrie.file.ops.AsJava

sealed trait Sys extends AsJava[FileSystem]

object Sys {
  implicit val eyrieFileLocalInstance: Sys =
    new Sys {
      override
      def asJava: FileSystem = FileSystems.getDefault
    }
}
