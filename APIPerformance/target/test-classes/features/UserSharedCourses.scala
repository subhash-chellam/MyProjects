package features

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps

class UserSharedCourses extends Simulation {

	 val protocol = karateProtocol(
	 "/users/{UserId}/tokens" -> pauseFor("post" -> 300000),
    "/users/{UserId}/courses/shared" -> pauseFor("get" -> 300000)
  )

	val createLoad = scenario("Get all the list of shared courses based on userId").exec(karateFeature(name = "classpath:features/UserSharedCourses.feature"))
	setUp(
    createLoad.inject(rampUsers(2) during (14400 seconds)).protocols(protocol)
  ).assertions(
      global.responseTime.max.lt(2000),
      global.successfulRequests.percent.gt(99.9),
      forAll.responseTime.percentile3.lte(1000)
  )

}

