package jugadores
import unidades.{Personajes, PersonajesMagico, Unidad}
import scala.collection.mutable



class Jugadores() extends player:
  private val unidades: mutable.ListBuffer[Unidad] = mutable.ListBuffer()

  override def ListaUnidades: List[Unidad] = unidades.toList

  def agregarUnidad(unidad:Unidad): Unit =
    unidades +=unidad

  override def VictoriaOrDefeat: Boolean =
    var todasCaidas = true
    for unidad <- unidades do
      if unidad.HpPoints > 0 then todasCaidas = false
    todasCaidas
