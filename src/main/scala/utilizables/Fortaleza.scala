package cl.uchile.dcc
package utilizables
import unidades.Personaje
class Fortaleza(nombre:String) extends PocionComun(nombre):
  override protected def aplicarA(personaje: Personaje): Boolean =
    personaje.aumentarDefensa(personaje.defensePoints * 15 / 100)
    true