object WelcomeService {

  def welcome: String = "Welcome"

  def welcome(name: String) = s"${welcome}, name"
}