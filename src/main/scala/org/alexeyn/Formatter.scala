package org.alexeyn

trait Formatter[A] {
  def fmt(a: A): String
}

object instances {
  implicit val float: Formatter[Float] =
    (a: Float) => s"$a f"

  implicit val boolean: Formatter[Boolean] =
    (a: Boolean) => a.toString.toUpperCase

  implicit def list[A](implicit ev: Formatter[A]): Formatter[List[A]] =
    (a: List[A]) => a.map(e => ev.fmt(e)).mkString(" :: ")

  implicit val e: Formatter[W[Formatter]] = (w0: W[Formatter]) => w0.fa.fmt(w0.a)

}

object syntax {

  implicit class FromatterOps[A: Formatter](a: A) {
    def fmt: String = Formatter[A].fmt(a)
  }

}

object Formatter {
  //def fmt[A](a: A)(implicit ev: Formatter[A]): String = ev.fmt(a)

  def fmt[A: Formatter](a: A): String = Formatter[A].fmt(a)

  def apply[A](implicit formatter: Formatter[A]): Formatter[A] = formatter
}

object subtyping {

  trait Formatter {
    def fmt: String = {
      this match {
        case v: FloatVal => s"${v.v} f"
        case v: BooleanVal => v.v.toString.toUpperCase
      }
    }
  }

  case class FloatVal(v: Float) extends Formatter {
    //override def fmt: String = s"$v f"
  }

  case class BooleanVal(v: Boolean) extends Formatter {
    //override def fmt: String = v.toString.toUpperCase
  }

}

trait W[F[_]] {
  type A
  val a: A
  val fa: F[A]
}

object W {
  def apply[F[_], A0](a0: A0)(implicit ev: F[A0]): W[F] =
    new W[F] {
      type A = A0
      val a = a0
      val fa = ev
    }
}

object FormatterApp extends App {

  import instances._
  import syntax._

  val floats = List(4.5f, 1f)
  val booleans = List(true, false)
  val integers = List(1, 2, 3)

  /*import subtyping._

  val vals: Seq[subtyping.Formatter] = List(FloatVal(1), BooleanVal(true), BooleanVal(false))
  println(vals.map(_.fmt).mkString(" "))*/

  val hlist = List(W(1f), W(true), W(2f))
  println(hlist.fmt)

  //println(Formatter.fmt[List[Float]](floats))
  //  println(Formatter.fmt(booleans))
  //  println(Formatter.fmt(integers))

  import syntax._

//  println(floats.fmt)
//  println(booleans.fmt)
}