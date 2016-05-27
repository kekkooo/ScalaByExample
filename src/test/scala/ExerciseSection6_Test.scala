import Section6.{SetOfIntUtil, EmptySet, SetOfInt}
import org.scalatest.{Matchers, FlatSpec}

/**
  * Created by francescousai on 26/05/16.
  */
class ExerciseSection6_Test extends FlatSpec with Matchers{

  val emptyList = Nil
  val someNumbers = List( 2, 4, 5, 10, 12, 19 )
  val someNumbersWithout4 = List( 2, 5, 10, 12, 19)
  val someNumbersWithout19 = List( 2, 4, 5, 10, 12)
  val someNumbersWithout4And19 = List( 2, 5, 10, 12)
  val otherNumbers = List( 12, 14, 15, 2, 9 )
  val unionNumbers = otherNumbers ++ ( someNumbers filter ( x => !( otherNumbers contains x )))
  val intersectionNumbers = unionNumbers filter ( x => ( otherNumbers contains x ) && ( someNumbers contains x ))

  val someSet = SetOfIntUtil.fromList( someNumbers )
  val otherSet = SetOfIntUtil.fromList( otherNumbers )

  it should "should correctly create sets" in {
    someSet.toList.sorted should be ( someNumbers.sorted )
    otherSet.toList.sorted should be ( otherNumbers.sorted )
  }
  it should "contain the right elements" in {
    someSet contains someNumbers(0) should be (true)
    someSet contains someNumbers(1) should be (true)
    someSet contains someNumbers(2) should be (true)
    someSet contains someNumbers(3) should be (true)
    someSet contains someNumbers(4) should be (true)
    someSet contains someNumbers(5) should be (true)

    otherSet contains otherNumbers(0) should be (true)
    otherSet contains otherNumbers(1) should be (true)
    otherSet contains otherNumbers(2) should be (true)
    otherSet contains otherNumbers(3) should be (true)
    otherSet contains otherNumbers(4) should be (true)
  }
  it should "convert to list in a tail recursive fashion" in {
    someSet.toList.sorted should be (someNumbers.sorted)
    otherSet.toList.sorted should be (otherNumbers.sorted)
  }
  it should " be able to perform unions and intersections " in {
    ( someSet union someSet ).toList.sorted should be ( someNumbers.sorted )
    ( someSet union SetOfIntUtil.empty ).toList.sorted should be ( someNumbers.sorted )
    ( someSet intersection someSet ).toList.sorted should be ( someNumbers.sorted )
    ( someSet intersection SetOfIntUtil.empty ).toList.sorted should be ( Nil )
    ( someSet union otherSet ).toList.sorted should be ( unionNumbers.sorted )
    ( someSet intersection otherSet ).toList.sorted should be ( intersectionNumbers.sorted )
  }
  it should "be able to remove elements correctly" in {
    (someSet exclude 4).toList.sorted should be( someNumbersWithout4.sorted)
    (someSet exclude 100000).toList.sorted should be(someNumbers.sorted)
    (someSet exclude 19).toList.sorted should be (someNumbersWithout19.sorted)
    ((someSet exclude 19) exclude 4).toList.sorted should be( someNumbersWithout4And19.sorted)
    (SetOfIntUtil.empty exclude 4).toList should be(List.empty)
  }


}
