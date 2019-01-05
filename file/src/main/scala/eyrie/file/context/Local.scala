package eyrie.file.context

import java.nio.file.{FileSystem, FileSystems}

import eyrie.file.ops.AsJava

sealed trait Local extends AsJava[FileSystem]

object Local {
  implicit val localInstance: Local =
    new Local {
      override
      def asJava: FileSystem = FileSystems.getDefault
    }
}
