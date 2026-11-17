package cl.uchile.dcc
package observer

/** Observador del patron: reacciona si se le notifica de un evento
 *
 * @tparam T el tipo del dato que se notifica
 */
trait Observer[T]:

  /** Recibe notificacion de sujeto al que se esta suscrito.
   *
   * @param subject  sujeto que envia notificacion
   * @param response dato notificado
   */
  def update(subject: Subject[T], response: T): Unit