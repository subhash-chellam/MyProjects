package features

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps

class UserActiveSessions extends Simulation {

	val createLoad = scenario("Get all the active sessions based on userId").exec(karateFeature(name = "classpath:features/UserActiveSessions.feature"))
	setUp(
    createLoad.inject(
      rampUsersPerSec(1).to(1000).during(10.seconds).randomized)
  ).assertions(
      global.responseTime.max.lt(4000),
      global.successfulRequests.percent.gt(99.9),
      forAll.responseTime.max.lt(2000),
      forAll.responseTime.percentile3.lte(1000)
  )

}

