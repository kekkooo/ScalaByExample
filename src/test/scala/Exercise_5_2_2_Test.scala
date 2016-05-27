import Section5.Exercise_5_2
import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by francescousai on 26/05/16.
  */
class Exercise_5_2_2_Test extends FlatSpec with Matchers {

  val e = new Exercise_5_2()

  it should "give correct results :D " in {
    e.product( identity )   ( 1, 5 ) should be (120)
    e.product( identity )   ( 5, 1 ) should be (0)
    e.product( identity )   ( 5, 5 ) should be (5)
    e.product( x => x + 1 ) ( 1, 5 ) should be (720)
    e.product( x => x * x)  ( 1, 5 ) should be (14400)
  }
  it should "make factorial work " in {
    e.factorial( 5 ) should be (120)
    e.factorial( 0 ) should be (1)
    e.factorial( 1 ) should be (1)
  }

}
