import Section5.Exercise_5_2
import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by francescousai on 26/05/16.
  */
class Exercise_5_2_1_Test extends FlatSpec with Matchers{
  val e = new Exercise_5_2()

  it should "evaluate to 15 provided with identity and a = 1 and b = 5 " in {
    e.sum( identity ) ( 1, 5 ) should be (15)
  }
  it should "evaluate to 0 if a > b " in {
    e.sum( identity ) ( 5, 1 ) should be (0)
  }
  it should "evaluate to a if a == b b " in {
    e.sum( identity ) ( 5, 5 ) should be (5)
  }
  it should "evaluate to 20 if a = 1 and b = 5 and function successor " in {
    e.sum( x => x + 1 ) ( 1, 5 ) should be (20)
  }
  it should "evaluate to  if a = 1 and b = 5 " in {
    e.sum( x => x * x ) ( 1, 5 ) should be (55)
  }
}
