package neetcode

import basetypes.UnitTestFlatSpec
import ArraysAndHashing._
import org.scalatest.propspec.AnyPropSpec

class ArraysAndHashingFlatSpec extends UnitTestFlatSpec("ArrayAndHashing") {

  "contains duplicate" must "return true for Array[1,2,3,1]" in { containsDuplicate(Array(1, 2, 3, 1)) must be(true) }
  it must "return false for Array[1,2,3,4]" in { containsDuplicate(Array(1, 2, 3, 4)) must be(false) }
  it must "return false for empty array" in { containsDuplicate(Array.emptyIntArray) }

  "isAnagram" must "return true for 'anagram' && 'nagaram'" in { isAnagram("anagram", "nagaram") must be(true) }
  it must "return false for 'bat' && 'cat'" in { isAnagram("cat", "bat") must be(false) }
  it must "return true when strings are of different length. Ex baat and bat" in { isAnagram("baat", "bat") must be(false) }
  it must "run for the required upper limit of strings that are equal" in {
    val r = scala.util.Random
    val s = (0 until (5 * math.pow(10, 4).toInt)).toList.map(_ => (r.between(0, 26) + 64).toChar).mkString
    val t = s
    isAnagram(s, t) must be(true)
  }
  it must "run for the required upper limit of strings that are unequal" in {
    val r = scala.util.Random
    val s = (0 until (5 * math.pow(10, 4).toInt)).toList.map(_ => (r.between(0, 26) + 64).toChar).mkString
    val t = (0 until (5 * math.pow(10, 4).toInt)).toList.map(_ => (r.between(0, 26) + 64).toChar).mkString
    isAnagram(s, t)
  }

  "minimum operations to make all zero" must "return 0 for Array(0)" in { minimumOperations(Array(0)) must be(0) }
  it must "return 3 for Array(1,5,0,3,5)" in { minimumOperations(Array(1, 5, 0, 3, 5)) must be(3) }
  it must "return 5 for Array(1,2,3,4,5)" in { minimumOperations(Array(1, 2, 3, 4, 5)) must be(5) }
  it must "return 1 for Array(10,10,10)" in { minimumOperations(Array(10, 10, 10)) must be(1) }
}
