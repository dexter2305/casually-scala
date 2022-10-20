package lectures.part2afp

object PartialFunctions extends App {
  val afussyFunction: Int => Int = x =>
    x match {
      case 1 => 2
      case 2 => 3
      case 5 => 11
    }
  println(afussyFunction(5))  
  //println(afussyFunction(10)) // throws match error  

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 2
    case 2 => 3
    case 5 => 11
  
  }
  val lifted = aPartialFunction.lift
  println(lifted(100))

  
}
