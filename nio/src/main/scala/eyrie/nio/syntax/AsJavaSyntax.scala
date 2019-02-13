package eyrie.nio.syntax

import eyrie.nio.ops.AsJava

trait AsJavaSyntax {
  implicit def toAsJavaOps[A](a: A)(implicit A: AsJava[A]): AsJava.Ops[A.Out] =
    A.asJavaOps(a)
}
