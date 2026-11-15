package utilizables
import unidades.Personaje

class Baston(nombre: String, ataque: Int, peso: Double, prop: Personaje, ataqueM: Int) extends ArmaMagic(nombre, ataque, peso, prop, ataqueM):
  override def equipablePorMagoNegro: Boolean = true
  override def equipablePorMagoBlanco: Boolean = true


