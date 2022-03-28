package lectures.part2oop

object CounterApp extends App {

  class Counter(val count: Int) {

    def inc: Counter = new Counter(count + 1)
    def dec: Counter = new Counter(count - 1)
    
    def inc(n: Int): Counter = if (n <= 0) this else inc.inc(n - 1)
    def dec(n: Int): Counter = if (n <= 0) this else dec.dec(n - 1)
  }



}
