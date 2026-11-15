package unidades
import utilizables.Arma
class Ladron(nombre:String,HpPoints: Int,DefensePoints: Int,weight: Int) extends Personaje(nombre, HpPoints, DefensePoints, weight):
  override def puedeEquipar(arma: Arma): Boolean = arma.equipablePorLadron
  
  