package cl.uchile.dcc
package utilizables
import acciones.AbstractTarget
import unidades.Personaje

abstract class PocionBase(override val nombre: String) extends AbstractTarget, Pocion:

  override def equals(obj: Any): Boolean =
    if getClass == obj.getClass then
      val other = obj.asInstanceOf[PocionBase]
      nombre == other.nombre
    else false
  
  override def consumirsePor(personaje: Personaje): Boolean = {
    aplicarA(personaje)
    personaje.quitarUtilizable(this)
    true
  }


  protected def aplicarA(personaje: Personaje): Boolean
