package org.alexeyn.plugin

import simulacrum._

@typeclass trait Formatter[A] {
  def fmt(a: A): String
}
