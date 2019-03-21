package org.alexeyn.plugin

object instances {
  implicit val float: Formatter[Float] =
    (a: Float) => s"$a f"

  implicit val boolean: Formatter[Boolean] =
    (a: Boolean) => a.toString.toUpperCase

  implicit def list[A: Formatter]: Formatter[List[A]] =
    (a: List[A]) => a.map(e => Formatter[A].fmt(e)).mkString(" :: ")
}
