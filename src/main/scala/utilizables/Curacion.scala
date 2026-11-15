package utilizables
import unidades.Personaje

class Curacion(nombre:String) extends PocionComun(nombre):
  override protected def aplicarA(personaje: Personaje): Boolean =
    personaje.curar(personaje.maxHpPoints * 20 / 100)
    true