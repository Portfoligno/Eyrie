package eyrie.file.ops

trait AsJava[A] {
  type Out

  def asJavaOps(a: A): AsJava.Ops[Out]
}

object AsJava {
  trait Ops[B] extends Any {
    def asJava: B
  }

  type Aux[A, B] = AsJava[A] {
    type Out = B
  }

  @inline
  def apply[A](A: AsJava[A]): AsJava[A] = A
}
