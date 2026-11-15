package utilizables
import unidades.Personaje

class Baston(nombre: String, AttackPoints: Int, weight: Double, owner: Personaje, AttackPointsM: Int) extends ArmaMagic(nombre, AttackPoints, weight, owner, AttackPointsM):
  override def equipablePorMagoNegro: Boolean = true
  override def equipablePorMagoBlanco: Boolean = true


