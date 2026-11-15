package jugadores
import scala.collection.mutable
import unidades.{Unidad}

trait player:
  def ListaUnidades : List[Unidad]
  def VictoriaOrDefeat : Boolean
