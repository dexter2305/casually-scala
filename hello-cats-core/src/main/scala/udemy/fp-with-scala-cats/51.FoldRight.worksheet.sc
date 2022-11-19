sealed trait MList[+A]
case object MNil                             extends MList[Nothing]
case class MCons[+A](head: A, acc: MList[A]) extends MList[A]

def sum(ints: MList[Int]): Int = ints match {
  case MCons(v, rest) => v + sum(rest)
  case MNil           => 0
}

val l = MCons(1, MCons(2, MCons(3, MCons(-20, MNil))))
sum(l)

def length[A](list: MList[A]): Int = list match {
  case MCons(_, rest) => 1 + length(rest)
  case MNil           => 0
}
length(l)

def filterPositive(list: MList[Int]): MList[Int] = list match {
  case MCons(head, rest) => if (head > 0) MCons(head, filterPositive(rest)) else filterPositive(rest)
  case MNil              => MNil
}

filterPositive(l)
filterPositive(MCons(-1, MNil))
