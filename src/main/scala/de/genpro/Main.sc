import de.genpro.{Variable, UniformDistribution}
import de.genpro.Variable._

val x = Variable("x") ~ UniformDistribution(0d, 1d)

f(x := 5)
