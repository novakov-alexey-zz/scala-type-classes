package org.alexeyn

trait Add[A, B, C] {
  def +(a: A, b: B): C
}

object Add {
  def +[A, B, C](a: A, b: B)(implicit ev: Add[A, B, C]): C =
    ev.+(a, b)
}

object AddMain extends App {
  //  implicit val intAdd1: Add[Int, Int, Double] =
  //    (a: Int, b: Int) => a + b
  implicit val intAdd2: Add[Int, Int, Int] =
  (a: Int, b: Int) => a + b

  private val value = Add.+(1, 2)
  println(value)

}
