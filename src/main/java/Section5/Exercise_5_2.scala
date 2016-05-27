package Section5

/**
  * Created by francescousai on 26/05/16.
  */
class Exercise_5_2 {
  val epsilon = 0.001

  // write a tail recursive version filling the ??
  def sum( f: Int => Int ) ( a: Int, b: Int ): Int = {
    def iter( a: Int, result: Int): Int = {
      if ( a > b) result
      else iter( a+1, result + f( a ))
    }
    iter( a, 0 )
  }

  //Write a function product that computes the product of the values of functions at points over a given range
  // write a tail recursive version filling the ??
  def product( f: Int => Int ) ( a: Int, b: Int ): Long = {
    def iter( a: Int, result: Int): Int = {
      if ( a > b) result
      else iter( a+1, result * f( a ))
    }
    if( a > b ) 0 else iter( a, 1 )
  }

  def factorial ( n : Int ) : Long = {
    n match {
      case 0 | 1 => 1
      case x if x < 0 => throw new IllegalArgumentException( "Cannot compute factorial of a negative number" )
      case x => product( identity ) ( 1, x )
    }
  }

  def rangeOperation ( transform : Int => Int, operation : ( Int, Int ) => Int, default : Int ) : (( Int, Int ) => Int )= {
    def intRangeOperation(a: Int, b: Int ): Int = {
      def iter(a: Int, result: Int): Int = {
        if (a > b) result
        else iter( a + 1, operation( result, transform( a )))
      }
      if (a > b) 0 else iter( a, default )
    }
    intRangeOperation
  }

  def averageDamping ( f : Double => Double ) (x : Double) = ( x + f(x) ) / 2

  def fixedPoint ( f : Double => Double ) ( guess : Double ) : Double = {
    def isGoodEnough( x : Double, guess : Double ) : Boolean = {
//      println( "current guess, residual : " + guess + ", " + (Math.abs(guess - x)) / x )
      (Math.abs(guess - x)) / x < epsilon
    }
    def iterate( guess : Double ) : Double = {
      val next = f( guess )
      if( isGoodEnough( guess, next )) next
      else iterate( next )
    }
    iterate ( guess )
  }

  def squareRoot( x : Double ) : Double = {
    if (x < 0) throw new IllegalArgumentException("Cannot compute squared root of a negative number")
    else fixedPoint(averageDamping( y => x / y )) (x/2.0)
  }

  def cubeRoot( x : Double ) : Double = fixedPoint ( averageDamping( y => (1.0/3.0) * ( 2.0 * y + x / ( y*y )))) ( x/3.0 )

}

