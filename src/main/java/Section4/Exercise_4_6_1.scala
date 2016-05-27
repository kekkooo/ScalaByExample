package Section4

/**
  * Created by francescousai on 25/05/16.
  */
class Exercise_4_6_1 {

  def factorial_tr( n : Int ) : Long = {

    def intFactorial( v: Int, acc: Int ) : Long = {
      v match {
        case 0 | 1 => acc
        case x if x < 0 => throw new IllegalArgumentException( "Cannot compute factorial of a negative number" )
        case x => intFactorial( x - 1, acc * x )
      }
    }
    intFactorial( n, 1 )
  }

}
