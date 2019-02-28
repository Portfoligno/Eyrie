package eyrie.nio.ops

trait JavaMirror[A] {
  type Out

  def asJavaOps(a: A): JavaMirror.Ops[Out]
}

object JavaMirror {
  trait Ops[B] extends Any {
    def asJava: B
  }

  type Aux[A, B] = JavaMirror[A] {
    type Out = B
  }

  @inline
  def apply[A](A: JavaMirror[A]): JavaMirror[A] = A
}
