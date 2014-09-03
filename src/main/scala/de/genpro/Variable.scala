package de.genpro

abstract class Variable[T](val name: String) {

  def f(value: T): Double

  def F(value: T)(implicit ordering: Ordering[T]): Double

  def value: T
  
  def :=(compared: T): () => Double = {
    () => f(compared)
  }
  
}

class RandomVariable[T](name: String, val distribution: Distribution[T]) extends Variable[T](name) {
  
  override def f(value: T): Double = distribution f value

  override def F(value: T)(implicit ordering: Ordering[T]): Double = distribution F value

  override def value: T = distribution.sample
  
}

class FixedVariable[T](name: String, val fixedValue: T) extends Variable[T](name) {

  override def f(value: T): Double = if (this.fixedValue == value) 1 else 0

  override def F(value: T)(implicit ordering: Ordering[T]): Double = {
    if (ordering.compare(this.fixedValue, value) > 0) 1 else 0
  }

  override def value: T = fixedValue

}

object Variable {
  
  private val DEFAULT_NAME: String = "ContinuousVariable"
  private var VARIABLE_COUNTER: Int = 0

  class RandomVariableBuilder(val name: String) {

    def ~[T](distribution: Distribution[T]): Variable[T] = new RandomVariable[T](name, distribution)

  }

  private def anonymousVariableName = {
    VARIABLE_COUNTER += 1
    DEFAULT_NAME + VARIABLE_COUNTER
  }

  def apply(name: String) = new RandomVariableBuilder(name)

  def apply[T](name: String, value: T) = new FixedVariable[T](name, value)

  def apply[T](value: T) = new FixedVariable[T](anonymousVariableName, value)

  def apply() = new RandomVariableBuilder(anonymousVariableName)
  
  def f[T](statement: () => Double): Double = {
    statement()
  }
  
  implicit def toVariable[T](fixedValue: T) = new FixedVariable[T](anonymousVariableName, fixedValue)

}
