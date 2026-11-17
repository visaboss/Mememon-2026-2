package cl.uchile.dcc
package acciones
trait Source:
  val nombre: String

  def acciones: List[Accion]


  def buscarAccion(nombreAccion: String): Option[Accion]

  /** Ejecuta una acción sobre un objetivo.
   */
  def doAction(accion: Accion, objetivo: Target): Boolean
