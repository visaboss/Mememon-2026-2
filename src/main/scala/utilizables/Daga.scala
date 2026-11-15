package cl.uchile.dcc
package utilizables
import unidades.Personaje

class Daga(nombre: String,ataque: Int,peso: Int,prop: Personaje) extends Arma(nombre, ataque, peso, prop):
  override def equipablePorCaballero: Boolean = true
  override def equipablePorLadron: Boolean = true
  override def equipablePorMagoNegro: Boolean = true
  