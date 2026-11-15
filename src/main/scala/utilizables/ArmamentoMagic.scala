package cl.uchile.dcc
package utilizables
import unidades.Personaje


abstract class ArmaMagic(nombre: String, ataque: Int,
                         peso: Double, prop: Personaje, val attackPointsM: Int) extends Arma(nombre, ataque, peso, prop):
  override def equals(obj: Any): Boolean =
    super.equals(obj) &&
      attackPointsM == obj.asInstanceOf[ArmaMagic].attackPointsM

  override def ataqueMagico: Int = attackPointsM
  