package Section7

import scala.util.continuations._

/**
  * Created by francescousai on 27/05/16.
  */
abstract class IntTree

case object EmptyTree extends IntTree
case class Node( value : Int, left : IntTree, right : IntTree) extends IntTree

  object IntTreeHelper {
    // these are not tail recursive :(
    def contains(tree: IntTree, value: Int): Boolean = tree match {
      case EmptyTree => false
      case Node( v, left, right ) =>{
          if ( v == value ) true
          else contains( left, value ) || contains (right, value )
      }
    }

    def insert( tree : IntTree, value : Int ) : IntTree = tree match {
      case EmptyTree => new Node( value, EmptyTree, EmptyTree )
      case Node( v, left, right ) =>
        if( v == value ) tree
        else if ( value < v ) new Node( v, insert(left, value), right  )
        else new Node(v, left, insert(right, value))
    }

    def fromList( values : List[Int] ): IntTree = {
      values.foldLeft( EmptyTree : IntTree ) (( tree, v ) => IntTreeHelper.insert( tree, v ))
    }


    def sum( tree: IntTree ) : Int = {
      def intSum(tree: IntTree, cont: Int => Int): Int = {
        tree match {
          case EmptyTree =>
            reset {
              println("yelding 0")
              shift { (k: Int => Int) => cont(0) } + 0
            }
          case Node(v, left, right) => {
            reset {
              println("yelding " + v)
              shift { (k: Int => Int) => k(
                intSum(left, leftSum => intSum(right, rightSum => cont(v + leftSum + rightSum))))
              }
            }
          }
        }
      }
      intSum(tree, identity)
    }
  }
