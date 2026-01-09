package features

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps

class UserSimulation extends Simulation {

	val createLoad = scenario("Get all the list of courses based on userId	 taken").exec(karateFeature(name = "classpath:features/UsersBackup.feature"))
	setUp(
    createLoad.inject(rampUsers(5) during (0 seconds))
  ).assertions(
      global.responseTime.max.lt(2000),
      global.successfulRequests.percent.gt(99.9),
      forAll.responseTime.percentile3.lte(1000)
  )

}

