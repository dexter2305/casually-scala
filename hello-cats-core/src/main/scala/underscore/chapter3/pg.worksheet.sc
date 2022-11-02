val double: Int => Int    = x => x * 2
val asText: Int => String = x => s"value is $x"

double(10)

// native scala
(double andThen asText)(10)
(asText compose double)(10)

import cats._
import cats.implicits._

// using cats
(double map asText) (10)  