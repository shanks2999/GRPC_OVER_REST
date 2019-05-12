import org.scalatest.FunSuite
import org.simulation.cloudsim.config.PullConfig

class TestConfig extends FunSuite {
  test("Config.test") {
    assert(new PullConfig().getSetting("test.a").toInt >= 0)
  }

}