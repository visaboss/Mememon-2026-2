package utilizables
import unidades.Personaje

class Daga(nombre: String,AttackPoints: Int,weight: Int,owner: Personaje) extends Arma(nombre, AttackPoints, weight, owner):
  override def equipablePorCaballero: Boolean = true
  override def equipablePorLadron: Boolean = true
  override def equipablePorMagoNegro: Boolean = true
  