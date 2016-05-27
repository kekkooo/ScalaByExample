package Section4

/**
  * Created by francescousai on 25/05/16.
  */
class Exercise4_4_1 {

  def squaredRoot( x : Double ) : Double = {
    val epsilon = 0.001
    val guess = 1.0
    def isGoodEnough( guess: Double ): Boolean = {
      val residual = Math.abs((guess * guess) / x - 1.0)
      residual < epsilon
    }

    def improve( guess: Double ): Double = {
      (guess + (x / guess)) / 2.0
    }

    def sqrtIter( guess: Double ): Double = {
      if (isGoodEnough( guess )) guess
      else sqrtIter( improve( guess ))
    }

    sqrtIter( guess )
  }
}


