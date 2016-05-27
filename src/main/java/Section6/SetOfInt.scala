package Section6

/**
  * Created by francescousai on 26/05/16.
  */
abstract class SetOfInt {
  def insert( x: Int ) : SetOfInt
  def contains( x : Int ) : Boolean
  def union ( other : SetOfInt ) : SetOfInt
  def intersection ( other : SetOfInt ) : SetOfInt
  def toList : List[Int]
  def isEmpty : Boolean
  def exclude( x : Int ) : SetOfInt
}

object SetOfIntUtil{
  def fromList( ls : List[Int] ) : SetOfInt ={
    def intBuild( list : List[Int], set : SetOfInt ): SetOfInt ={
      list match {
        case Nil => set
        case x :: xs => intBuild( xs, set.insert( x ))
      }
    }
    intBuild( ls, new EmptySet )
  }

  def empty = new EmptySet()
}

class EmptySet extends SetOfInt{
  override def insert( x: Int ) = new NonEmptySet(x, SetOfIntUtil.empty, SetOfIntUtil.empty )
  override def contains( x: Int ) : Boolean = false
  override def union ( other : SetOfInt ) = other
  override def intersection ( other : SetOfInt ) = this
  override def toList : List[Int] = Nil
  override def isEmpty : Boolean = false
  override def exclude( x : Int ) : SetOfInt = this
}


class NonEmptySet( val root : Int, val left : SetOfInt, val right : SetOfInt ) extends SetOfInt{

  override def isEmpty : Boolean = true
  def isLeaf : Boolean = (left.isEmpty && right.isEmpty)

  override def insert( x : Int ) = {
    if( x == root ) this    // avoid duplication
    else if ( x < root ) new NonEmptySet( root, left insert x, right )
    else new NonEmptySet( root, left, right insert x )
  }

  override def contains( x : Int ) = {
    if ( x < root ) left contains x
    else if( x > root ) right contains x
    else true
  }

  def toList : List[Int] = {
    // pre-order visit :  value - left - right
    def intToList( stack : List[SetOfInt], result : List[SetOfInt] ) : List[SetOfInt] = {
      stack match {
        case Nil => result
        case x :: xs =>{
          x match {
            case s : EmptySet => intToList ( xs, result)
            case s : NonEmptySet =>  intToList( s.left::(s.right::xs), s::result )
          }
        }
      }
    }
    def extractValue( node : SetOfInt ) : Int = {
      node match {
        case x : EmptySet => throw new IllegalArgumentException( "Cannot extract value from an empty set" )
        case x : NonEmptySet => x.root
      }
    }
    val treeList = intToList( List(this), Nil )
    treeList.map( extractValue )
  }

  override def union ( other : SetOfInt ) : SetOfInt = {
    other.toList.foldLeft ( this : SetOfInt ) (( set, x ) =>  set insert x )
  }

  // I think these implementations are inefficent but they should work
  override def intersection ( other : SetOfInt ) : SetOfInt = {
    def intIntersection( items : List[Int], result : SetOfInt, otherSet : SetOfInt ) : SetOfInt = {
      items match {
        case Nil => result
        case x :: xs => intIntersection( xs, ( if( otherSet contains x ) (result insert x) else result  ), otherSet  )
      }
    }
    intIntersection( other.toList, ( intIntersection( this.toList, SetOfIntUtil.empty, other )), this )
  }

  override def exclude( x : Int ) : SetOfInt = {
    def foldOperation : ( SetOfInt, Int ) => SetOfInt = (( set, value ) => if( value == x ) set else  set insert value )
    this.toList.foldLeft ( SetOfIntUtil.empty : SetOfInt ) (foldOperation)

  }

}