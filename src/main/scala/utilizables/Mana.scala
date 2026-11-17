package cl.uchile.dcc
package utilizables
import unidades.Personaje
class Mana(nombre:String) extends PocionMagica(nombre):
  override protected def aplicarA(personaje: Personaje): Boolean =
    personaje.recibirPocionMana()
    