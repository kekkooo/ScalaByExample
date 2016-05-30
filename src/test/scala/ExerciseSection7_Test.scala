import Section7.IntTreeHelper
import org.scalatest._

/**
  * Created by francescousai on 27/05/16.
  */
class ExerciseSection7 extends FlatSpec with Matchers {
  var emptyList = Nil
  var someNumbers = List( 10, 12, 19, 2, 4, 5 )
  val otherNumbers = List( 12, 14, 0, 15, 2, 9 )


  it should "be able to insert elements" in {
     IntTreeHelper.Sum( IntTreeHelper.fromList( someNumbers )) should be (52)
  }
}
