package cl.uchile.dcc
package observer

/** mantiene  lista de observadores y les avisa
 * cuando ocurre un evento, sin necesitar saber que hace cada uno con el aviso
 *
 * @tparam T  tipo de dato que se notifica
 */
trait Subject[T]:

  /** attach un observador para que reciba notificaciones.
   *
   * @param observer observador a desconectar
   */
  def attach(observer: Observer[T]): Unit

  /** detach un observador,deja de recibir notificaciones.
   *
   * @param observer observador a detach
   */
  def detach(observer: Observer[T]): Unit

  /** envia notificacion a todos observadores que estan attached.
   *
   * @param notification  dato que se notifica
   */
  def notifyObservers(notification: T): Unit