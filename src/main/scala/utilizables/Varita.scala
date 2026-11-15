package cl.uchile.dcc
package utilizables
import unidades.Personaje

class Varita(nombre: String, ataque: Int, peso: Double, prop: Personaje, ataqueM: Int) extends ArmaMagic(nombre, ataque, peso, prop, ataqueM):
  override def equipablePorArquero: Boolean = true
  override def equipablePorMagoNegro: Boolean = true
  override def equipablePorMagoBlanco: Boolean = true

