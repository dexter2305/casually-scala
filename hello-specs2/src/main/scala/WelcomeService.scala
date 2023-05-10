object WelcomeService {

  def welcome: String = "Welcome"

  def welcome(name: String): String = s"${welcome}, name"
}