package utilizables
import unidades.Personajes
class Fortaleza(nombre:String) extends PocionComun(nombre):
  override protected def aplicarA(personaje: Personajes): Boolean =
    personaje.aumentarDefensa(personaje.defensePoints * 15 / 100)
    true