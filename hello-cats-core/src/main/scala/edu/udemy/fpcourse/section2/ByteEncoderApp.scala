package edu.udemy.fpcourse.section2

/**
 * NOTE: Implement type-classes Use case: Represent types are byte array eg; String, Int, Switch(case class)
 */
object ByteEncoderApp extends App {

  trait ByteEncoder[A] {
    def encode(a: A): Array[Byte]
  }

  object ByteEncoder {

    /**
     *   - Creates a new instance of ByteCoder[A] from given *f*
     */
    def instance[A](f: A => Array[Byte]): ByteEncoder[A] = new ByteEncoder[A]() {
      override def encode(a: A): Array[Byte] = f(a)
    }

    /**
     *   - Summons the implicit TypeClass.
     *   - Instance may/may not be available.
     *   - Unlike *instance[A]*, it does not create one
     */
    def apply[A](implicit enc: ByteEncoder[A]): ByteEncoder[A] = enc

    // just a container for default instances for type class
    object instances {

      implicit object StringByteEncoder extends ByteEncoder[String] {
        override def encode(a: String): Array[Byte] = {
          println(s"object used")
          a.getBytes()
        }
      }
      implicit val stringByteEncoder = instance[String] { s =>
        println("val used")
        s.getBytes()
      }
    }
    object syntax {
      import instances._
      implicit class ByteEncoderOps[A](a: A) {
        def encode(implicit encoder: ByteEncoder[A]): Array[Byte] = {
          encoder.encode(a)
        }
      }
    }

  }

  //NOTE: instance approach
  import ByteEncoder.instances._
  ByteEncoder[String].encode("hello")
  
  //NOTE: syntax approach
  import ByteEncoder.syntax._
  "world".encode
}
