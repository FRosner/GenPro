package de.genpro

trait Distribution[T] {
  
  def f(value: T): Double
  
  def F(value: T): Double

  def sample: T

  def sample(repetitions: Int): Iterable[T]

}
