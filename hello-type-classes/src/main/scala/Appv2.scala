import java.awt.Color
object Appv2 extends App {

  object sqltype {
    trait ColumnType[+A] {
      def value(): A
      def asString(): String
      override def toString(): String = asString()
    }

    final case class StringType(value: String) extends ColumnType[String] {

      override def asString(): String = value

    }
    final case class NullType() extends ColumnType[Null] {

      override def asString(): String = "NULL"

      override def value(): Null = null

    }

    trait codec[A] {
      def encode(value: A): ColumnType[A]
      def decode(columnInstance: ColumnType[A]): A = columnInstance.value()
    }

    object CodecInstances {

      implicit val stringCodec = new codec[String] {
        override def encode(value: String): ColumnType[String] = StringType(value)

      }

      implicit val nullCodec = new codec[Null] {
        override def encode(value: Null): ColumnType[Null] = {
          NullType()
        }

      }
    }

    /**
     * Front facing api to encode
     *
     * @param value
     * @return
     */
    def encode[A: codec](value: A): ColumnType[A] = {
      val codec = implicitly[codec[A]]
      println(s"value: $value, codec: $codec")
      codec.encode(value)
    }

    /**
     * Front facing api to decode
     *
     * @param columnInstance
     * @return
     */
    def decode[A](columnInstance: ColumnType[A]): A = columnInstance.value()

    object Implicits {}
  }

  // Tests

  def printRow[A](row: Seq[sqltype.ColumnType[A]]): Unit = {
    println(s"${row.mkString(" | ")}")
  }

  {
    println("v:1")
    import sqltype._
    val row: Seq[ColumnType[Any]] = List(StringType("naveen"), NullType())
    printRow(row)
  }
  {
    println("v:2")
    import sqltype._
    import sqltype.CodecInstances._
    val row: Seq[ColumnType[Any]] = List(encode("Naveen"), encode(null))
    printRow(row)
  }
  {
    println("v:3")
    import sqltype._
    import sqltype.CodecInstances._
    implicit def toColumnType[A](value: A)(implicit codec: codec[A]): ColumnType[A] = {
      println(s"wrapper for $value")
      encode(value)
    }
    implicit val toCT: Null => ColumnType[Any] = (a: Null) => {
      println("f invoked")
      NullType()
    }
    //val x: ColumnType[Null]                                                       = null
    val row: Seq[ColumnType[Any]]     = List("Naveen", null)
    printRow(row)
  }
}
