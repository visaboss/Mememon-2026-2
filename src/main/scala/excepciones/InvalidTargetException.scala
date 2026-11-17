package cl.uchile.dcc
package excepciones


/** Lanza si objetivo no puede recibir la accion
 *
 * @param message por que accion fallo
 * @param cause
 * */
class InvalidTargetException(message: String, cause: Throwable = null) extends InvalidActionException(message, cause)