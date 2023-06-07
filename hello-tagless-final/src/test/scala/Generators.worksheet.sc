import org.scalacheck.Gen
import org.scalacheck.Prop._

val samples = Seq(
  (Array(2, 7, 11, 15), 9, Array(1, 1)),
  (Array(2, 7, 11, 15), 9, Array(1, 2)),
  (Array(2, 7, 11, 15), 9, Array(1, 0)),
)



Gen.oneOf(samples)



