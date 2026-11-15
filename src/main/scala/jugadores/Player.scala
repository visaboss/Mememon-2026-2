package cl.uchile.dcc
package jugadores
import scala.collection.mutable
import unidades.{Unidad}

trait Player:
  def listaUnidades : List[Unidad]
  def derrotado : Boolean
