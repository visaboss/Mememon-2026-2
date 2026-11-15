package cl.uchile.dcc
package jugadores
import unidades.{Personaje, PersonajeMagico, Unidad}
import scala.collection.mutable



class Jugador() extends Player:
  private val unidades: mutable.ListBuffer[Unidad] = mutable.ListBuffer()

  override def listaUnidades: List[Unidad] = unidades.toList

  def agregarUnidad(unidad:Unidad): Unit =
    unidades +=unidad

  override def derrotado: Boolean =
    var todasCaidas = true
    for unidad <- unidades do
      if unidad.hpPoints > 0 then todasCaidas = false
    todasCaidas
