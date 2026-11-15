package utilizables

import unidades.Personaje

class Espada(nombre: String,AttackPoints: Int,weight: Int,owner: Personaje) extends Arma(nombre, AttackPoints, weight, owner):
  override def equipablePorCaballero: Boolean = true
  override def equipablePorArquero: Boolean = true
  override def equipablePorLadron: Boolean = true
  