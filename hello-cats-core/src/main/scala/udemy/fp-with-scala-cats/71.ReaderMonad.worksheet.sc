import cats._
import cats.implicits._
import cats.data._

val signReader: Reader[Int, String] = Reader(n => if (n > 0) "positive" else "negative")
signReader.run(-10)

val parityReader: Reader[Int, String] = Reader(n => if ((n % 2) == 0) "even" else "odd")

val descriptionReader = for {
  sign   <- signReader
  parity <- parityReader
} yield s"$sign as $parity"

descriptionReader.run(-10)
descriptionReader.run(23)

// bigger example

// domain
case class Person(id: Int, name: String, email: String)
case class Account(id: Int, ownerid: Int)

trait PersonRepository {

  val personRepository: Service
  trait Service {
    def findById(id: Int): Person
  }
}

trait AccountRepository {

  val accountRepository: Service
  trait Service {
    def findById(id: Int): Account
    def save(account: Account): Unit
  }
}

trait EmailService {

  val emailService: Service
  trait Service {
    def sendEmail(address: String, text: String): Unit
  }
}

// implementation
trait LivePersonRepository extends PersonRepository {

  override val personRepository: Service = new Service {
    override def findById(id: Int): Person = Person(100, "Dexter", "dexter@udemy.com")
  }
}

trait LiveAccountRepository extends AccountRepository {
  override val accountRepository: Service = new Service {
    override def save(account: Account): Unit = println(s"Account $account saved.")
    override def findById(id: Int): Account = Account(1, 100)
  }
}

trait LiveEmailService extends EmailService {

  override val emailService: Service = (address, text) => println(s"Sending $text to $address")

}
// services

def findOwnerNameByAccountId(accountId: Int): Reader[PersonRepository with AccountRepository, String] =
  for {
    accountModule <- Reader(identity[AccountRepository])
    personModule  <- Reader(identity[PersonRepository])
    account = accountModule.accountRepository.findById(accountId)
    person  = personModule.personRepository.findById(account.ownerid)
  } yield person.name

def openAccount(accountId: Int, ownerId: Int): Reader[PersonRepository with AccountRepository with EmailService, Account] =
  for {
    personModule  <- Reader(identity[PersonRepository])
    accountModule <- Reader(identity[AccountRepository])

    account = Account(accountId, ownerId)
    _       = accountModule.accountRepository.save(account)
    emailModule <- Reader(identity[EmailService])
    person = personModule.personRepository.findById(ownerId)
    _      = emailModule.emailService.sendEmail(person.email, s"Account ${account.id} created.")
  } yield account

// wiring
type Env = PersonRepository with AccountRepository with EmailService
val liveEnv: Env = new LivePersonRepository with LiveAccountRepository with LiveEmailService

findOwnerNameByAccountId(100).run(liveEnv)
val x = openAccount(200, 1).run(liveEnv)
