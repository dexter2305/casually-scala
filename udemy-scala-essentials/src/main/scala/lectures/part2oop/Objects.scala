package lectures.part2oop

import java.time.Instant

object Objects {
  
  class Person(name: String) {
    private val privateFieldInClass = 10
    private def privateMethodInClass: Int = privateFieldInClass
  }

  object Person {

    def apply(): Person =  {
      val personInstance = new Person("")
      println(s"${personInstance.privateFieldInClass}")
      println(s"${personInstance.privateMethodInClass}")
      personInstance
    }
  }

}
