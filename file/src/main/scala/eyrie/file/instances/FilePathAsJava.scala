package eyrie.file.instances

import java.nio.file.Path

import eyrie.file.ops.AsJava

private
object FilePathAsJava extends AsJava[Any] {
  override
  def asJavaOps(a: Any): AsJava.Ops[Out] =
    a.asInstanceOf[AsJava.Ops[Out]]
}

private[instances]
trait AsJavaInstances[A[_]] {
  implicit def eyrieFileAsJavaInstance[C]: AsJava.Aux[A[C], Path] =
    FilePathAsJava.asInstanceOf[AsJava.Aux[A[C], Path]]
}
