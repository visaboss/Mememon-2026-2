package cl.uchile.dcc
package unidades
import acciones.{Accion, Curacion, Purificacion, Target}
import utilizables.Arma

class MagoBlanco(nombre: String,hp:Int,defensa: Int,peso:Int,mana:Int) extends PersonajeMagico(nombre, hp, defensa, peso, mana):
  override def acciones: List[Accion] =
    super.acciones ++ List(new Curacion("Curacion"), new Purificacion("Purificacion"))

  override protected def ejecutar(accion: Accion, objetivo: Target): Boolean =
    accion.ejecutadaPorMagoBlanco(this, objetivo)

  override def puedeEquipar(arma: Arma): Boolean = arma.equipablePorMagoBlanco