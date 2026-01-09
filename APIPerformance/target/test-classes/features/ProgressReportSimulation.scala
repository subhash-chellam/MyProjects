package features

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps

class ProgressReportSimulation extends Simulation {

	val createLoad = scenario("Get the progress report for the given user").exec(karateFeature(name = "classpath:features/ProgressReport.feature"))
	setUp(
    createLoad.inject(rampUsers(3) during (0 seconds))
  ).assertions(
      global.responseTime.max.lt(2000),
      global.successfulRequests.percent.gt(99.9),
      forAll.responseTime.percentile3.lte(1000)
  )

}

