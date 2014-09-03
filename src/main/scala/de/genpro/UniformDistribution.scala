package de.genpro

import scala.util.Random

class UniformDistribution(val lowerBound: Variable[Double], val upperBound: Variable[Double]) extends Distribution[Double] {

  private val concreteLowerBound = lowerBound.value
  private val concreteUpperBound = upperBound.value
  private val random = Random

  private def sampleOneValue(concreteLowerBound: Double, concreteUpperBound: Double) = {
    random.nextDouble() * (concreteUpperBound - concreteLowerBound) + concreteLowerBound
  }

  override def sample: Double = sample(1).iterator.next

  override def sample(repetitions: Int): Iterable[Double] = {
    (0 to repetitions).map(_ => sampleOneValue(concreteLowerBound, concreteUpperBound))
  }

  override def f(value: Double): Double = 
    if (value >= concreteLowerBound && value <= concreteUpperBound)
      1d / (concreteUpperBound - concreteLowerBound)
    else
      0d

  override def F(value: Double): Double = (value - concreteLowerBound) / (concreteUpperBound - concreteLowerBound)
}

object UniformDistribution {
  
  def apply(lowerBound: Variable[Double], upperBound: Variable[Double]): UniformDistribution = new UniformDistribution(lowerBound, upperBound)
  
}
