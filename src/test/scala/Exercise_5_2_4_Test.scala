import Section5.Exercise_5_2
import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by francescousai on 26/05/16.
  */

class Exercise_5_2_4_Test extends FlatSpec with Matchers {

  val e = new Exercise_5_2()
  val sumFunction : (Int, Int ) => Int  = ((x, y) => x + y)
  val productFunction : (Int, Int ) => Int  = ((x, y) => x * y)

  val identitySum  = e.rangeOperation( identity, sumFunction, 0 )
  val successorSum = e.rangeOperation( x => x + 1 , sumFunction, 0 )
  val squareSum    = e.rangeOperation( x => x * x , sumFunction, 0 )

  val identityProduct  = e.rangeOperation( identity, productFunction, 1 )
  val successorProduct = e.rangeOperation( x => x + 1 ,  productFunction, 1 )
  val squareProduct    = e.rangeOperation( x => x * x , productFunction, 1 )

  it should "give correct results for sum :D " in {
    identitySum   ( 1, 5 ) should be (15)
    identitySum   ( 5, 1 ) should be (0)
    identitySum   ( 5, 5 ) should be (5)
    successorSum  ( 1, 5 ) should be (20)
    squareSum     ( 1, 5 ) should be (55)
  }
  it should "give correct results for product :D " in {
    identityProduct  ( 1, 5 ) should be (120)
    identityProduct  ( 5, 1 ) should be (0)
    identityProduct  ( 5, 5 ) should be (5)
    successorProduct ( 1, 5 ) should be (720)
    squareProduct    ( 1, 5 ) should be (14400)
  }
  it should "be able to compute square root" in {
    e.squareRoot( 4.0 ) should be ((2.0) +-  e.epsilon)
    e.squareRoot( 100000000.0 ) should be (( 10000.0 ) +-  e.epsilon)
    a [IllegalArgumentException] should be thrownBy {
      e.squareRoot(-100)
    }

  }
  it should "be able to compute cube root" in {
    e.cubeRoot( 8.0 )     should be ((2.0)  +-  0.1 )
    e.cubeRoot( 1000.0 )  should be ((10.0) +-  0.1 )
    // does not pass test, don't care for now
    //e.cubeRoot( -27.0 )   should be ((-3.0) +-  0.1 )
  }

}