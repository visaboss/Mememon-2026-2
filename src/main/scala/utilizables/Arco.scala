package utilizables

import unidades.Personaje

class Arco(nombre: String,ataque: Int,peso: Int,prop: Personaje) extends Arma(nombre, ataque, peso, prop):
  override def equipablePorCaballero: Boolean = true
  override def equipablePorArquero: Boolean = true
  override def equipablePorMagoBlanco: Boolean = true
  