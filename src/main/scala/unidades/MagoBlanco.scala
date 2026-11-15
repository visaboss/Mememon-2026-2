package unidades
import acciones.{Accion, Curacion, Purificacion, Target}
import utilizables.Arma

class MagoBlanco(nombre: String,HpPoints:Int,DefensePoints: Int,weight:Int,ManaPoints:Int) extends PersonajeMagico(nombre, HpPoints, DefensePoints, weight, ManaPoints):
  override def acciones: List[Accion] =
    super.acciones ++ List(new Curacion("Curacion"), new Purificacion("Purificacion"))

  override def doAction(accion: Accion, objetivo: Target): Boolean =
    accion.ejecutadaPorMagoBlanco(this, objetivo)

  override def puedeEquipar(arma: Arma): Boolean = arma.equipablePorMagoBlanco