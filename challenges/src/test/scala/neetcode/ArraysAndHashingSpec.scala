package neetcode

import basetypes.UnitTest
import ArraysAndHashing._
class ArraysAndHashingSpec extends UnitTest("ArrayAndHashing") {

  "contains duplicate" must "return true for Array[1,2,3,1]" in { containsDuplicate(Array(1, 2, 3, 1)) must be(true) }
  it must "return false for Array[1,2,3,4]" in { containsDuplicate(Array(1, 2, 3, 4)) must be(false) }
  it must "return false for empty array" in { containsDuplicate(Array.emptyIntArray) }

}
