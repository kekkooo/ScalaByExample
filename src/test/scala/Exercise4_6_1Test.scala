/**
  * Created by francescousai on 25/05/16.
  */
import org.scalatest._
import Section4.{Exercise_4_6_1}
class Exercise4_6_1Test extends FlatSpec with Matchers{
  val e = new Exercise_4_6_1
  "Factorial of 5 " should " be 120" in {
    e.factorial_tr( 5 ) should be (120)
  }
  it should "evaluate to 1 if n is 0 or 1" in{
    e.factorial_tr( 0 ) should be (1)
    e.factorial_tr( 1 ) should be (1)
  }

  it should "throw IllegalArgumentException if a negative number is provided" in{
    a [IllegalArgumentException] should be thrownBy{
      e.factorial_tr( -10 )
    }
  }


}
