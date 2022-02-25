package telegram

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import waterjug._

class WaterJugSpec extends AnyFreeSpec with Matchers {

  "when full" - {
    "isFull() should be true" in {
      val j = Jug(10, 10)
      j.isFull() should be(true)
    }
    "isEmpty() should be false" in {
      val j = Jug(10, 10)
      j.isEmpty() should be(false)
    }
  }
  "when empty" - {
    "isEmpty() should be true" in {
      val j = Jug(10, 0)
      j.isEmpty() should be(true)
    }
    "isFull() should be false" in {
      val j = Jug(10, 0)
      j.isFull() should be(false)
    }
  }
}
class WaterJugSolvableSpec extends AnyFreeSpec with Matchers {

  private val solvable = new Solvable {}
  "solvable" - {
    "(4,2) with target as 2" in {
      solvable.condition(Jug(4, 0), Jug(2, 0), 2).isRight
    }
    "(2,4) with target as 2" in {
      solvable.condition(Jug(2, 0), Jug(4, 0), 2).isRight

    }
    "(2,2) with target as 2" in {
      solvable.condition(Jug(2, 0), Jug(2, 0), 2).isRight

    }
  }
  "not solvable" - {
    "(6,3) with target 2" in {
      solvable.condition(Jug(6, 0), Jug(3, 0), 2).isLeft

    }
    "(6,0) with target 2" in {
      solvable.condition(Jug(6, 0), Jug(0, 0), 2).isLeft

    }
  }
}

class WaterJugOpsSpec extends AnyFreeSpec with Matchers {

  "Empty Ops" - {
    "empties only right jug" in {
      val state    = State(Jug(4, 4), Jug(3, 3))
      val emptyOp  = new Empty(state)
      val newState = emptyOp.op()
      newState.right should be(Jug(3, 0))
    }
    "should not empty left jug" in {
      val state    = State(Jug(4, 4), Jug(3, 3))
      val emptyOp  = new Empty(state)
      val newState = emptyOp.op()
      newState.left should be(Jug(4, 4))
    }
  }

  "Fill Ops" - {
    "fills only left jug" in {

      val state    = State(Jug(10, 0), Jug(10, 0))
      val fillOps  = new Fill(state)
      val newState = fillOps.op()
      newState.left.quantity should be(10)

    }
    "does not fill only right jug" in {

      val state    = State(Jug(10, 0), Jug(10, 0))
      val fillOps  = new Fill(state)
      val newState = fillOps.op()
      newState.right.quantity should be(0)

    }
  }

  "Pour Ops" - {
    "pour only from left - right: new state" in {
      val state    = State(Jug(4, 4), Jug(3, 0))
      val pourOp   = new Pour(state)
      val newState = pourOp.op()
      newState.left.quantity should be(1)
      newState.right.quantity should be(3)
    }
    "pour does not change state when right is NOT empty" in {
      val state    = State(Jug(4, 4), Jug(3, 3))
      val pourOp   = new Pour(state)
      val newState = pourOp.op()
      newState.left.quantity should be(4)
      newState.right.quantity should be(3)
    }
    "pour does not change state when left is empty" in {
      val state    = State(Jug(4, 0), Jug(3, 3))
      val pourOp   = new Pour(state)
      val newState = pourOp.op()
      newState.left.quantity should ===(0)
      newState.right.quantity should be(3)
    }
    "pour only available quantity from left -> right" in {
      val state    = State(Jug(4, 2), Jug(3, 0))
      val pourOp   = new Pour(state)
      val newState = pourOp.op()
      newState.right.quantity should be(2)
      newState.left.quantity should be(0)
    }
  }
}
