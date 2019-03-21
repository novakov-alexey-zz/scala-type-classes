package org.alexeyn

trait Num[A] {
  def +(l: A, r: A): A

  def *(l: A, r: A): A
}

object numInstances {

  implicit val intNum = new Num[Int] {
    override def +(l: Int, r: Int): Int = l + r

    override def *(l: Int, r: Int): Int = l * r
  }

  implicit val floatNum = new Num[Float] {
    override def +(l: Float, r: Float): Float = l + r

    override def *(l: Float, r: Float): Float = l * r
  }
}

object Num {
  def +[A: Num](l: A, r: A): A = implicitly[Num[A]].+(l, r)

  def *[A: Num](l: A, r: A): A = implicitly[Num[A]].*(l, r)
}

object Main extends App {

  import numInstances._

  val r1 = Num + (1, 2)
  val r2 = Num * (1, 2)

  println(r1)
  println(r2)
}
