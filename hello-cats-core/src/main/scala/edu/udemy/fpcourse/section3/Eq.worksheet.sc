import cats._
import cats.instances._

case class Switch(id: Int, isOn: Boolean = false)

object Switch {

  implicit val universalEquals: Eq[Switch] = Eq.fromUniversalEquals // uses == from scala native

  object Instances {

    /*
      NOTE: `Eq.instance[Switch]((a, b) => Eq[Int].eqv(a.id, b.id))` is the basic structure. Drawback is that it uses hardcoded type classs
     *Eq[Int]*. Instead, an implicit type class for Eq[Int] can be summoned using as shown below.
     */
    // elaborated form
    implicit def eqSwitchByID(implicit eqInt: Eq[Int]): Eq[Switch]         = Eq.instance[Switch]((s1, s2) => eqInt.eqv(s1.id, s2.id))
    // compare 'by' field. Similar to groupBy in Traversable
    implicit def eqSwitchByState(implicit eqBool: Eq[Boolean]): Eq[Switch] = Eq.by[Switch, Boolean](aSwitch => aSwitch.isOn)

  }

}

val switch1 = Switch(1, true)
val switch2 = Switch(2, true)

// uses default instance of Eq[Switch]
Eq[Switch].eqv(switch1, switch2)

// uses non-default instance of Eq[Switch]
Switch.Instances.eqSwitchByID.eqv(switch1, switch2)
Switch.Instances.eqSwitchByState.eqv(switch1, switch2)

