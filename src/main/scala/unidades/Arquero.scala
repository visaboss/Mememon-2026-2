package unidades
import utilizables.Arma
class Arquero(nombre: String,HpPoints: Int,DefensePoints: Int,weight: Int) extends Personaje(nombre, HpPoints, DefensePoints, weight):
  override def puedeEquipar(arma: Arma): Boolean = arma.equipablePorArquero
  