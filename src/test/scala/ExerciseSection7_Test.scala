import Section7.IntTreeHelper
import org.scalatest._

/**
  * Created by francescousai on 27/05/16.
  */
class ExerciseSection7 extends FlatSpec with Matchers {
  var emptyList = Nil
  var someNumbers = List(2, 4, 5, 10, 12, 19)
  var someNumbersWithout4 = List(2, 5, 10, 12, 19)

  it should "be able to insert elements" in {
     IntTreeHelper.sum( IntTreeHelper.fromList( someNumbers )) should be (52)
  }
}
