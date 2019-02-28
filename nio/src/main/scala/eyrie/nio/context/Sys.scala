package eyrie.nio.context

import java.nio.file.{FileSystem, FileSystems}

import eyrie.nio.ops.JavaMirror

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
}
