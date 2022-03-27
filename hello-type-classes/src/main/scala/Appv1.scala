object sql {

  sealed trait ColumnType
  case class StringType(value: String)   extends ColumnType
  case class IntType(value: Int)         extends ColumnType
  case class NullType()                  extends ColumnType
  case class BooleanType(value: Boolean) extends ColumnType
  type Row = Seq[ColumnType]

  trait codec[A] {
    def encode(value: A): ColumnType
  }

  object codecs {

    implicit val stringCodec: codec[String] = StringType(_)
    implicit val intCodec: codec[Int]       = IntType(_)
    implicit val nullCodec: codec[Null]     = new codec[Null] {
      override def encode(value: Null): ColumnType = new NullType
    }
  }

  /**
   * curried parameters
   *
   * @param value
   * @param codec
   * @return
   */
  def encode_v1[A](value: A)(implicit codec: codec[A]): ColumnType = codec.encode(value)

  /**
   * context bound syntax
   *
   * @param value
   * @return
   */
  def encode_v2[A: codec](value: A) = {
    val con = implicitly[codec[A]]
    con.encode(value)
  }

  object ops {
    implicit class CodecOps[A](value: A) {
      def encode(implicit codec: codec[A]): ColumnType = codec.encode(value)
    }

  }
}

object Appv1 extends App {

  

  // (a)
  // create sql row without using type classes
  // create a single column of type string
  {
    import sql._    
    val nameColumn           = StringType("Naveen")
    val idColumn             = IntType(1)
    val row: Seq[ColumnType] = List(idColumn, nameColumn)
    println(row.mkString(" | "))
  }
  // (b) type-classes using interface object approach
  // create using type-classes
  // use interface object approach or explicit approach
  {
    { // v1 - explicit encoding
      import sql._
      import sql.codecs.{stringCodec, intCodec, nullCodec}
      val row_excplicit_encode: Seq[ColumnType] = List(encode_v1("Naveen"), encode_v2(1), encode_v2(null))
      println(row_excplicit_encode.mkString(" | "))
    }

    { //v2 - implicit encoding
      import sql._
      import sql.codecs.{stringCodec, intCodec}
      implicit def toColType[A](e: A)(implicit codec: codec[A]) = {
        
        //codec.encode(value = e)
        val r = encode_v1(e)
        println(s"encoded '$e' as '$r'")
        r
      }
      val row_implicit_encode: Seq[ColumnType] = List("Naveen", 1, null)
      println(row_implicit_encode.mkString(" "))
    }
  }
  // (c) type classes uing interface syntax approach
  // create row using type-classes
  // use interface syntax approach

  {
    import sql._
    import sql.codecs.{stringCodec, intCodec, nullCodec}
    import sql.ops._
    {//explicit
      //fails for null :-(
      //val row: Seq[ColumnType] = List(1.encode, "Naveen".encode, null.encode)
      val row: Seq[ColumnType] = List(1.encode, "Naveen".encode)
      println(row.mkString(" "))
    }
  }

}
