package unidades

import acciones.{Accion, Meteorito, Target, Trueno}
import utilizables.Arma

class MagoNegro(nombre: String,HpPoints: Int,DefensePoints:Int,weight: Int,ManaPoints: Int) extends PersonajesMagico(nombre, HpPoints, DefensePoints, weight, ManaPoints):
  override def acciones: List[Accion] =
    super.acciones ++ List(new Trueno("Trueno"), new Meteorito("Meteorito"))

  override def doAction(accion: Accion, objetivo: Target): Boolean =
    accion.ejecutadaPorMagoNegro(this, objetivo)

  override def puedeEquipar(arma: Arma): Boolean = arma.equipablePorMagoNegro