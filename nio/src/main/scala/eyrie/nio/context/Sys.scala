package eyrie.nio.context

import java.nio.file.{FileSystem, FileSystems}

import eyrie.nio.ops.AsJava

sealed trait Sys

object Sys {
  implicit val eyrieNioSysInstance: Sys =
    new Sys { }

  implicit val eyrieNioAsJavaInstance: AsJava.Aux[Sys, FileSystem] =
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
