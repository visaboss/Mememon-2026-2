package utilizables
import unidades.Personaje


abstract class ArmaMagic(nombre: String, AttackPoints: Int,
                         weight: Double, owner: Personaje, val attackPointsM: Int) extends Arma(nombre, AttackPoints, weight, owner):
  override def equals(obj: Any): Boolean =
    super.equals(obj) &&
      attackPointsM == obj.asInstanceOf[ArmaMagic].attackPointsM

  override def ataqueMagico: Int = attackPointsM
  