package cl.uchile.dcc
package excepciones

/*** Lanza si unidad intenta moverse a un panel no aledaño
 *
 * @param message por que accion fallo
 * @param cause
 *
 *
 */
class InvalidMovementException(message:String, cause: Throwable = null) extends InvalidActionException(message, cause)