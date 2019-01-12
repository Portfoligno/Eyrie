package eyrie.file.context

import java.nio.file.{FileSystem, FileSystems}

import eyrie.file.ops.AsJava

sealed trait Sys

object Sys {
  implicit val eyrieFileSysInstance: Sys =
    new Sys { }

  implicit val eyrieFileAsJavaInstance: AsJava.Aux[Sys, FileSystem] =
    new AsJava[Sys] with AsJava.Ops[FileSystem] {
      override
      type Out = FileSystem

      override
      def asJavaOps(a: Sys): AsJava.Ops[FileSystem] =
        this

      override
      def asJava: FileSystem =
        FileSystems.getDefault
    }
}
