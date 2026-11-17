package cl.uchile.dcc
package unidades
import utilizables.Arma
class Arquero(nombre: String,hp: Int,defensa: Int,peso: Int) extends Personaje(nombre, hp, defensa, peso):
  override def puedeEquipar(arma: Arma): Boolean = arma.equipablePorArquero
  