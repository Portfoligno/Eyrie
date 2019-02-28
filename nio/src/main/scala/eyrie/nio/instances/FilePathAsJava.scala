package eyrie.nio.instances

import java.nio.file.Path

import eyrie.nio.ops.JavaMirror

private
object FilePathAsJava extends JavaMirror[Any] {
  override
  def asJavaOps(a: Any): JavaMirror.Ops[Out] =
    a.asInstanceOf[JavaMirror.Ops[Out]]
}

private[instances]
trait AsJavaInstances[A[_]] {
  implicit def eyrieNioAsJavaInstance[C]: JavaMirror.Aux[A[C], Path] =
    FilePathAsJava.asInstanceOf[JavaMirror.Aux[A[C], Path]]
}
