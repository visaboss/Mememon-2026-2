package cl.uchile.dcc
package jugadores
import unidades.{Personaje, PersonajeMagico, Unidad}
import scala.collection.mutable
import observer.{BaseSubject, Observer, Subject}




class Jugador(val nombre:String = "Jugador")  extends BaseSubject[Player] ,Player,Observer[Unidad]:
  private val unidades: mutable.ListBuffer[Unidad] = mutable.ListBuffer()

  override def listaUnidades: List[Unidad] = unidades.toList

  /** Agrega unidad a grupo de jugador y attach a ella.
   *
   * @param unidad  unidad que pasa a controlar
   */
  def agregarUnidad(unidad:Unidad): Unit = {
    unidades +=unidad
    unidad.attach(this)
  }

  override def derrotado: Boolean =
    var todasCaidas = true
    for unidad <- unidades do
      if unidad.hpPoints > 0 then todasCaidas = false
    todasCaidas


  /** se ejecuta cuando una de sus unidades es derrotada, si ya no le quedan
   * unidades vivas, notifica que fue derrotado.
   */
  override def update(subject: Subject[Unidad], response: Unidad): Unit =
    if derrotado then notifyObservers(this)
