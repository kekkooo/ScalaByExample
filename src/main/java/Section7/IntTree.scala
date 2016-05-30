package Section7

import scala.annotation.tailrec
import scala.util.continuations._

/**
  * Created by francescousai on 27/05/16.
  */
abstract class IntTree
abstract class Result

case object Done extends Result
case class Next( n:Int, f : ( Unit => Result ) ) extends Result

case object EmptyTree extends IntTree
case class Node( value : Int, left : IntTree, right : IntTree) extends IntTree

  object IntTreeHelper {
    // these are not tail recursive :(
    def contains(tree: IntTree, value: Int): Boolean = tree match {
      case EmptyTree => false
      case Node(v, left, right) => {
        if (v == value) true
        else contains(left, value) || contains(right, value)
      }
    }

    def insert(tree: IntTree, value: Int): IntTree = tree match {
      case EmptyTree => new Node(value, EmptyTree, EmptyTree)
      case Node(v, left, right) =>
        if (v == value) tree
        else if (value < v) new Node(v, insert(left, value), right)
        else new Node(v, left, insert(right, value))
    }

    def fromList(values: List[Int]): IntTree = {
      values.foldLeft(EmptyTree: IntTree)((tree, v) => IntTreeHelper.insert(tree, v))
    }

    def Sum( tree : IntTree ): Int = {
      def innerSum(t: IntTree, sum: Int): Int = {
        t match {
          case EmptyTree => sum
          case Node(v, left, right) =>
            if ( v==2) throw  new StackOverflowError("bla")
            else
            reset {
              innerSum(right, sum +
                shift { k: (Int => Int) =>
                  k(innerSum(left, v))
                })
            }
        }
      }
      innerSum(tree, 0)
    }

  }
