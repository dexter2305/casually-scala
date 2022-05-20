import scala.collection.mutable.Queue

val q = Queue[Int]()
q.enqueue(1)
q.enqueue(2)
q.enqueue(3)
q.enqueue(4)
val x = 4
for (_ <- 0 until q.size - 1) {
  q.enqueue(q.dequeue())
}
q

