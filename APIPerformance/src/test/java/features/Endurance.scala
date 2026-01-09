package features

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps

class EnduranceTest extends Simulation {
  def hello(): Unit = {
	val createLoad = scenario("Get the progress report update for the given user orchestrator").exec(karateFeature(name = "classpath:features/ProgressReportUpdate.feature"))
	setUp(
    createLoad.inject(rampUsers(3) during (0 seconds))
  ).assertions(
      global.responseTime.max.lt(2000),
      global.successfulRequests.percent.gt(99.9),
      forAll.responseTime.percentile3.lte(1000)
  )
  }
}

object EnduranceTest {
  def main(args: Array[String]): Unit = {
    val durationInMillis = 60000 // 1 minute
    val startTime = System.currentTimeMillis()
    var iterations = 0
    // Run the test until the specified duration
    while (System.currentTimeMillis() - startTime < durationInMillis) {
	    val myObject = new EnduranceTest()
	    myObject.hello()
	    println("Performing task...")
	    iterations += 1
    }

    // Calculate the duration of the test
    val durationInSeconds = (System.currentTimeMillis() - startTime) / 1000.0

    // Output the results
    println(s"Test completed in $durationInSeconds seconds.")
    println(s"Iterations: $iterations")
    println(s"Iterations per second: ${iterations / durationInSeconds}")
  }
}