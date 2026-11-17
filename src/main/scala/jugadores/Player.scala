package cl.uchile.dcc
package jugadores
import scala.collection.mutable
import unidades.Unidad
import observer.Subject

import cl.uchile.dcc.observer.Subject

trait Player extends Subject[Player]:

  /**nombre de jugador*/
  def nombre:String

 /**lista de unidades que controla jugador*/
  def listaUnidades : List[Unidad]

 /**
  * Indica si jugaddor derrotado
  *
  * @return true si ninguna unidad queda viva
  * */
  def derrotado : Boolean
