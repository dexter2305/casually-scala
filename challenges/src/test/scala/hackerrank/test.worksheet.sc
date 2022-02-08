import org.scalacheck.Gen

val spg: Gen[(String, String)] = for {
  x <- Gen.alphaNumStr.suchThat(_.length == 2)
  y <- Gen.alphaNumStr.suchThat(_.length == 2)
} yield (x, y)

