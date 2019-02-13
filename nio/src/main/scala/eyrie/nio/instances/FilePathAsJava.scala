package eyrie.nio.instances

import java.nio.file.Path

import eyrie.nio.ops.AsJava

private
object FilePathAsJava extends AsJava[Any] {
  override
  def asJavaOps(a: Any): AsJava.Ops[Out] =
    a.asInstanceOf[AsJava.Ops[Out]]
}

private[instances]
trait AsJavaInstances[A[_]] {
  implicit def eyrieNioAsJavaInstance[C]: AsJava.Aux[A[C], Path] =
    FilePathAsJava.asInstanceOf[AsJava.Aux[A[C], Path]]
}
