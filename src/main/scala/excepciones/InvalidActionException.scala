package cl.uchile.dcc
package excepciones

/*** excepcion base de juego, se lanza cuando no es posible hacer accion
 * Demas excepciones la extienden, de esta forma controlador puede a todas con un case
 *
 * @param message por que accion fallo
 * @cause
 *
 *
 * */

class InvalidActionException(message: String, cause:Throwable = null) extends Exception(message, cause)