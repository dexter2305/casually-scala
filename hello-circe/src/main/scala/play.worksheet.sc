import io.circe._
import io.circe.syntax._

case class Product(name: String)
object Product {

  implicit val productEncoder: Encoder[Product] = product =>
    Json.obj(
      "name" -> product.name.asJson,
    )
}

case class User(id: Int, name: String)
case class Order(id: Int, products: Seq[Product], user: User)

// data
val pencil   = Product("pencil")
val sharpner = Product("Sharpner")
val user     = User(1, "Light yagami")
val order    = Order(1, Seq(pencil, sharpner), user)

// Encoders
// test with Json.obj() implementation
pencil.asJson.noSpaces
val maybePencil = Option(pencil)
maybePencil.asJson.spaces2

// import io.circe.generic.auto._
// user.asJson

import io.circe.generic.semiauto._
implicit val userEncoder: Encoder[User]   = deriveEncoder[User]
implicit val orderEncoder: Encoder[Order] = deriveEncoder[Order]
order.asJson

// Decoder

val userJson: Json = User(1, "El").asJson

val invalidUser = Json.obj(
  "x" -> 1.asJson,
)

implicit val userDecoder: Decoder[User] = Decoder.forProduct2[User, Int, String]("id", "name")((id, name) => User(id, name))
userDecoder(userJson.hcursor)
userJson.as[User]
userDecoder(invalidUser.hcursor)
userDecoder.decodeAccumulating(invalidUser.hcursor)
