import java.util.Collections

import org.apache.commons.math3.distribution.MultivariateNormalDistribution

import scala.util.Random

val dataset = Array(Array(0, 0), Array(0, 1), Array(1, 0), Array(1, 1), Array(10, 10), Array(11, 10), Array(10, 11), Array(11, 11))
val numMixtureComponents = 2
val numberOfDimensions = 2

type Dimension = Array[Double]

def p(observation: Dimension, mu: Dimension, sigma: Array[Dimension]): Double = {
  new MultivariateNormalDistribution(mu, sigma).density(observation)
}

def logP(observation: Dimension, mu: Dimension, sigma: Array[Dimension]): Double = {
  Math.log(p(observation, mu, sigma))
}

def membershipWeight(component: Int, observation: Dimension, mus: Array[Dimension],
                     sigmas: Array[Array[Dimension]], mixtureWeights: Array[Double]): Double = {
  p(observation, mus(component), sigmas(component)) * mixtureWeights(component)
}

def initializeEm(observations: Array[Dimension], numberOfDimensions: Int): (Array[Dimension], Array[Array[Dimension]]) = {
  
}

logP(Array(0d,0d), Array(0d,0d), Array(Array(1d,0d), Array(0d,1d)))
logP(Array(10d,10d), Array(0d,0d), Array(Array(1d,0d), Array(0d,1d)))

