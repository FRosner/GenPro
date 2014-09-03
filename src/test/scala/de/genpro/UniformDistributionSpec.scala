package de.genpro

import org.scalatest.{FlatSpec, Matchers}

class UniformDistributionSpec extends FlatSpec with Matchers {
  
  val distribution = UniformDistribution(0d, 1d)
  val samples = distribution.sample(10000)

  "A uniform distribution with l=0 and u=1" should "have mean 0.5" in {
    val mean: Double = samples.sum / samples.size
    mean should be > 0.45
    mean should be < 0.55
  }
  
  it should "not sample values smaller than 0" in {
    all (samples) should be >= 0d
  }

  it should "not sample values greater than 1" in {
    all (samples) should be <= 1d
  }
  
  it should "have pdf(x) = 1 in (0 < x < 1)" in {
    0.01.to(0.99, 0.01).foreach((value) => {
      distribution.f(value) should be (1d)
    })
  }

  it should "have pdf(x) = 1 for x = 0" in {
    distribution.f(0) should be (1d)
  }

  it should "have pdf(x) = 1 for x = 1" in {
    distribution.f(1) should be (1d)
  }

  it should "have pdf(x) = 0 in (x < 0)" in {
    (-10.0).to(-0.5, 0.5).foreach((value) => {
      distribution.f(value) should be (0d)
    })
  }

  it should "have pdf(x) = 0 in (x > 1)" in {
    (1.5).to(10.0, 0.5).foreach((value) => {
      distribution.f(value) should be (0d)
    })
  }
  
}
