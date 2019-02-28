package eyrie.nio.syntax

import eyrie.nio.ops.JavaMirror

trait JavaMirrorSyntax {
  implicit def toJavaMirrorOps[A](a: A)(implicit A: JavaMirror[A]): JavaMirror.Ops[A.Out] =
    A.asJavaOps(a)
}
